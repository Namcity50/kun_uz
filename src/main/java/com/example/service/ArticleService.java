package com.example.service;

import com.example.dto.ArticleDTO;
import com.example.dto.ArticleShortInfoDTO;
import com.example.dto.attach.AttachResponseDTO;
import com.example.entity.ArticleEntity;
import com.example.entity.AttachEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.enums.ProfileRole;
import com.example.exps.AppBadRequestException;
import com.example.exps.ItemNotFoundException;
import com.example.repository.ArticleRepository;
import com.example.repository.AttachRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private AttachService attachService;
    @Autowired
    private AttachRepository attachRepository;
//    public ArticleRequestDTO create(ArticleRequestDTO dto, Integer moderId) {
//        // check
//        ProfileEntity moderator = profileService.get(moderId);
//        RegionEntity region = regionService.get(dto.getRegionId());
//        CategoryEntity category = categoryService.get(dto.getCategoryId());
//
//        ArticleEntity entity = new ArticleEntity();
//        entity.setTitle(dto.getTitle());
//        entity.setDescription(dto.getDescription());
//        entity.setContent(dto.getContent());
//        entity.setModeratorId(moderId);
//        entity.setRegionId(dto.getRegionId());
//        entity.setCategoryId(dto.getCategoryId());
//        entity.setAttachId(dto.getAttachId());
//        // type
//        articleRepository.save(entity);
//        return dto;
//    }

    public ArticleDTO create(ArticleDTO dto, Integer id) {
        Optional<CategoryEntity> category = categoryRepository.findById(dto.getCategoryId());
        Optional<RegionEntity> region = regionRepository.findById(dto.getRegionId());
        Optional<AttachEntity> attach = attachRepository.findById(dto.getImageId());
        ArticleEntity entity = new ArticleEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDescription(dto.getDescription());
        entity.setSharedCount(dto.getShared_count());
        if (category.isEmpty()) {
            throw new ItemNotFoundException("Not Found category:");
        }
        if (region.isEmpty()) {
            throw new ItemNotFoundException("Not Found region:");
        }
        if (attach.isEmpty()) {
            throw new ItemNotFoundException("Not Found ImageId:");
        }
        entity.setCategory(category.get());
        entity.setRegion(region.get());
        entity.setImage(attach.get());
        entity.setStatus(ProfileRole.NOT_PUBLISHER);
        entity.setPrtId(id);
        articleRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(ArticleDTO dto) {
        Optional<ArticleEntity> optional = articleRepository.findById(dto.getId());
        Optional<CategoryEntity> category = categoryRepository.findById(dto.getCategoryId());
        Optional<RegionEntity> region = regionRepository.findById(dto.getRegionId());
        Optional<AttachEntity> attach = attachRepository.findById(dto.getImageId());
        ArticleEntity entity = optional.get();
        if (entity.getId() == null) {
            throw new AppBadRequestException("Not Found Article:");
        }
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDescription(dto.getDescription());
        entity.setSharedCount(dto.getShared_count());
        if (category.isEmpty()) {
            throw new ItemNotFoundException("Not Found category:");
        }
        if (region.isEmpty()) {
            throw new ItemNotFoundException("Not Found region:");
        }
        if (attach.isEmpty()) {
            throw new ItemNotFoundException("Not Found ImageId:");
        }
        entity.setCategory(category.get());
        entity.setRegion(region.get());
        entity.setImage(attach.get());
        articleRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        ArticleEntity entity = optional.get();
        if (entity.getId() == null) {
            throw new ItemNotFoundException("Not Found Article:");
        }
        articleRepository.delete(entity);
        return true;
    }

    public ProfileRole changeStatus(Integer id, ProfileRole status) {
        Optional<ArticleEntity> optional = articleRepository.findByIdAndStatus(id, status);
        ArticleEntity entity = optional.get();
        if (entity.getStatus().equals(status)) {
            throw new AppBadRequestException("Wrong status:");
        }
        ArticleEntity role = articleRepository.updateStatus(id, status);
        return role.getStatus();
    }

    public List<ArticleShortInfoDTO> getArticleType5(Integer id) {
        List<ArticleEntity> list = articleRepository.findTop5ByArticleType_IdOrderByCreatedDateDesc(id);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        for (ArticleEntity entity : list) {
            ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setTitle(entity.getTitle());
            dto.setImage(new AttachResponseDTO(entity.getImage().getId(),
                    attachService.toOpenUrl(entity.getImage().getId())));
            dto.setPublishedDate(entity.getPublishedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<ArticleShortInfoDTO> getArticleType3(Integer id) {
        List<ArticleEntity> list = articleRepository.findTop3ByArticleType_IdOrderByCreatedDateDesc(id);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        for (ArticleEntity entity : list) {
            ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setTitle(entity.getTitle());
            dto.setImage(new AttachResponseDTO(entity.getImage().getId(),
                    attachService.toOpenUrl(entity.getImage().getId())));
            dto.setPublishedDate(entity.getPublishedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<ArticleShortInfoDTO> getArticleType8(Integer id) {
        List<ArticleEntity> list = articleRepository.findTop8ByArticleType_IdOrderByCreatedDateDesc(id);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        for (ArticleEntity entity : list) {
            ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setTitle(entity.getTitle());
            dto.setImage(new AttachResponseDTO(entity.getImage().getId(),
                    attachService.toOpenUrl(entity.getImage().getId())));
            dto.setPublishedDate(entity.getPublishedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
