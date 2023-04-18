package com.example.backend.service.impl;

import com.example.backend.model.exceptions.SubjectNotFoundException;
import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import com.example.backend.model.requests.AddSubjectRequest;
import com.example.backend.model.requests.EditSubjectRequest;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.interfaces.SemesterTypeService;
import com.example.backend.service.interfaces.SubjectService;
import com.example.backend.service.interfaces.YearService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SemesterTypeService semesterTypeService;
    private final YearService yearService;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SemesterTypeService semesterTypeService, YearService yearService) {
        this.subjectRepository = subjectRepository;
        this.semesterTypeService = semesterTypeService;
        this.yearService = yearService;
    }

    @Override
    public List<Subject> allSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
    }

    @Override
    public List<Subject> findAllSubjectsByYear(Year year) {
        return subjectRepository.findAllByYear(year);
    }

    @Override
    public List<Subject> findAllSubjectsByYearAndSemesterType(Year year, SemesterType semesterType) {
        return subjectRepository.findAllByYearAndSemesterType(year, semesterType);
    }

    @Override
    public List<Subject> findAllSubjectsByName(String name) {
        return subjectRepository.findAllByNameContainsIgnoreCase(name);
    }

    @Override
    public Page<Subject> findPaginatedSubjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return subjectRepository.findAll(pageable);
    }

    @Override
    public void addSubject(AddSubjectRequest subjectHelper) {
        SemesterType semesterType = semesterTypeService.findById(subjectHelper.getSemesterType());
        Year year = yearService.getYear(subjectHelper.getYear());
        Subject newSubject = new Subject(subjectHelper.getName(), semesterType, year);
        subjectRepository.save(newSubject);
    }

    @Override
    public List<Subject> findAllByFullName(String name) {
        return subjectRepository.findAllByName(name);
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void editSubject(EditSubjectRequest subjectHelperEdit) {
        Subject subject = subjectRepository.findById(subjectHelperEdit.getId()).orElseThrow(SubjectNotFoundException ::new);
        subject.setName(subjectHelperEdit.getName());
        Year year = yearService.findByName(subjectHelperEdit.getYear().getName());
        SemesterType semesterType = semesterTypeService.findSemesterTypeByName(subjectHelperEdit.getSemesterType().getName());
        subject.setYear(year);
        subject.setSemesterType(semesterType);
        subjectRepository.save(subject);
    }
}

