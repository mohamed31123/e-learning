package com.school.elearning.controller;

import com.school.elearning.dto.request.ModelRequest;
import com.school.elearning.dto.response.ModelResponse;
import com.school.elearning.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/models")
public class ModelController {

    private final ModelService modelService;

    @PostMapping
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    public ResponseEntity<ModelResponse> create(@Valid @RequestBody ModelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelService.createModel(request));
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModelResponse> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.getModelById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    public ResponseEntity<ModelResponse> update(@PathVariable Long id, @Valid @RequestBody ModelRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.updateModel(id , request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('INSTRUCTOR', 'TEACHER', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        modelService.deleteModelById(id);
    }
}
