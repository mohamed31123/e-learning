package com.school.elearning.dto.response;

import com.school.elearning.entity.Course;

import java.util.List;

public record ModelResponse(
        Long id,
        String title,
        int orderIndex,
        String learningPathTitle ,
        List<Course> courseList
) {
}
