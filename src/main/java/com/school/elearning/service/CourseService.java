package com.school.elearning.service;

import com.school.elearning.dto.request.CourseRequest;
import com.school.elearning.dto.response.CourseResponse;
import com.school.elearning.entity.Course;
import com.school.elearning.entity.Model;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.CourseMapper;
import com.school.elearning.repository.CourseRepository;
import com.school.elearning.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModelRepository modelRepository;
    private final CourseMapper courseMapper;



    public CourseResponse createCourse(CourseRequest request) {
        Model model = modelRepository.findById(request.modelId())
                .orElseThrow(() -> new RessourceNotFoundException("Model not found with id: " + request.modelId()));

        Course course = courseMapper.toEntity(request);
        course.setModel(model);

        Course savedCourse = courseRepository.save(course);
        return courseMapper.toResponse(savedCourse);
    }

    public CourseResponse updateCourse(CourseRequest request, Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Course not found with id: " + id));

        if (course.getModel() == null || !course.getModel().getId().equals(request.modelId())) {
            Model model = modelRepository.findById(request.modelId())
                    .orElseThrow(() -> new RessourceNotFoundException("Model not found with id: " + request.modelId()));
            course.setModel(model);
        }

        courseMapper.updateCourse(request, course);
        return courseMapper.toResponse(course);
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Course not found with id: " + id));
        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    public CourseResponse findCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Course not found with id: " + id));
        return courseMapper.toResponse(course);
    }

    @Transactional(readOnly = true)
    public List<CourseResponse> findAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toResponse)
                .toList();
    }
}
