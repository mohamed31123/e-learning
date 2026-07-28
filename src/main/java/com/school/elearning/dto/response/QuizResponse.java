package com.school.elearning.dto.response;

import java.util.List;

public record QuizResponse(
        Long id,
        String title,
        Double passingScore,
        String modelTitle,
        List<QuestionResponse> questions
) {
}
