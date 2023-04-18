package com.example.backend.service.impl;

import com.example.backend.model.SemesterType;
import com.example.backend.model.exceptions.SemesterTypeNotFound;
import com.example.backend.repository.SemesterTypeRepository;
import com.example.backend.service.interfaces.SemesterTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterTypeServiceImpl implements SemesterTypeService {
    private final SemesterTypeRepository semesterTypeRepository;

    public SemesterTypeServiceImpl(SemesterTypeRepository semesterTypeRepository) {
        this.semesterTypeRepository = semesterTypeRepository;
    }

    @Override
    public SemesterType findSemesterTypeByName(String name) {
        return semesterTypeRepository.findByName(name);
    }

    @Override
    public SemesterType findById(Long id) {
        return semesterTypeRepository.findById(id).orElseThrow(SemesterTypeNotFound::new);
    }

    @Override
    public List<SemesterType> findAll() {
        return semesterTypeRepository.findAll();
    }


}
