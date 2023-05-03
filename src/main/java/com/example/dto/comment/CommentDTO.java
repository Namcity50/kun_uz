package com.example.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Integer id;
    private Integer profileId;
    private String articleId;
    private String content;
    private Integer replyId;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Boolean visible;
}
