package com.school.elearning.dto.response;

import com.school.elearning.entity.Model;
import com.school.elearning.enums.LearningPathLevel;

import java.util.List;

public record LearningPathResponse(
        Long id,
        String title,
        String description,
        LearningPathLevel level,
        Integer durationHours,
        Boolean published,
        UserResponse createdBy  ,
        List<Model> models
) {
}
