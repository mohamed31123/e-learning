package com.school.elearning.dto.response;

import com.school.elearning.entity.Progress;
import com.school.elearning.enums.EnrollmentStatus;

import java.time.LocalDateTime;
import java.util.List;

public record EnrollmentResponse(
        Long id ,
        LocalDateTime enrolledAt ,
        EnrollmentStatus status ,
        Long userId ,
        String userName ,
        List<Progress> progressList
) {
}
