package com.example.backend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SubjectAlreadyExistsException extends RuntimeException{
    public SubjectAlreadyExistsException(){
        super("Subject not found");
    }
}
