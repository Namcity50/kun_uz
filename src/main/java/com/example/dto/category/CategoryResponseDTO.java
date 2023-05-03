package com.example.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDTO {
    private Integer id;
    private String key;
    private String lang;

    public CategoryResponseDTO(Integer id, String lang) {
        this.id = id;

        this.lang = lang;
    }
}
