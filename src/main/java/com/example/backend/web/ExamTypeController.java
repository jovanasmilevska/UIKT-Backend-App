package com.example.backend.web;

import com.example.backend.model.ExamType;
import com.example.backend.service.interfaces.ExamTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam-type")
public class ExamTypeController {

    private final ExamTypeService examTypeService;

    public ExamTypeController(ExamTypeService examTypeService) {
        this.examTypeService = examTypeService;
    }

    @GetMapping("/all")
    public List<ExamType> getAllSemesterTypes(){
        return examTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ExamType getSemesterType(@PathVariable Long id){
        return examTypeService.getType(id);
    }

}
