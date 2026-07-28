package com.school.elearning.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuizRequest(
        @NotBlank(message = "Quiz title is required")
        String title,

        @Min(value = 0, message = "Passing score must be non-negative")
        Double passingScore,

        @NotNull(message = "Model ID is required")
        Long modelId,

        List<QuestionRequest> questions
) {
}
