package com.example.backend.service.interfaces;


import com.example.backend.model.ExamType;
import com.example.backend.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    File getFile(Long id);

    void saveFile(Long id, MultipartFile file, ExamType type);

    List<File> findFilesForSubject(Long id);

    void deleteFile(Long id);
}
