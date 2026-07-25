package com.school.elearning.dto.response;

public record CourseResponse(
        Long id ,
        String title ,
        String description ,
        Long  modelId  ,
        String modelTitle
) {
}
