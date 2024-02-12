package com.sannova.repository;

import com.sannova.model.StudyTypes;
import com.sannova.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyTypesRepository extends JpaRepository<StudyTypes,Integer> {
}
