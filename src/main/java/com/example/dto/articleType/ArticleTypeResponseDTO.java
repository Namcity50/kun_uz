package com.example.dto.articleType;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeResponseDTO {
    private Integer id;
    private String key;
    private String lang;
}
