package com.school.elearning.service;

import com.school.elearning.dto.request.ModelRequest;
import com.school.elearning.dto.response.ModelResponse;
import com.school.elearning.entity.LearningPath;
import com.school.elearning.entity.Model;
import com.school.elearning.exception.RessourceNotFoundException;
import com.school.elearning.mapper.ModelMapper;
import com.school.elearning.repository.LearningPathRepository;
import com.school.elearning.repository.ModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModelService {

    private final ModelRepository modelRepository;
    private final LearningPathRepository learningPathRepository;
    private final ModelMapper modelMapper;

    public ModelService(ModelRepository modelRepository,
                        LearningPathRepository learningPathRepository,
                        ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.learningPathRepository = learningPathRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public ModelResponse getModelById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Model not found with id: " + id));
        return modelMapper.toResponse(model);
    }

    @Transactional(readOnly = true)
    public List<ModelResponse> findAll() {
        return modelRepository.findAll()
                .stream()
                .map(modelMapper::toResponse)
                .toList();
    }

    public ModelResponse createModel(ModelRequest request) {
        LearningPath learningPath = learningPathRepository.findById(request.learningPathId())
                .orElseThrow(() -> new RessourceNotFoundException("LearningPath not found with id: " + request.learningPathId()));

        Model model = modelMapper.toEntity(request);
        model.setLearningPath(learningPath);

        Model savedModel = modelRepository.save(model);
        return modelMapper.toResponse(savedModel);
    }

    public ModelResponse updateModel(Long id, ModelRequest request) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Model not found with id: " + id));

        if (model.getLearningPath() == null || !model.getLearningPath().getId().equals(request.learningPathId())) {
            LearningPath learningPath = learningPathRepository.findById(request.learningPathId())
                    .orElseThrow(() -> new RessourceNotFoundException("LearningPath not found with id: " + request.learningPathId()));
            model.setLearningPath(learningPath);
        }

        modelMapper.updateModel(request, model);
        return modelMapper.toResponse(model);
    }

    public void deleteModelById(Long id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Model not found with id: " + id));
        modelRepository.delete(model);
    }
}
