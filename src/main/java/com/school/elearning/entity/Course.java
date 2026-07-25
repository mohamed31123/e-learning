package com.school.elearning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , name = "title")
    private String title;
    @Column(name = "description" , nullable = false)
    private String description;
    @Column(unique = true, nullable = false)
    private int orderIndex ;



    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
