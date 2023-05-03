package com.example.dto.article;

import com.example.dto.attach.AttachResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ArticleResponseDTO {
    private String id;
    private String title;
    private String description;
    private AttachResponseDTO image;

}
