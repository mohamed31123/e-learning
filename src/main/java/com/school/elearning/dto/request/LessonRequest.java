package com.school.elearning.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonRequest(
        @NotBlank(message = "title is required")
        String title ,
        @NotBlank(message = "content url is required")
        String contentUrl ,
        @NotBlank(message = "content type is required")
        String contentType ,
        @NotNull(message = "order index is required")
        int orderIndex ,
        Long courseId

) {

}
