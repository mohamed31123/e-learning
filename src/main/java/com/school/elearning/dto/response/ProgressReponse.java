package com.school.elearning.dto.response;

import java.time.LocalDateTime;

public record ProgressReponse(
        Long id ,
        LocalDateTime createdAt ,
        boolean isCompleted ,
        String lessonTitle


) {
}
