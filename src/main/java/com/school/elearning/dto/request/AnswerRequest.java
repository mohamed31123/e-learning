package com.school.elearning.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequest(
        @NotBlank(message = "Answer text is required")
        String text,

        @NotNull(message = "isCorrect flag is required")
        Boolean isCorrect
) {
}
