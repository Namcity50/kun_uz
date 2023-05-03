package com.example.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentCustomDTO {
    private Integer profileId;
    private String articleId;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private Integer replyId;
}
