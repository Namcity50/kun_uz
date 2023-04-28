package com.example.dto;

import com.example.dto.attach.AttachResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ArticleShortInfoDTO {
    private String id;
    private String title;
    private String description;
    private AttachResponseDTO image;
    private LocalDateTime publishedDate;
}
