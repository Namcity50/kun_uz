package com.example.mapper;

import java.time.LocalDate;

public interface ArticleShortInfoMapper {
    String getId();

    String getTitle();

    String getDescription();

    String getAttachId();

    Integer getTagId();

    Integer getType();

    Integer getRegId();
    Integer getCatId();

    Long getViewCount();

    LocalDate getPublished_date();
}
