package com.crud.user_with_exception.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Cannot be null
    @Column(nullable = false)
    private String name;

    //Cannot be null and No duplicates allowed
    @Column(nullable = false, unique = true)
    private String email;
}
