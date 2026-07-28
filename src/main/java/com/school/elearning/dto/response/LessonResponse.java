package com.school.elearning.dto.response;

import com.school.elearning.entity.Progress;

import java.util.List;

public record LessonResponse(
        Long id ,
        String title ,
        String contentUrl ,
        String contentType ,
        Long courseId ,
        List<Progress> progresses
) {
}
