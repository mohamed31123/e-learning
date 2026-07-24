package com.school.elearning.dto.request;

import com.school.elearning.enums.LearningPathLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LearningPathRequest(
        @NotBlank(message = "Title is required")
        String title,

        String description,

        @NotNull(message = "Level is required")
        LearningPathLevel level,

        @Min(value = 1, message = "Duration must be at least 1 hour")
        Integer durationHours,

        Boolean published,

        @NotNull(message = "Creator user ID is required")
        Long createdById
) {
}
