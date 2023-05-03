package com.example.service;

import com.example.dto.article.ArticleDTO;
import com.example.dto.article.ArticleResponseDTO;
import com.example.dto.profile.ProfileDTO;
import com.example.dto.saveArticle.SavedArticleDTO;
import com.example.dto.saveArticle.SavedArticleResponseDTO;
import com.example.entity.SavedArticleEntity;
import com.example.mapper.SaveArticleMapperDTO;
import com.example.repository.SavedArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class SavedArticleService {
    private final ProfileService profileService;
    private final ArticleService articleService;
    private final SavedArticleRepository savedArticleRepository;
    private final AttachService attachService;
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
    public List<SavedArticleResponseDTO> getArticleById(Integer profileId){
        List<SaveArticleMapperDTO> mapperDTOList = savedArticleRepository.getArticleId(profileId);
        List<SavedArticleResponseDTO> dtoList = new LinkedList<>();
        mapperDTOList.forEach(saveArticleMapperDTO -> {
            dtoList.add(getSavedArticleResponseDTO(saveArticleMapperDTO));
        });  
        return dtoList;
    }
    public SavedArticleResponseDTO getSavedArticleResponseDTO(SaveArticleMapperDTO mapperDTO){
        SavedArticleResponseDTO dto = new SavedArticleResponseDTO();
        dto.setId(mapperDTO.getId());
        dto.setArticle(new ArticleResponseDTO(mapperDTO.getArticleId(),mapperDTO.getArticleTitle(),
                mapperDTO.getArticleDesc(),
                attachService.toOpenUrl(mapperDTO.getImageId())));
        return dto;
    }

}
