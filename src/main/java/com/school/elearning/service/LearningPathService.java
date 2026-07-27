package com.school.elearning.service;

import com.school.elearning.dto.request.LearningPathRequest;
import com.school.elearning.dto.response.LearningPathResponse;
import com.school.elearning.entity.LearningPath;
import com.school.elearning.entity.User;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.LearningPathMapper;
import com.school.elearning.repository.LearningPathRepository;
import com.school.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LearningPathService {

    private final LearningPathRepository learningPathRepository;
    private final UserRepository userRepository;
    private final LearningPathMapper learningPathMapper;



    public LearningPathResponse createLearningPath(LearningPathRequest request) {
        User creator = userRepository.findById(request.createdById())
                .orElseThrow(() -> new RessourceNotFoundException("User (creator) not found with id: " + request.createdById()));

        LearningPath learningPath = learningPathMapper.toEntity(request);
        learningPath.setCreatedBy(creator);
        if (learningPath.getPublished() == null) {
            learningPath.setPublished(false);
        }

        LearningPath savedLearningPath = learningPathRepository.save(learningPath);
        return learningPathMapper.toResponse(savedLearningPath);
    }

    @Transactional(readOnly = true)
    public LearningPathResponse getLearningPathById(Long id) {
        LearningPath learningPath = learningPathRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("LearningPath not found with id: " + id));
        return learningPathMapper.toResponse(learningPath);
    }

    @Transactional(readOnly = true)
    public List<LearningPathResponse> getAllLearningPaths() {
        return learningPathRepository.findAll()
                .stream()
                .map(learningPathMapper::toResponse)
                .toList();
    }

    public LearningPathResponse updateLearningPath(Long id, LearningPathRequest request) {
        LearningPath learningPath = learningPathRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("LearningPath not found with id: " + id));

        if (!learningPath.getCreatedBy().getId().equals(request.createdById())) {
            User creator = userRepository.findById(request.createdById())
                    .orElseThrow(() -> new RessourceNotFoundException("User (creator) not found with id: " + request.createdById()));
            learningPath.setCreatedBy(creator);
        }

        learningPathMapper.updateLearningPathFromDto(request, learningPath);
        return learningPathMapper.toResponse(learningPath);
    }

    public void deleteLearningPath(Long id) {
        if (!learningPathRepository.existsById(id)) {
            throw new RessourceNotFoundException("LearningPath not found with id: " + id);
        }
        learningPathRepository.deleteById(id);
    }
}
