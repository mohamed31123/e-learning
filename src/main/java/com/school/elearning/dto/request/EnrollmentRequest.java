package com.school.elearning.dto.request;

import com.school.elearning.enums.EnrollmentStatus;

import java.time.LocalDateTime;

public record EnrollmentRequest(
        LocalDateTime enrolledAt ,
        EnrollmentStatus status
) {
}
