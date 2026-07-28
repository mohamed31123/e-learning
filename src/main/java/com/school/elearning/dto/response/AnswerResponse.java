package com.school.elearning.dto.response;

public record AnswerResponse(
        Long id,
        String text,
        Boolean isCorrect
) {
}
