package com.school.elearning.dto.response;

import com.school.elearning.enums.LearningPathLevel;

public record LearningPathResponse(
        Long id,
        String title,
        String description,
        LearningPathLevel level,
        Integer durationHours,
        Boolean published,
        UserResponse createdBy
) {
}
