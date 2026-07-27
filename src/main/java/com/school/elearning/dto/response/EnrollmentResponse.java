package com.school.elearning.dto.response;

import com.school.elearning.enums.EnrollmentStatus;

import java.time.LocalDateTime;

public record EnrollmentResponse(
        Long id ,
        LocalDateTime enrolledAt ,
        EnrollmentStatus status ,
        Long userId ,
        String userName
) {
}
