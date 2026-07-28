package com.school.elearning.service;

import com.school.elearning.dto.request.QuizSubmissionRequest;
import com.school.elearning.dto.response.QuizAttemptResponse;
import com.school.elearning.entity.*;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.QuizAttemptMapper;
import com.school.elearning.repository.QuizAttemptRepository;
import com.school.elearning.repository.QuizRepository;
import com.school.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizAttemptMapper quizAttemptMapper;

    public QuizAttemptResponse submitQuizAttempt(QuizSubmissionRequest request) {
        Quiz quiz = quizRepository.findById(request.quizId())
                .orElseThrow(() -> new RessourceNotFoundException("Quiz not found with id: " + request.quizId()));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RessourceNotFoundException("User not found with id: " + request.userId()));

        Map<Long, List<Long>> userAnswers = request.selectedAnswers() != null ?
                request.selectedAnswers() : Collections.emptyMap();

        int totalQuestions = quiz.getQuestions().size();
        int correctQuestionsCount = 0;

        for (Question question : quiz.getQuestions()) {
            Set<Long> correctAnswersSet = question.getAnswers().stream()
                    .filter(Answer::getIsCorrect)
                    .map(Answer::getId)
                    .collect(Collectors.toSet());

            List<Long> selectedForQuestion = userAnswers.getOrDefault(question.getId(), Collections.emptyList());
            Set<Long> selectedSet = Set.copyOf(selectedForQuestion);

            if (!correctAnswersSet.isEmpty() && correctAnswersSet.equals(selectedSet)) {
                correctQuestionsCount++;
            }
        }

        double scorePercentage = totalQuestions > 0 ? ((double) correctQuestionsCount / totalQuestions) * 100.0 : 0.0;
        boolean passed = scorePercentage >= quiz.getPassingScore();

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .user(user)
                .score(scorePercentage)
                .passed(passed)
                .build();

        QuizAttempt savedAttempt = quizAttemptRepository.save(attempt);
        return quizAttemptMapper.toResponse(savedAttempt);
    }

    @Transactional(readOnly = true)
    public QuizAttemptResponse findAttemptById(Long id) {
        QuizAttempt attempt = quizAttemptRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("QuizAttempt not found with id: " + id));
        return quizAttemptMapper.toResponse(attempt);
    }

    @Transactional(readOnly = true)
    public List<QuizAttemptResponse> findAttemptsByUserId(Long userId) {
        return quizAttemptRepository.findByUserId(userId)
                .stream()
                .map(quizAttemptMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<QuizAttemptResponse> findAttemptsByQuizId(Long quizId) {
        return quizAttemptRepository.findByQuizId(quizId)
                .stream()
                .map(quizAttemptMapper::toResponse)
                .toList();
    }
}
