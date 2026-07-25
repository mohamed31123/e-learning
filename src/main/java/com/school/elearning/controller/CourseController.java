package com.school.elearning.controller;


import com.school.elearning.dto.request.CourseRequest;
import com.school.elearning.dto.response.CourseResponse;
import com.school.elearning.service.CourseService;
import com.school.elearning.service.LearningPathService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponse createCourse(CourseRequest courseRequest) {
        return courseService.createCourse(courseRequest);
    }
    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable Long id,@Valid @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(courseRequest ,id) ;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
    @GetMapping("/{id}")
    public CourseResponse getCourse(@PathVariable Long id) {
        return courseService.findCourseById(id) ;

    }
}
