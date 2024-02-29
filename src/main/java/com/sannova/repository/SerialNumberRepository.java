package com.sannova.repository;

import com.sannova.model.SerialNumberCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerialNumberRepository extends JpaRepository<SerialNumberCount,Integer> {
}
