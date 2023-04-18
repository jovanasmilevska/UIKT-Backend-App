package com.example.backend.repository;

import com.example.backend.model.File;
import com.example.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllBySubject(Subject subject);
}
