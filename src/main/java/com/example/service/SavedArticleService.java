package com.example.service;

import com.example.dto.article.ArticleDTO;
import com.example.dto.profile.ProfileDTO;
import com.example.dto.saveArticle.SavedArticleDTO;
import com.example.entity.SavedArticleEntity;
import com.example.repository.SavedArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SavedArticleService {
    private final ProfileService profileService;
    private final ArticleService articleService;
    private final SavedArticleRepository savedArticleRepository;
    public SavedArticleDTO create(SavedArticleDTO dto) {
        ProfileDTO profileId = profileService.getByProfileId(dto.getProfileId());
        ArticleDTO articleId = articleService.getArticleId(dto.getArticleId());
        SavedArticleEntity entity = new SavedArticleEntity();
        entity.setArticleId(articleId.getId());
        entity.setProfileId(profileId.getId());
        entity.setCreatedDate(LocalDateTime.now());
        savedArticleRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean delete(String id) {
       SavedArticleEntity entity  = savedArticleRepository.findByArticleId(id);
       savedArticleRepository.delete(entity);
       return true;
    }
}
