package com.school.elearning.entity;


import com.school.elearning.enums.EnrollmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "enrolled time is required")
    private LocalDateTime enrolledAt;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private EnrollmentStatus status;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
