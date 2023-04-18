package com.example.backend.service.interfaces;

import com.example.backend.model.ExamType;

import java.util.List;

public interface ExamTypeService {

    ExamType getType(Long id);

    List<ExamType> findAll();
}
