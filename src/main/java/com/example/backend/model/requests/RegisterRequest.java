package com.example.backend.model.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String name;
    private String surname;
}
