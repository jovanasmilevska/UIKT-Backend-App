package com.example.backend.service.interfaces;

import com.example.backend.model.Subject;
import com.example.backend.model.User;
import com.example.backend.model.responses.UserDetailsResponse;
import com.example.backend.model.requests.RegisterRequest;

public interface UserService {

    User findUserByEmail(String email);

    void register(String email, String password, RegisterRequest helper);

    boolean passwordMatches(User user, String password);

    UserDetailsResponse getUserDetails();

    User findById(Long id);

    void takeSubject(User user, Subject subject);

    void removeSubject(User user, Subject subject);

}
