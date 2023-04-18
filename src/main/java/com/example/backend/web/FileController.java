package com.example.backend.web;

import com.example.backend.model.ExamType;
import com.example.backend.model.File;
import com.example.backend.service.interfaces.ExamTypeService;
import com.example.backend.service.interfaces.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping ("/file")
public class FileController {

    private final FileService fileService;
    private final ExamTypeService examTypeService;

    public FileController(FileService fileService, ExamTypeService exam_typeService) {
        this.fileService = fileService;
        this.examTypeService = exam_typeService;
    }

    @GetMapping("/get/{id}")
    public File getFile(@PathVariable Long id){
        return fileService.getFile(id);
    }

    @GetMapping("/{id}")
    public List<File> filesForSubject(@PathVariable Long id){
        return fileService.findFilesForSubject(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void uploadFiles(@PathVariable Long id, @RequestParam("type") Long type,
                            @RequestParam("files") List<MultipartFile> files){

        ExamType exam_type = examTypeService.getType(type);
        for(MultipartFile file : files){
            fileService.saveFile(id, file, exam_type);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id){

        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getContent()));
    }

    @GetMapping("/openFile/{id}")
    public ResponseEntity<ByteArrayResource> openFile(@PathVariable Long id){

        File file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline:filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getContent()));
    }
}