package com.school.elearning.controller;

import com.school.elearning.dto.request.CourseRequest;
import com.school.elearning.dto.response.CourseResponse;
import com.school.elearning.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(courseService.createCourse(courseRequest));
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.findAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable Long id) {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(courseService.findCourseById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequest courseRequest) {
        return  ResponseEntity.
                status(HttpStatus.OK)
                .body(courseService.updateCourse(courseRequest, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
       courseService.deleteCourse(id);
       return ResponseEntity.noContent().build();

    }
}
