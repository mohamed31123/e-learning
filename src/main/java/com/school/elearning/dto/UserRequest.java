package com.school.elearning.dto;

import com.school.elearning.enums.UserRole;

public record UserRequest(
        String fullName ,
        String email ,
        String password,
        UserRole role
) {
}
