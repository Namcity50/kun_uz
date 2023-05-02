package com.example.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
        private Integer id;
        private String key;
        private String nameUz;
        private String nameRu;
        private String nameEng;
        private Boolean visible;
        private LocalDateTime createdDate;
}
