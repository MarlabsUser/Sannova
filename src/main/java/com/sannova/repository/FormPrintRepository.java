package com.sannova.repository;

import com.sannova.model.FormPrintDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface FormPrintRepository extends JpaRepository<FormPrintDetails,Integer> {
      List<FormPrintDetails> findByStudyName(String studyNumber);
      List<FormPrintDetails> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDateTime fromDate,LocalDateTime toDate);
}
