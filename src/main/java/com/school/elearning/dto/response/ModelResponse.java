package com.school.elearning.dto.response;

public record ModelResponse(
        Long id,
        String title,
        int orderIndex,
        String learningPathTitle
) {
}
