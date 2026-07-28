package com.school.elearning.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean isCompleted ;
    @Column(nullable = false)
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "enrollmentId")
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "lessonId")
    private Lesson lesson;
}
