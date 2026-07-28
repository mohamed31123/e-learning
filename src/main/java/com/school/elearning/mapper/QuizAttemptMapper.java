package com.school.elearning.mapper;

import com.school.elearning.dto.response.QuizAttemptResponse;
import com.school.elearning.entity.QuizAttempt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizAttemptMapper {

    @Mapping(source = "quiz.id", target = "quizId")
    @Mapping(source = "quiz.title", target = "quizTitle")
    @Mapping(source = "user.id", target = "userId")
    QuizAttemptResponse toResponse(QuizAttempt attempt);

    List<QuizAttemptResponse> toResponseList(List<QuizAttempt> attempts);
}
