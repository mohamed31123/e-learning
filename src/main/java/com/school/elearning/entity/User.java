package com.school.elearning.entity;


import com.school.elearning.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName ;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
