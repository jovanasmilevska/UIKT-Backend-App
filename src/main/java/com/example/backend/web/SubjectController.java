package com.example.backend.web;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.model.exceptions.SubjectAlreadyExistsException;
import com.example.backend.model.requests.AddSubjectRequest;
import com.example.backend.model.requests.EditSubjectRequest;
import com.example.backend.service.interfaces.SemesterTypeService;
import com.example.backend.service.interfaces.SubjectService;
import com.example.backend.service.interfaces.YearService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final YearService yearService;
    private final SemesterTypeService semesterTypeService;

    public SubjectController(SubjectService subjectService, YearService yearService, SemesterTypeService semesterTypeService) {
        this.subjectService = subjectService;
        this.yearService = yearService;
        this.semesterTypeService = semesterTypeService;
    }

    @GetMapping("/all")
    public List<Subject> getAllSubjects() {
        return subjectService.allSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Long id) {
        return subjectService.findById(id);
    }

    @GetMapping("/filter/semester")
    public List<Subject> getAllSubjectsByYearAndSemester(@RequestParam (required = false) Long semesterId, @RequestParam Long yearId) {
        Year year = yearService.getYear(yearId);
        if (semesterId == null) {
            return subjectService.findAllSubjectsByYear(year);
        } else {
            SemesterType semesterType = semesterTypeService.findById(semesterId);
            return subjectService.findAllSubjectsByYearAndSemesterType(year, semesterType);
        }
    }

    @GetMapping("/search")
    public List<Subject> searchByName(@RequestParam String value) {
        return subjectService.findAllSubjectsByName(value);
    }

    @GetMapping("/page/{pageNo}/{pageSize}")
    public List<Subject> findPaginated(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        Page<Subject> page = subjectService.findPaginatedSubjects(pageNo, pageSize);
        return page.getContent();
    }

    @GetMapping("/totalSubjects")
    public int findNumberOfTotalSubjects() {
        return subjectService.allSubjects().size();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addSubject(@RequestBody AddSubjectRequest request) {
        if (subjectService.findAllByFullName(request.getName()).size() != 0) {

            throw new SubjectAlreadyExistsException();
        }
        subjectService.addSubject(request);
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editSubject(@RequestBody EditSubjectRequest request) {
        subjectService.editSubject(request);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteById(id);
    }

//    @GetMapping("/image")
//    public void image(@RequestParam MultipartFile image){
//        BufferedImage myPicture = (BufferedImage) image;
//
//        Graphics2D g = (Graphics2D) myPicture.getGraphics();
//
//    }
}
