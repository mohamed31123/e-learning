package com.school.elearning.dto;

import com.school.elearning.enums.UserRole;

public record UserResponse(
        Long id ,
        String fullName,
        String email ,
        UserRole role
) {
}
