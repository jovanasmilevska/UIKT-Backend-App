package com.example.backend.service.impl;


import com.example.backend.model.ExamType;
import com.example.backend.model.exceptions.ExamTypeNotFoundException;
import com.example.backend.repository.ExamTypeRepository;
import com.example.backend.service.interfaces.ExamTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamTypeServiceImpl implements ExamTypeService {

    private final ExamTypeRepository examTypeRepository;

    public ExamTypeServiceImpl(ExamTypeRepository examTypeRepository) {
        this.examTypeRepository = examTypeRepository;
    }

    @Override
    public ExamType getType(Long id) {
        return examTypeRepository.findById(id).orElseThrow(ExamTypeNotFoundException::new);
    }

    @Override
    public List<ExamType> findAll() {
        return examTypeRepository.findAll();
    }
}
