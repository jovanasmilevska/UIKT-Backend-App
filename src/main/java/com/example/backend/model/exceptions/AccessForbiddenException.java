package com.example.backend.model.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed"})
public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException(){
        super("Access forbidden");
    }
}
