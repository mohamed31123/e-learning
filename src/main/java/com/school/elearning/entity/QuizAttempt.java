package com.school.elearning.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_attempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "passed", nullable = false)
    private Boolean passed;

    @Column(name = "attempted_at", nullable = false)
    private LocalDateTime attemptedAt;

    @PrePersist
    public void prePersist() {
        if (this.attemptedAt == null) {
            this.attemptedAt = LocalDateTime.now();
        }
    }
}
