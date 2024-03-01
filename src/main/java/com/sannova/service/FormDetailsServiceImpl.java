package com.sannova.service;

import com.sannova.dto.*;
import com.sannova.model.FormPrintDetails;
import com.sannova.model.SerialNumberCount;
import com.sannova.model.StudyTypes;
import com.sannova.model.TemplateDetails;
import com.sannova.repository.FormPrintRepository;
import com.sannova.repository.SerialNumberRepository;
import com.sannova.repository.StudyTypesRepository;
import com.sannova.repository.TemplateDetailsRepository;
import com.sannova.util.WordOperationUtils;
import com.sannova.util.ZipConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormDetailsServiceImpl implements FormDetailsService{

    private final FormPrintRepository formPrintRepository;
    private  final TemplateDetailsRepository templateDetailsRepository;
    private  final StudyTypesRepository studyTypesRepository;

    private final SerialNumberRepository serialNumberRepository;

    @Override
    public String getStudyNumber(Integer study_id) {
        Optional<FormPrintDetails> formPrintDetails=formPrintRepository.findTopByStudyTypesIdAndCreatedAtGreaterThanEqual(study_id,LocalDate.now());
        if(formPrintDetails.isPresent()){
            return formPrintDetails.get().getStudyName();
        }else{
            String studyName = "SV";
            String date = String.valueOf(LocalDateTime.now().getYear());
            Integer count = Math.toIntExact(formPrintRepository.count());
            Integer Number=count+1;
            String studyNumber=studyName+Number+date;
            return studyNumber;
        }
    }

    @Override
    public byte[] addFormConfirmationDetails(FormConfirmationRequest request) throws IOException {

        Optional<StudyTypes> studyTypes=studyTypesRepository.findById(request.getStudyId());
        List<SerialNumberCount> serialNumbersDetails=request.getTemplateDetails().stream().map(templateDetail->{
            Optional<TemplateDetails> templateDetails = templateDetailsRepository.findById(templateDetail.getTemplateId());
            FormPrintDetails formPrint = new FormPrintDetails();
            formPrint.setStudyName(request.getStudyNumber());
            formPrint.setNumberOfFormsCount(templateDetail.getFormCount());
            formPrint.setPrintBy(request.getUsername());
            formPrint.setStudyTypes(studyTypes.get());
            formPrint.setTemplateDetails(templateDetails.get());
            FormPrintDetails finalFormPrint=formPrintRepository.save(formPrint);

            AtomicInteger lastSerialNumber= new AtomicInteger(templateDetail.getStartingSerialNumber());
            List<SerialNumberCount> serialNumberCounts=templateDetail.getSerialNumberList().stream()
                    .map(serialNumber->{
                        SerialNumberCount serialNumberCount=new SerialNumberCount();
                        serialNumberCount.setFormPrintDetails(finalFormPrint);
                        serialNumberCount.setSerialCount(lastSerialNumber.get());
                        serialNumberCount.setSerialNumber(serialNumber);
                        lastSerialNumber.set(lastSerialNumber.get() + 1);
                        return serialNumberCount;
                    }).collect(Collectors.toList());
            serialNumberCounts=serialNumberRepository.saveAll(serialNumberCounts);
            return serialNumberCounts;
        }).flatMap(List::stream).collect(Collectors.toList());

        List<ZipFormattedFiles> zipFormattedFiles= WordOperationUtils.replaceDocxBySerialId(serialNumbersDetails);
        return ZipConvert.zipBytes(zipFormattedFiles);
    }

    @Override
    public IdGeneratorResponse generateId(IdGeneratorResquest request) {
        List<IdGeneratorTemplateDetailsResponse> templateDetails=
                request.getStudyTypeDetailsId().stream().map(templatedRequest->{
            Optional<FormPrintDetails> formPrintDetails= formPrintRepository.findTop1ByStudyTypesIdAndTemplateDetailsIdOrderByIdDesc(request.getStudyId(),templatedRequest.getTemplateId());
            String serialNumberPrefix=formPrintDetails.isPresent()?
                    formPrintDetails.get().getTemplateDetails().getSerialNumberPrefix()
                    :templateDetailsRepository.findById(templatedRequest.getTemplateId()).get().getSerialNumberPrefix();
            List<String> listOfSerialNumber = new ArrayList<>();
            Integer startValue=0,endValue=templatedRequest.getFormCount();
            if(formPrintDetails.isPresent()){
                startValue=formPrintRepository.findLastSerialNumberCount(formPrintDetails.get().getId())+1;
                endValue=startValue+templatedRequest.getFormCount();
            }

            for (int i = startValue; i < endValue; i++) {
                String serialNumber="";
                switch (String.valueOf(i).length()){
                    case 1:
                    case 0:
                        serialNumber=serialNumberPrefix+"000"+i;
                        break;
                    case 2:
                        serialNumber=serialNumberPrefix+"00"+i;
                        break;
                    case 3:
                        serialNumber=serialNumberPrefix+"0"+i;
                        break;
                    default:
                        serialNumber=serialNumberPrefix+i;
                }
                listOfSerialNumber.add(serialNumber);
            }
            IdGeneratorTemplateDetailsResponse templateDetailsResponse = new IdGeneratorTemplateDetailsResponse();
            templateDetailsResponse.setFormCount(templatedRequest.getFormCount());
            templateDetailsResponse.setTemplateId(templatedRequest.getTemplateId());
            templateDetailsResponse.setTemplateName(templatedRequest.getTemplateName());
            templateDetailsResponse.setSerialNumberList(listOfSerialNumber);
            templateDetailsResponse.setFirstSerialNumber(listOfSerialNumber.isEmpty()?null:listOfSerialNumber.get(0));
            templateDetailsResponse.setLastSerialNumber(listOfSerialNumber.isEmpty()?null:listOfSerialNumber.get(listOfSerialNumber.size()-1));
            templateDetailsResponse.setStartingSerialNumber(startValue);
            return templateDetailsResponse;
        }).collect(Collectors.toList());

        IdGeneratorResponse idGeneratorResponse=new IdGeneratorResponse();
        idGeneratorResponse.setTemplateDetails(templateDetails);
        idGeneratorResponse.setStudyNumber(request.getStudyNumber());
        idGeneratorResponse.setUsername(request.getUsername());
        idGeneratorResponse.setStudyId(request.getStudyId());

        //Form_details:  find study id and template id
        // if not present, starts from 0 to end.
        // if present, get  new table reference and get  serial number last count and add then new count with it.
        //response type


        return idGeneratorResponse;

    }
}