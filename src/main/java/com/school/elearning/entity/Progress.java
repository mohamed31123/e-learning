package com.school.elearning.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean completed ;
    @Column(nullable = false)
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "enrollmentId")
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "lessonId")
    private Lesson lesson;
}
