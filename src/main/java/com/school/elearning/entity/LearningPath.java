package com.school.elearning.entity;

import com.school.elearning.enums.LearningPathLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "learning_paths")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private LearningPathLevel level;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "published", nullable = false)
    private Boolean published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "learningPath")
    private List<Model> models;
}
