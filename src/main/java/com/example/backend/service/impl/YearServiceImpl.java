package com.example.backend.service.impl;

import com.example.backend.model.Year;
import com.example.backend.model.exceptions.YearNotFoundException;
import com.example.backend.repository.YearRepository;
import com.example.backend.service.interfaces.YearService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearServiceImpl implements YearService {

    private final YearRepository yearRepository;

    public YearServiceImpl(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    @Override
    public Year getYear(Long id) {
        return yearRepository.findById(id).orElseThrow(YearNotFoundException::new);
    }

    @Override
    public List<Year> allYears() {
        return yearRepository.findAll();
    }

    @Override
    public Year findByName(String name) {
         return yearRepository.findByName(name);
    }
}
