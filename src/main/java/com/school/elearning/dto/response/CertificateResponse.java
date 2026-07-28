package com.school.elearning.dto.response;

import java.time.LocalDateTime;

public record CertificateResponse(
        Long id,
        Long userId,
        String userFullName,
        Long learningPathId,
        String learningPathTitle,
        LocalDateTime issuedAt,
        String certificateUrl
) {
}
