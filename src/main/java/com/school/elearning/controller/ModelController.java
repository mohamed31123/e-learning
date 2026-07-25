package com.school.elearning.controller;

import com.school.elearning.dto.request.ModelRequest;
import com.school.elearning.dto.response.ModelResponse;
import com.school.elearning.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModelResponse create(@Valid @RequestBody ModelRequest request) {
        return modelService.createModel(request);
    }

    @GetMapping
    public List<ModelResponse> getAll() {
        return modelService.findAll();
    }

    @GetMapping("/{id}")
    public ModelResponse getById(@PathVariable Long id) {
        return modelService.getModelById(id);
    }

    @PutMapping("/{id}")
    public ModelResponse update(@PathVariable Long id, @Valid @RequestBody ModelRequest request) {
        return modelService.updateModel(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        modelService.deleteModelById(id);
    }
}
