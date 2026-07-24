package com.school.elearning.dto;

import com.school.elearning.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotEmpty(message = "full name should not be empty")
        String fullName ,
        @NotEmpty(message = "Email should not be empty")
        String email ,
        @Size(min = 6 , max = 30)
        String password,
        @NotEmpty(message = "Role can not be empty")
        UserRole role
) {
}
