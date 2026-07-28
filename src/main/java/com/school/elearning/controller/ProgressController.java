package com.school.elearning.controller;


import com.school.elearning.dto.request.ProgressRequest;
import com.school.elearning.entity.Progress;
import com.school.elearning.service.ProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
@RequiredArgsConstructor
public class ProgressController {
    private final ProgressService progressService;

    @PostMapping
    public ResponseEntity<?> saveProgress( @Valid @RequestBody ProgressRequest progressRequest){
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(progressService.createProgress(progressRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProgress(@PathVariable Long id){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(progressService.getProgressById(id));
    }
    @GetMapping
    public ResponseEntity<?> getAllProgress(){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(progressService.getAllProgresses());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProgress(@PathVariable Long id , @Valid @RequestBody ProgressRequest progressRequest ){
        return ResponseEntity.
                status(HttpStatus.OK).
                body(progressService.updateProgressById(id ,progressRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable Long id){

                progressService.deleteProgressById(id);
    }
}
