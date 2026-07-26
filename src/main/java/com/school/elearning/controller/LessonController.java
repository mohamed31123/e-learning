package com.school.elearning.controller;

import com.school.elearning.dto.request.LessonRequest;
import com.school.elearning.dto.response.LessonResponse;
import com.school.elearning.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonResponse createLesson(@Valid @RequestBody LessonRequest lessonRequest) {
        return lessonService.createLesson(lessonRequest);
    }

    @PutMapping("/{id}")
    public LessonResponse updateLesson(@Valid @RequestBody LessonRequest lessonRequest, @PathVariable Long id) {
        return lessonService.updateLesson(lessonRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    @GetMapping("/{id}")
    public LessonResponse findLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @GetMapping
    public List<LessonResponse> findLessons() {
        return lessonService.findAllLessons();
    }
}
