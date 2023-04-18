package com.example.backend.service.impl;

import com.example.backend.model.ExamType;
import com.example.backend.model.File;
import com.example.backend.model.Subject;
import com.example.backend.model.exceptions.FileNotFoundException;
import com.example.backend.repository.FileRepository;
import com.example.backend.service.interfaces.FileService;
import com.example.backend.service.interfaces.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final SubjectService subjectService;

    public FileServiceImpl(FileRepository fileRepository, SubjectService subjectService) {
        this.fileRepository = fileRepository;
        this.subjectService = subjectService;
    }


    @Override
    public File getFile(Long id) {
        return fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public void saveFile(Long id, MultipartFile file, ExamType type) {
        Subject sub = subjectService.findById(id);
        File newFile = new File();
        newFile.setName(file.getOriginalFilename());
        newFile.setSubject(sub);
        newFile.setExamType(type);
        newFile.setMimeType(file.getContentType());
        try {
            newFile.setContent(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileRepository.save(newFile);
    }

    @Override
    public List<File> findFilesForSubject(Long id) {
        Subject sub = subjectService.findById(id);
        return fileRepository.findAllBySubject(sub);
    }

    @Override
    public void deleteFile(Long id) {
        File file = this.getFile(id);
        fileRepository.delete(file);
    }
}


