package com.school.elearning.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(nullable = false)
    private String contentUrl;
    @Column(nullable = false)
    private String contentType;
    @Column(nullable = false)
    private int orderIndex;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;


}
