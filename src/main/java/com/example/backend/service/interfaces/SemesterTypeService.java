package com.example.backend.service.interfaces;

import com.example.backend.model.SemesterType;

import java.util.List;

public interface SemesterTypeService {

    SemesterType findSemesterTypeByName(String name);

    SemesterType findById(Long id);

    List<SemesterType> findAll();
}
