package com.school.elearning.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Content URL is required")
        String contentUrl,

        @NotBlank(message = "Content type is required")
        String contentType,

        @Min(value = 0, message = "Order index cannot be negative")
        int orderIndex,

        @NotNull(message = "Course ID is required")
        Long courseId
) {
}
