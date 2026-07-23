package com.school.elearning.service;


import com.school.elearning.dto.UserRequest;
import com.school.elearning.dto.UserResponse;
import com.school.elearning.entity.User;
import com.school.elearning.mapper.UserMapper;
import com.school.elearning.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        User user1Created = userRepository.save(user);
        UserResponse userResponse = userMapper.toResponse(user1Created);
        return userResponse;
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(null);
        return userMapper.toResponse(user);

    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponse updateUser(UserRequest userRequest , Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(null);
        userMapper.updateUser(userRequest);
        UserResponse userResponse = userMapper.toResponse(user);
        return userResponse;
    }


    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(null);
        userRepository.delete(user);
    }
}
