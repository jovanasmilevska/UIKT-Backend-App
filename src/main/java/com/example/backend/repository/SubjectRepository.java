package com.example.backend.repository;

import com.example.backend.model.SemesterType;
import com.example.backend.model.Subject;
import com.example.backend.model.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllByYear(Year year);
    List<Subject> findAllByNameContainsIgnoreCase(String name);
    List<Subject> findAllByYearAndSemesterType(Year year, SemesterType semesterType);
    Subject findByNameAndYearAndSemesterType(String name, Year year, SemesterType semesterType);
    List<Subject> findAllByName(String name);
    void deleteById(Long id);
    Subject findSubjectByNameIgnoreCase(String name);

}
