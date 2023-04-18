package com.example.backend.web;

import com.example.backend.model.Subject;
import com.example.backend.model.User;
import com.example.backend.model.responses.UserDetailsResponse;
import com.example.backend.model.exceptions.EmailAlreadyExistsException;
import com.example.backend.model.requests.RegisterRequest;
import com.example.backend.service.interfaces.SubjectService;
import com.example.backend.service.interfaces.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final SubjectService subjectService;

    public UserController(UserService userService, SubjectService subjectService) {
        this.userService = userService;
        this.subjectService = subjectService;
    }


    @PostMapping("/register")
    public void register(@RequestHeader String email,
                         @RequestHeader String password,
                         @RequestBody RegisterRequest request) {

        User user = userService.findUserByEmail(email);
        if (user != null) {
            throw new EmailAlreadyExistsException();
        }
        userService.register(email, password, request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }

    @GetMapping("/details")
    @PreAuthorize("isAuthenticated()")
    public UserDetailsResponse userDetails(){
        return userService.getUserDetails();
    }

    @GetMapping("/{id}/subjects")
    public List<Subject> getUserSubjects(@PathVariable Long id){
        User user=userService.findById(id);
        return user.getFavoriteSubjects();
    }

    @GetMapping("/takeSubject")
    public void subjectTakenByUser(@RequestParam Long userId, @RequestParam Long subjectId){
        User user=userService.findById(userId);
        Subject subject=subjectService.findById(subjectId);
        userService.takeSubject(user,subject);

    }

    @GetMapping("/removeSubject")
    public void removeSubjectForUser(@RequestParam Long userId, @RequestParam Long subjectId){
        User user=userService.findById(userId);
        Subject subject=subjectService.findById(subjectId);
        userService.removeSubject(user,subject);
    }
}
