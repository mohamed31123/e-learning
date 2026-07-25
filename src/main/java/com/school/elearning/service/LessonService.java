package com.school.elearning.service;

import com.school.elearning.dto.request.LessonRequest;
import com.school.elearning.dto.response.LessonResponse;
import com.school.elearning.entity.Course;
import com.school.elearning.entity.Lesson;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.LessonMapper;
import com.school.elearning.repository.CourseRepository;
import com.school.elearning.repository.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.lessonMapper = lessonMapper;
    }

    public LessonResponse createLesson(LessonRequest lessonRequest) {
        Course course = courseRepository.findById(lessonRequest.courseId())
                .orElseThrow(() -> new RessourceNotFoundException("Course not found with id: " + lessonRequest.courseId()));
        Lesson lesson = lessonMapper.toEntity(lessonRequest);
        lesson.setCourse(course);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toResponse(savedLesson);
    }

    public LessonResponse updateLesson(LessonRequest lessonRequest, Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Lesson not found with id: " + id));

        if (lesson.getCourse() == null || !lesson.getCourse().getId().equals(lessonRequest.courseId())) {
            Course course = courseRepository.findById(lessonRequest.courseId())
                    .orElseThrow(() -> new RessourceNotFoundException("Course not found with id: " + lessonRequest.courseId()));
            lesson.setCourse(course);
        }

        lessonMapper.updateLesson(lessonRequest, lesson);
        return lessonMapper.toResponse(lesson);
    }

    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Lesson not found with id: " + id));
        lessonRepository.delete(lesson);
    }

    @Transactional(readOnly = true)
    public List<LessonResponse> findAllLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Lesson not found with id: " + id));
        return lessonMapper.toResponse(lesson);
    }
}
