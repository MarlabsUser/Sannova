package com.sannova.repository;

import com.sannova.model.FormPrintDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface FormPrintRepository extends JpaRepository<FormPrintDetails,Integer> {
      List<FormPrintDetails> findByStudyName(String studyNumber);
      List<FormPrintDetails> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDate fromDate,LocalDate toDate);

      Optional<FormPrintDetails> findTopByStudyIdIdAndCreatedAtGreaterThanEqual(Integer id,LocalDate fromDate);

      Optional<FormPrintDetails> findByStudyNameAndTemplateDetailsId(String studyNumber,Integer templateId);

}
