package com.sannova.repository;

import com.sannova.model.StudyTypes;
import com.sannova.model.User;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyTypesRepository extends JpaRepository<StudyTypes,Integer> {
    Optional<StudyTypes> findByIdAndTemplateDetailsStatus(Integer study_id,Boolean status);
}
