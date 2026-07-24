package com.school.elearning.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModelRequest(
        @NotBlank(message = "Title is required")
        String title,

        @Min(value = 0, message = "Order index cannot be negative")
        int orderIndex,

        @NotNull(message = "Learning path ID is required")
        Long learningPathId
) {
}
