package com.school.elearning.controller;

import com.school.elearning.dto.request.CertificateRequest;
import com.school.elearning.dto.response.CertificateResponse;
import com.school.elearning.service.CertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('LEARNER', 'STUDENT', 'ADMIN', 'INSTRUCTOR', 'TEACHER')")
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping("/generate")
    public ResponseEntity<CertificateResponse> generateCertificate(@Valid @RequestBody CertificateRequest request) {
        return new ResponseEntity<>(certificateService.generateCertificate(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateResponse> getCertificateById(@PathVariable Long id) {
        return ResponseEntity.ok(certificateService.findCertificateById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CertificateResponse>> getCertificatesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(certificateService.findCertificatesByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<CertificateResponse>> getAllCertificates() {
        return ResponseEntity.ok(certificateService.findAllCertificates());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }
}
