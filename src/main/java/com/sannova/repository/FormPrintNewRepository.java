package com.sannova.repository;
import com.sannova.model.FormPrintDetails;
import com.sannova.model.FormPrintDetailsNew;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FormPrintNewRepository extends JpaRepository<FormPrintDetailsNew,Integer> {
    List<FormPrintDetailsNew> findByStudyName(String studyNumber);
    List<FormPrintDetailsNew> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDate fromDate,LocalDate toDate);

}
