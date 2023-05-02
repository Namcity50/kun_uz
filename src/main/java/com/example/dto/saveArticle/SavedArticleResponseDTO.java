package com.example.dto.saveArticle;

import com.example.dto.article.ArticleResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavedArticleResponseDTO {
    private Integer id;
    private ArticleResponseDTO article;
}
