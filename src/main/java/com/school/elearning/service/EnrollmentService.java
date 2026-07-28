package com.school.elearning.service;

import com.school.elearning.dto.request.EnrollmentRequest;
import com.school.elearning.dto.response.EnrollmentResponse;
import com.school.elearning.entity.Enrollment;
import com.school.elearning.entity.User;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.EnrollmentMapper;
import com.school.elearning.repository.EnrollmentRepository;
import com.school.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final UserRepository userRepository;



    public EnrollmentResponse createEnrollment(EnrollmentRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RessourceNotFoundException("User not found with id :" + request.userId()));
        Enrollment enrollment = enrollmentMapper.toEnrollment(request);
        enrollment.setUser(user);
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);

    }

    public void deleteEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Enrollment not found with id :" + id));
        enrollmentRepository.delete(enrollment);
    }

    public EnrollmentResponse updateEnrollment(Long id, EnrollmentRequest request) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Enrollment not found with id :" + id));
        enrollmentMapper.updateEnrollment(request ,enrollment);
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }
    public EnrollmentResponse findEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Enrollment not found with id :" + id));
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    public List<EnrollmentResponse> findAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toEnrollmentResponse)
                .toList();
    }
}
