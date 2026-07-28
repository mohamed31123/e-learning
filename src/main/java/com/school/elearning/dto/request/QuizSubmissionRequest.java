package com.school.elearning.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record QuizSubmissionRequest(
        @NotNull(message = "Quiz ID is required")
        Long quizId,

        @NotNull(message = "User ID is required")
        Long userId,

        Map<Long, List<Long>> selectedAnswers
) {
}
