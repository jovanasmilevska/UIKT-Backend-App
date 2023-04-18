package com.example.backend.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class YearNotFoundException extends RuntimeException{
    public YearNotFoundException(){
        super("Year not found");
    }
}
