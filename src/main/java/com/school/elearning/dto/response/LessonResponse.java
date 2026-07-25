package com.school.elearning.dto.response;

public record LessonResponse(
        Long id ,
        String title ,
        String contentUrl ,
        String contentType ,
        Long courseId
) {
}
