package com.sannova.repository;

import com.sannova.model.TemplateDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateDetailsRepository extends JpaRepository<TemplateDetails,Integer> {

    List<TemplateDetails> findByStatusAndStudyTypesIdIn(Boolean status,List<Integer> studyId);

}
