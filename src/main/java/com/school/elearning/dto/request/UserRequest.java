package com.school.elearning.dto.request;

import com.school.elearning.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "full name should not be Null")
        String fullName,
        @NotBlank(message = "Email should not be Null")
        String email,
        @Size(min = 8, max = 50,
                message = "password must be between 8 and 50 characters")
        String password,
        @NotNull(message = "Role is  required")
        UserRole role
) {
}
