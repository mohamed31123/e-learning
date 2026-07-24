package com.school.elearning.controller;

import com.school.elearning.dto.request.LearningPathRequest;
import com.school.elearning.dto.response.LearningPathResponse;
import com.school.elearning.service.LearningPathService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/learning-paths")
public class LearningPathController {

    private final LearningPathService learningPathService;

    public LearningPathController(LearningPathService learningPathService) {
        this.learningPathService = learningPathService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LearningPathResponse create(@Valid @RequestBody LearningPathRequest request) {
        return learningPathService.createLearningPath(request);
    }

    @GetMapping
    public List<LearningPathResponse> getAll() {
        return learningPathService.getAllLearningPaths();
    }

    @GetMapping("/{id}")
    public LearningPathResponse getById(@PathVariable Long id) {
        return learningPathService.getLearningPathById(id);
    }

    @PutMapping("/{id}")
    public LearningPathResponse update(@PathVariable Long id, @Valid @RequestBody LearningPathRequest request) {
        return learningPathService.updateLearningPath(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        learningPathService.deleteLearningPath(id);
    }
}
