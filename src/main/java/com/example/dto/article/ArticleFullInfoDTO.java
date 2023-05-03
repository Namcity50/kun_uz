package com.example.dto.article;

import com.example.dto.articleType.ArticleTypeResponseDTO;
import com.example.dto.attach.AttachDTO;
import com.example.dto.category.CategoryResponseDTO;
import com.example.dto.region.RegionResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFullInfoDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private Long sharedCount ;
    private RegionResponseDTO region;
    private CategoryResponseDTO category;
    private ArticleTypeResponseDTO articleType;
    private LocalDateTime publishedDate;
    private Long viewCount;
    private Long likeCount;
    private AttachDTO image;
}
