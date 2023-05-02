package com.example.mapper;

import com.example.dto.category.CategoryResponseDTO;
import com.example.dto.region.RegionResponseDTO;

import java.time.LocalDateTime;

public interface ArticleFullInfoMapper {
     String getId();
     String getTitle();
     String getDescription();
     String getContent();
     Long getSharedCount();
     RegionResponseDTO getRegion();
     CategoryResponseDTO getCategory();
     LocalDateTime getPublishedDate();
     Long getViewCount();
     Long getLikeCount();
}
