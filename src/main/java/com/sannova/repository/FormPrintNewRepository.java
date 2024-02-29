package com.sannova.repository;
import com.sannova.model.FormPrintDetailsBackUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FormPrintNewRepository extends JpaRepository<FormPrintDetailsBackUp,Integer> {
    List<FormPrintDetailsBackUp> findByStudyName(String studyNumber);
    List<FormPrintDetailsBackUp> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDate fromDate, LocalDate toDate);

}
