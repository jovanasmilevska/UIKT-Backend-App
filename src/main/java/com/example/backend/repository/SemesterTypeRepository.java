package com.example.backend.repository;

import com.example.backend.model.SemesterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterTypeRepository extends JpaRepository<SemesterType,Long> {
    SemesterType findByName(String name);
}
