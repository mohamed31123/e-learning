package com.school.elearning.service;

import com.school.elearning.dto.request.CertificateRequest;
import com.school.elearning.dto.response.CertificateResponse;
import com.school.elearning.entity.Certificate;
import com.school.elearning.entity.LearningPath;
import com.school.elearning.entity.User;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.CertificateMapper;
import com.school.elearning.repository.CertificateRepository;
import com.school.elearning.repository.LearningPathRepository;
import com.school.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final UserRepository userRepository;
    private final LearningPathRepository learningPathRepository;
    private final CertificateMapper certificateMapper;

    public CertificateResponse generateCertificate(CertificateRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RessourceNotFoundException("User not found with id: " + request.userId()));

        LearningPath learningPath = learningPathRepository.findById(request.learningPathId())
                .orElseThrow(() -> new RessourceNotFoundException("LearningPath not found with id: " + request.learningPathId()));

        return certificateRepository.findByUserIdAndLearningPathId(user.getId(), learningPath.getId())
                .map(certificateMapper::toResponse)
                .orElseGet(() -> {
                    String certificateUrl = "/api/certificates/download/" + UUID.randomUUID();
                    Certificate certificate = Certificate.builder()
                            .user(user)
                            .learningPath(learningPath)
                            .certificateUrl(certificateUrl)
                            .build();

                    Certificate saved = certificateRepository.save(certificate);
                    return certificateMapper.toResponse(saved);
                });
    }

    @Transactional(readOnly = true)
    public CertificateResponse findCertificateById(Long id) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Certificate not found with id: " + id));
        return certificateMapper.toResponse(certificate);
    }

    @Transactional(readOnly = true)
    public List<CertificateResponse> findCertificatesByUserId(Long userId) {
        return certificateRepository.findByUserId(userId)
                .stream()
                .map(certificateMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CertificateResponse> findAllCertificates() {
        return certificateRepository.findAll()
                .stream()
                .map(certificateMapper::toResponse)
                .toList();
    }

    public void deleteCertificate(Long id) {
        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Certificate not found with id: " + id));
        certificateRepository.delete(certificate);
    }
}
