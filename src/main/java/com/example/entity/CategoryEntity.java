package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Table(name = "category")
@Entity
public class CategoryEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "key")
        private String key;
        @Column(name = "name_uz")
        private String nameUz;
        @Column(name = "name_ru")
        private String nameRu;
        @Column(name = "name_eng")
        private String nameEng;
        @Column(name = "visible")
        private Boolean visible = Boolean.TRUE;
        @Column(name = "created_date")
        private LocalDateTime createdDate = LocalDateTime.now();
    }
