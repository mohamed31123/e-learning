package com.school.elearning.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @Min(value = 0, message = "Order index cannot be negative")
        int orderIndex,

        @NotNull(message = "Model ID is required")
        Long modelId
) {
}
