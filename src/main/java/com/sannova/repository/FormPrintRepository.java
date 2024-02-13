package com.sannova.repository;

import com.sannova.model.FormPrintDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FormPrintRepository extends JpaRepository<FormPrintDetails,Integer> {

}
