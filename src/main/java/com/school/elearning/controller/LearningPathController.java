package com.school.elearning.controller;

import com.school.elearning.dto.request.LearningPathRequest;
import com.school.elearning.dto.response.LearningPathResponse;
import com.school.elearning.service.LearningPathService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/learning-paths")
public class LearningPathController {

    private final LearningPathService learningPathService;



    @PostMapping
    public ResponseEntity<LearningPathResponse> create(@Valid @RequestBody LearningPathRequest request) {
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(learningPathService.createLearningPath(request));
    }

    @GetMapping
    public ResponseEntity<List<LearningPathResponse>> getAll() {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(learningPathService.getAllLearningPaths());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningPathResponse> getById(@PathVariable Long id) {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(learningPathService.getLearningPathById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningPathResponse> update(@PathVariable Long id, @Valid @RequestBody LearningPathRequest request) {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(learningPathService.updateLearningPath(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        learningPathService.deleteLearningPath(id);
    }
}
