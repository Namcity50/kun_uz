package com.example.dto.article;

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
    private String id; //(uuid)
    private String title;
    private String description;
    private String content;
    private Long sharedCount;
    private RegionResponseDTO region; //(key, name)
    private CategoryResponseDTO category; //(key, name)
    private LocalDateTime publishedDate;
    private Long viewCount;
    private Long likeCount;
    //tagList(name)
}
