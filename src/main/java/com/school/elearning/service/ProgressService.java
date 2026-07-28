package com.school.elearning.service;


import com.school.elearning.dto.request.ProgressRequest;
import com.school.elearning.dto.response.ProgressReponse;
import com.school.elearning.entity.Enrollment;
import com.school.elearning.entity.Lesson;
import com.school.elearning.entity.Progress;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.ProgressMapper;
import com.school.elearning.repository.EnrollmentRepository;
import com.school.elearning.repository.LessonRepository;
import com.school.elearning.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LessonRepository lessonRepository;
    private final ProgressMapper progressMapper;

    public ProgressReponse createProgress(ProgressRequest progressRequest) {
        Enrollment enrollment = enrollmentRepository.findById(progressRequest.enrollmentId())
                .orElseThrow(() ->
                        new RessourceNotFoundException("enrollment not found with id : "
                                + progressRequest.enrollmentId()));
        Lesson lesson = lessonRepository.findById(progressRequest.lessonId()).
                orElseThrow(() ->
                        new RessourceNotFoundException("lesson not found with id" +
                                " : " + progressRequest.lessonId()));

        Progress progress = new Progress();
        progress.setEnrollment(enrollment);
        progress.setLesson(lesson);

        Progress savedProgress = progressRepository.save(progress);
        return progressMapper.toProgressReponse(savedProgress);
    }

    public ProgressReponse getProgressById(Long id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() ->
                        new RessourceNotFoundException("progress not found with id : " + id));
        return progressMapper.toProgressReponse(progress);
    }

    public List<ProgressReponse> getAllProgresses() {
        return progressRepository.findAll()
                .stream()
                .map(progressMapper::toProgressReponse)
                .toList();
    }

    public ProgressReponse updateProgressById(Long id, ProgressRequest progressRequest) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() ->
                        new RessourceNotFoundException("progress not found with id : " + id));
        progressMapper.updateProgress(progressRequest, progress);
        progressRepository.save(progress);
        return progressMapper.toProgressReponse(progress);

    }

    public void deleteProgressById(Long id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("progress not found with id : " + id));
        progressRepository.deleteById(id);
    }
}
