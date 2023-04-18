package com.example.backend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException(){
        super("Subject not found!");
    }
}
