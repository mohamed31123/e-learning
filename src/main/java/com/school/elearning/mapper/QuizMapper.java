package com.school.elearning.mapper;

import com.school.elearning.dto.request.AnswerRequest;
import com.school.elearning.dto.request.QuestionRequest;
import com.school.elearning.dto.request.QuizRequest;
import com.school.elearning.dto.response.AnswerResponse;
import com.school.elearning.dto.response.QuestionResponse;
import com.school.elearning.dto.response.QuizResponse;
import com.school.elearning.entity.Answer;
import com.school.elearning.entity.Question;
import com.school.elearning.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    @Mapping(source = "model.title", target = "modelTitle")
    QuizResponse toResponse(Quiz quiz);

    List<QuizResponse> toResponseList(List<Quiz> quizzes);

    QuestionResponse toResponse(Question question);

    AnswerResponse toResponse(Answer answer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "questions", ignore = true)
    Quiz toEntity(QuizRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    @Mapping(target = "answers", ignore = true)
    Question toEntity(QuestionRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", ignore = true)
    Answer toEntity(AnswerRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "questions", ignore = true)
    void updateQuiz(QuizRequest request, @MappingTarget Quiz quiz);
}
