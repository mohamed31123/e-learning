package com.school.elearning.controller;

import com.school.elearning.dto.request.LessonRequest;
import com.school.elearning.dto.response.LessonResponse;
import com.school.elearning.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lessons")
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LessonResponse> createLesson(@Valid @RequestBody LessonRequest lessonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.createLesson(lessonRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    public ResponseEntity<LessonResponse> updateLesson(@Valid @RequestBody LessonRequest lessonRequest, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.updateLesson(lessonRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> findLessonById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.getLessonById(id));
    }

    @GetMapping
    public ResponseEntity<List<LessonResponse>> findLessons() {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllLessons());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
