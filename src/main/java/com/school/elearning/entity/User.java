package com.school.elearning.entity;


import com.school.elearning.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
    @Column(name = "fullName" , nullable = false)
    private String fullName ;
    @Column(name = "email" ,  nullable = false , unique = true)
    @Email(message = "Email not supported")
    private String email;
    @Size(min = 6 , max = 30)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
