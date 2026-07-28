package com.school.elearning.controller;


import com.school.elearning.dto.request.EnrollmentRequest;
import com.school.elearning.dto.response.EnrollmentResponse;
import com.school.elearning.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
@PreAuthorize("hasAnyRole('LEARNER', 'STUDENT', 'ADMIN', 'INSTRUCTOR', 'TEACHER')")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EnrollmentResponse> createEnrollment(@Valid @RequestBody EnrollmentRequest request){
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(enrollmentService.createEnrollment(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> updateEnrollment( @PathVariable Long id ,@Valid @RequestBody EnrollmentRequest request){
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(enrollmentService.updateEnrollment(id,request));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnrollment(@PathVariable Long id){
        enrollmentService.deleteEnrollment(id);
    }
    @GetMapping("/{id}")

    public ResponseEntity<EnrollmentResponse> getEnrollment(@PathVariable Long id){
         return ResponseEntity.
                 status(HttpStatus.OK).
                 body(enrollmentService.findEnrollmentById(id));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EnrollmentResponse>> getEnrollments(){
          return ResponseEntity.
                  status(HttpStatus.OK)
                  .body(enrollmentService.findAllEnrollments());
    }

}
