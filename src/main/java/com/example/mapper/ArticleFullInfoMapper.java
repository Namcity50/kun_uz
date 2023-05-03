package com.example.mapper;

import java.time.LocalDateTime;

public interface ArticleFullInfoMapper {
     String getId();
     String getTitle();
     String getDescription();
     String getContent();
     Long getSharedCount();
     Integer getRegionId();
     String getRegionName();
     Integer getCategoryId();
     String getCategoryName();
     Integer getTypeId();
     String getTypeName();
     LocalDateTime getPublishedDate();
     Long getViewCount();
     Long getLikeCount();
}
