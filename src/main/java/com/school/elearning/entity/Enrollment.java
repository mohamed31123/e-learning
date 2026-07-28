package com.school.elearning.entity;


import com.school.elearning.enums.EnrollmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "enrollment")
    private List<Progress> progress;



}
