package com.example.backend.web;

import com.example.backend.model.Year;
import com.example.backend.service.interfaces.YearService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/year")
public class YearController {

    private final YearService yearService;

    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    @GetMapping("/all")
    public List<Year> getAllYears(){
        return yearService.allYears();
    }

    @GetMapping("/{id}")
    public Year getYear(@PathVariable Long id){
        return yearService.getYear(id);
    }

}
