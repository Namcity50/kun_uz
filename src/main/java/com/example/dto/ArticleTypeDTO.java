package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ArticleTypeDTO {
    private Integer id;
    private String key;
    private String nameUz;
    private String nameRu;
    private String nameEng;
    private Boolean visible;
    private LocalDateTime createdDate;
}
