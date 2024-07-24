package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;
    private String grade;

    @Column(name = "student_id_image_url")
    private String studentIdImageUrl;

    @Column(name = "admin")
    private String admin;
}
