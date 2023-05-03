package com.example.dto.comment;

import com.example.dto.article.ArticleCommentResponseDTO;
import com.example.dto.profile.ProfileResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    private Integer id;
    private ProfileResponseDTO profile;
    private ArticleCommentResponseDTO article;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
