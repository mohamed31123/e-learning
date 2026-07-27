package com.school.elearning.controller;


import com.school.elearning.dto.request.EnrollmentRequest;
import com.school.elearning.dto.response.EnrollmentResponse;
import com.school.elearning.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResponse createEnrollment(@Valid @RequestBody EnrollmentRequest request){
        return enrollmentService.createEnrollment(request);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResponse updateEnrollment( @PathVariable Long id ,@Valid @RequestBody EnrollmentRequest request){
        return enrollmentService.updateEnrollment(id ,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEnrollment(@PathVariable Long id){
        enrollmentService.deleteEnrollment(id);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EnrollmentResponse getEnrollment(@PathVariable Long id){
         return enrollmentService.findEnrollmentById(id);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EnrollmentResponse> getEnrollments(){
        return  enrollmentService.findAllEnrollments();
    }

}
