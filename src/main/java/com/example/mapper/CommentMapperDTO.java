package com.example.mapper;

import java.time.LocalDateTime;

public interface CommentMapperDTO {
    Integer getId();
    Integer getProfileId();
    String getProfileName();
    String getArticleId();
    String getArticleTitle();
    String getProfileSurname();
    LocalDateTime getCreatedDate();
    LocalDateTime getUpdateDate();

}
