package com.school.elearning.service;

import com.school.elearning.dto.request.CourseRequest;
import com.school.elearning.dto.response.CourseResponse;
import com.school.elearning.entity.Course;
import com.school.elearning.mapper.CourseMapper;
import com.school.elearning.repository.CourseRepository;
import com.school.elearning.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelRepository modelRepository;
    private final CourseMapper  courseMapper;

    public CourseService(CourseRepository courseRepository, ModelRepository modelRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.modelRepository = modelRepository;
        this.courseMapper = courseMapper;
    }
    public CourseResponse createCourse(CourseRequest request) {
        Course course = courseMapper.toEntity(request);
        courseRepository.save(course);
        return courseMapper.toResponse(course);
    }

    public CourseResponse updateCourse(CourseRequest request , Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow();
        courseMapper.updateCourse(request,course);
        courseRepository.save(course);
        return courseMapper.toResponse(course);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow();
    }

    public CourseResponse findCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow();
        return courseMapper.toResponse(course);
    }


}
