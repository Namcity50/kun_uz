package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Table(name = "emailHistory")
@Entity
public class EmailHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "message",length = 1000)
    private String message;
    @Column(name = "email")
    private String email;
    @Column(name = "created_date")
    private LocalDateTime createdData;
}
