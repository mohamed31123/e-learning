package com.school.elearning.dto.response;

import java.time.LocalDateTime;

public record QuizAttemptResponse(
        Long id,
        Long quizId,
        String quizTitle,
        Long userId,
        Double score,
        Boolean passed,
        LocalDateTime attemptedAt
) {
}
