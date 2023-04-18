package com.example.backend.service.interfaces;

import com.example.backend.model.Year;

import java.util.List;

public interface YearService {

    Year getYear(Long id);

    List<Year> allYears();

    Year findByName(String name);
}
