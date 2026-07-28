package com.school.elearning.dto.request;

import jakarta.validation.constraints.NotNull;

public record CertificateRequest(
        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Learning path ID is required")
        Long learningPathId
) {
}
