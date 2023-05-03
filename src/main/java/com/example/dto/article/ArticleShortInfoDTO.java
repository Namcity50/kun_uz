package com.example.dto.article;

import com.example.dto.articleType.ArticleTypeDTO;
import com.example.dto.attach.AttachResponseDTO;
import com.example.dto.category.CategoryResponseDTO;
import com.example.dto.region.RegionDTO;
import com.example.dto.tag.TagDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleShortInfoDTO {
    private String id;
    private String title;
    private String description;
    private AttachResponseDTO image;
    private LocalDate publishedDate;
    private TagDTO tag;
    private ArticleTypeDTO typeDTO;
    private CategoryResponseDTO category;
    private RegionDTO region;

    public ArticleShortInfoDTO() {
    }

    public ArticleShortInfoDTO(String id, String title, String description,
                               LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
    }
}
