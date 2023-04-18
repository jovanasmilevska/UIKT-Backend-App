package com.example.backend.repository;

import com.example.backend.model.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year,Long> {
    Year findByName(String name);
}
