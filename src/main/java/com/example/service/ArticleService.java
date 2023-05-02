package com.example.service;

import com.example.dto.article.ArticleDTO;
import com.example.dto.article.ArticleFullInfoDTO;
import com.example.dto.article.ArticleShortInfoDTO;
import com.example.entity.ArticleEntity;
import com.example.entity.AttachEntity;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.enums.ArticleStatus;
import com.example.exps.ItemNotFoundException;
import com.example.mapper.ArticleFullInfoMapper;
import com.example.mapper.ArticleShortInfoMapper;
import com.example.repository.article.ArticleRepository;
import com.example.repository.AttachRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;
    private final AttachService attachService;
    private final AttachRepository attachRepository;
    private final TagService tagService;
    private final CategoryService categoryService;
    private final  RegionService regionService;

    public ArticleDTO create(ArticleDTO dto, Integer id) {
        Optional<CategoryEntity> category = categoryRepository.findById(dto.getCategoryId());
        Optional<RegionEntity> region = regionRepository.findById(dto.getRegionId());
        Optional<AttachEntity> attach = attachRepository.findById(dto.getImageId());
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(id);
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getImageId());
        articleRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(ArticleDTO dto,String id ) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        ArticleEntity entity = optional.get();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getImageId());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(entity);
        return true;
    }

    public Boolean delete(String id) {
        int b = articleRepository.deleteArticle(id);
        if (b != 1){
            return false;
        }
        return true;
    }

    public Boolean changeStatus(ArticleStatus status, String id, Integer prtId) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        ArticleEntity entity = optional.get();
        if (status.equals(ArticleStatus.PUBLISHER)) {
            entity.setPublishedDate(LocalDate.now());
            entity.setPublisherId(prtId);
        }
        entity.setStatus(status);
        articleRepository.save(entity);
        return true;
    }

    public List<ArticleShortInfoDTO> getArticles5(Integer id) {
            List<ArticleShortInfoMapper> entityList = articleRepository.find5ByTypeIdNative(id, ArticleStatus.PUBLISHER.name(), 5);
            List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
            entityList.forEach(entity -> {
                dtoList.add(toArticleShortInfo(entity));
            });
            return dtoList;
    }

    public List<ArticleShortInfoDTO> getArticles3(Integer id) {
        List<ArticleShortInfoMapper> entityList = articleRepository.find3ByTypeIdNative(id, ArticleStatus.PUBLISHER.name(), 3);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }

    public List<ArticleShortInfoDTO> getArticles8(List<String>  ids) {
        List<ArticleShortInfoMapper> entityList = articleRepository.find8ByTypeIdNative(ids);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }

    public ArticleFullInfoDTO getAllByLang(String id, String lang) {
        ArticleFullInfoMapper entityList = articleRepository.findIdAndLangNative(id, lang);
        ArticleFullInfoDTO dto = toArticleFullInfo(entityList);
        return dto;
    }
    public List<ArticleShortInfoDTO> getLast4Articles(String id) {
        List<ArticleShortInfoMapper> entityList = articleRepository.getLast4ArticleType(id,4);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }
    public List<ArticleShortInfoDTO> getMostReadArticles() {
        List<ArticleShortInfoMapper> entityList = articleRepository.getMostRead4ArticleType(4);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }
    public List<ArticleShortInfoDTO> getLast4ArticlesTagName(Integer tagId) {
        List<ArticleShortInfoMapper> entityList = articleRepository.getLast4ArticleTagName(tagId,4);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }
    public List<ArticleShortInfoDTO> getRegionKeyArticles(Integer key) {
        List<ArticleShortInfoMapper> entityList = articleRepository.getLast4ArticleRegionId(key,4);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }
//
    public Page<ArticleShortInfoDTO> getPaginationRegion(Integer page, Integer size, Integer key) {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_date");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ArticleShortInfoMapper> entityPage = articleRepository.findByRegionIdPagination(key,pageable);
        Long totalCount = entityPage.getTotalElements();
        List<ArticleShortInfoMapper> entityList = entityPage.getContent();
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        Page<ArticleShortInfoDTO> result = new PageImpl<>(dtoList, pageable, totalCount);
        return result;
    }
    public List<ArticleShortInfoDTO> getCategoryKeyArticles(Integer key) {
        List<ArticleShortInfoMapper> entityList = articleRepository.getLast4ArticleCategoryId(key,4);
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        return dtoList;
    }
    public Page<ArticleShortInfoDTO> getPaginationCategory(Integer page, Integer size, Integer key) {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_date");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ArticleShortInfoMapper> entityPage = articleRepository.findByCategoryIdPagination(key,pageable);
        Long totalCount = entityPage.getTotalElements();
        List<ArticleShortInfoMapper> entityList = entityPage.getContent();
        List<ArticleShortInfoDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(toArticleShortInfo(entity));
        });
        Page<ArticleShortInfoDTO> result = new PageImpl<>(dtoList, pageable, totalCount);
        return result;
    }
    public ArticleShortInfoDTO toArticleShortInfo(ArticleShortInfoMapper entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublished_date());
        dto.setTag(tagService.getByIdDto(entity.getTagId()));
        dto.setRegion(regionService.getRegionId(entity.getRegId()));
        dto.setImage(attachService.toOpenUrl(entity.getAttachId()));
        return dto;
    }
    public ArticleFullInfoDTO toArticleFullInfo(ArticleFullInfoMapper entity) {
        ArticleFullInfoDTO dto = new ArticleFullInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setViewCount(entity.getViewCount());
        dto.setLikeCount(entity.getLikeCount());
        dto.setRegion(entity.getRegion());
        dto.setCategory(entity.getCategory());
        dto.setSharedCount(entity.getSharedCount());
        return dto;
    }

    public boolean increaseViewCount(String id ) {
        int i = articleRepository.increaseViewCount(id);
        return i > 0;
    }

    public Boolean increaseShareCount(String id) {
        int i = articleRepository.increaseShareCount(id);
        return i > 0;

    }

    public ArticleDTO getArticleId(String articleId) {
        Optional<ArticleEntity> optional = articleRepository.findById(articleId);
        if (optional.isPresent()){
             throw new ItemNotFoundException(" not found article: ");
        }
        ArticleEntity entity = optional.get();
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        return dto;
    }
}
