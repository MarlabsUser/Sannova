package com.sannova.repository;

import com.sannova.model.FormPrintDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface FormPrintRepository extends JpaRepository<FormPrintDetails,Integer> {
      List<FormPrintDetails> findByStudyName(String studyNumber);
      List<FormPrintDetails> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDate fromDate,LocalDate toDate);

      Optional<FormPrintDetails> findTopByStudyTypesIdAndCreatedAtGreaterThanEqual(Integer id,LocalDate fromDate);

      Optional<FormPrintDetails> findTop1ByStudyTypesIdAndTemplateDetailsIdOrderByIdDesc(Integer studyId,Integer templateId);


      @Query(nativeQuery = true,value = "select serial_count from serial_number_count snc where form_details_id=:formId \n"
              + "order by serial_count desc limit 1")
      Integer findLastSerialNumberCount(@Param("formId")Integer formId);

}
