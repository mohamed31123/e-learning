package com.school.elearning.dto.request;

import java.time.LocalDateTime;

public record ProgressRequest(
        LocalDateTime createdAt ,
        Long lessonId ,
        Long enrollmentId ,
        boolean isCompleted
) {
}
