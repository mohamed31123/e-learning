package com.school.elearning.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
        @NotBlank(message = "title is required")
        String title ,
        @NotBlank(message = "description is required")
        String description ,
        @NotNull(message = "order index is required")
        int orderIndex

) {
}
