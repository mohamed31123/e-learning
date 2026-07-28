package com.school.elearning.service;

import com.school.elearning.dto.request.QuizRequest;
import com.school.elearning.dto.response.QuizResponse;
import com.school.elearning.entity.Answer;
import com.school.elearning.entity.Model;
import com.school.elearning.entity.Question;
import com.school.elearning.entity.Quiz;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.QuizMapper;
import com.school.elearning.repository.ModelRepository;
import com.school.elearning.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final ModelRepository modelRepository;
    private final QuizMapper quizMapper;

    public QuizResponse createQuiz(QuizRequest request) {
        Model model = modelRepository.findById(request.modelId())
                .orElseThrow(() -> new RessourceNotFoundException("Model not found with id: " + request.modelId()));

        Quiz quiz = quizMapper.toEntity(request);
        quiz.setModel(model);

        if (request.questions() != null) {
            List<Question> questions = new ArrayList<>();
            request.questions().forEach(qReq -> {
                Question question = quizMapper.toEntity(qReq);
                question.setQuiz(quiz);

                if (qReq.answers() != null) {
                    List<Answer> answers = new ArrayList<>();
                    qReq.answers().forEach(aReq -> {
                        Answer answer = quizMapper.toEntity(aReq);
                        answer.setQuestion(question);
                        answers.add(answer);
                    });
                    question.setAnswers(answers);
                }
                questions.add(question);
            });
            quiz.setQuestions(questions);
        }

        Quiz savedQuiz = quizRepository.save(quiz);
        return quizMapper.toResponse(savedQuiz);
    }

    @Transactional(readOnly = true)
    public QuizResponse findQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Quiz not found with id: " + id));
        return quizMapper.toResponse(quiz);
    }

    @Transactional(readOnly = true)
    public List<QuizResponse> findQuizzesByModelId(Long modelId) {
        return quizRepository.findByModelId(modelId)
                .stream()
                .map(quizMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<QuizResponse> findAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(quizMapper::toResponse)
                .toList();
    }

    public QuizResponse updateQuiz(QuizRequest request, Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Quiz not found with id: " + id));

        if (quiz.getModel() == null || !quiz.getModel().getId().equals(request.modelId())) {
            Model model = modelRepository.findById(request.modelId())
                    .orElseThrow(() -> new RessourceNotFoundException("Model not found with id: " + request.modelId()));
            quiz.setModel(model);
        }

        quizMapper.updateQuiz(request, quiz);
        return quizMapper.toResponse(quiz);
    }

    public void deleteQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Quiz not found with id: " + id));
        quizRepository.delete(quiz);
    }
}
