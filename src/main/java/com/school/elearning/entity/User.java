package com.school.elearning.entity;


import com.school.elearning.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullName" , nullable = false)
    private String fullName ;
    @Column(name = "email" ,  nullable = false , unique = true)
    private String email;
    @Column(name = "password" ,  nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role" ,  nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "createdBy")
    private List<LearningPath> learningPaths;
}
