package com.school.elearning.controller;

import com.school.elearning.dto.request.QuizSubmissionRequest;
import com.school.elearning.dto.response.QuizAttemptResponse;
import com.school.elearning.service.QuizAttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-attempts")
@RequiredArgsConstructor
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    @PostMapping("/submit")
    public ResponseEntity<QuizAttemptResponse> submitAttempt(@Valid @RequestBody QuizSubmissionRequest request) {
        return new ResponseEntity<>(quizAttemptService.submitQuizAttempt(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizAttemptResponse> getAttemptById(@PathVariable Long id) {
        return ResponseEntity.ok(quizAttemptService.findAttemptById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuizAttemptResponse>> getAttemptsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(quizAttemptService.findAttemptsByUserId(userId));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizAttemptResponse>> getAttemptsByQuizId(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizAttemptService.findAttemptsByQuizId(quizId));
    }
}
