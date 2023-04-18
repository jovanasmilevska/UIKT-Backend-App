package com.example.backend.web;

import com.example.backend.model.SemesterType;
import com.example.backend.service.interfaces.SemesterTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semester/type")
public class SemesterTypeController {

    private final SemesterTypeService semesterTypeService;

    public SemesterTypeController(SemesterTypeService semesterTypeService) {
        this.semesterTypeService = semesterTypeService;
    }

    @GetMapping("/all")
    public List<SemesterType> getAllSemesterTypes(){
        return semesterTypeService.findAll();
    }

    @GetMapping("/{id}")
    public SemesterType getSemesterType(@PathVariable Long id){
        return semesterTypeService.findById(id);
    }
}
