package com.school.elearning.dto.response;

import com.school.elearning.enums.QuestionType;

import java.util.List;

public record QuestionResponse(
        Long id,
        String text,
        QuestionType type,
        List<AnswerResponse> answers
) {
}
