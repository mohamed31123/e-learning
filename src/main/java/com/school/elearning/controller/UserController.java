package com.school.elearning.controller;


import com.school.elearning.dto.UserRequest;
import com.school.elearning.dto.UserResponse;
import com.school.elearning.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserResponse create( @Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/getAll")
    public List<UserResponse> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {

        userService.deleteUserById(id);
    }
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id,@Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(userRequest, id);
    }
}
