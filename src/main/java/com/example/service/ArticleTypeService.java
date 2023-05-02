package com.example.service;

import com.example.mapper.ArticleTypeMapperDTO;
import com.example.dto.articleType.ArticleTypeResponseDTO;
import com.example.dto.articleType.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.exps.AppBadRequestException;
import com.example.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;
    public ArticleTypeDTO create(ArticleTypeDTO dto ) {
        Optional<ArticleTypeEntity> exist = articleTypeRepository.findByKey(dto.getKey());
        if (!exist.isEmpty()){
            throw new AppBadRequestException("ArticleType exist: ");
        }
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEng(dto.getNameEng());
        entity.setNameRu(dto.getNameRu());
        articleTypeRepository.save(entity);
        return dto;
    }
    public ArticleTypeEntity isValidArticleType(Integer dto){
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(dto);
        if (optional.isEmpty()){
            throw new AppBadRequestException(" Not found ArticleType:");
        }
        ArticleTypeEntity entity = optional.get();
        return entity;
    }

    public ArticleTypeDTO update(Integer id, ArticleTypeDTO dto) {
        ArticleTypeEntity entity = isValidArticleType(id);
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        entity.setKey(dto.getKey());
        if (dto.getNameEng() == null){
            throw new AppBadRequestException("Not found nameEng: ");
        }
        if (dto.getNameUz() == null){
            throw new AppBadRequestException("Not found nameUz: ");
        }if (dto.getNameRu() == null){
            throw new AppBadRequestException("Not found nameRu: ");
        }
        articleTypeRepository.save(entity);
        return dto;
    }

    public Boolean deleteById(Integer id) {
        ArticleTypeEntity entity = isValidArticleType(id);
        articleTypeRepository.delete(entity);
        return true;
    }

    public Page<ArticleTypeDTO> getAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"CreatedDate");
        Pageable pageable = PageRequest.of(page, size,sort);
        Page<ArticleTypeEntity> entityPage = articleTypeRepository.findAll(pageable);
        Long totalCount = entityPage.getTotalElements();
        List<ArticleTypeEntity> entityList = entityPage.getContent();
        List<ArticleTypeDTO> typeDTOList = new LinkedList<>();
        for (ArticleTypeEntity entity : entityList){
            ArticleTypeDTO dto = new ArticleTypeDTO();
            dto.setKey(entity.getKey());
            dto.setNameUz(entity.getNameUz());
            dto.setNameRu(entity.getNameRu());
            dto.setNameEng(entity.getNameEng());
            dto.setVisible(entity.getVisible());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setId(entity.getId());
            typeDTOList.add(dto);
        }
        return new PageImpl<>(typeDTOList,pageable,totalCount);

    }

    public List<ArticleTypeResponseDTO> getLang(String lang) {
        List<ArticleTypeMapperDTO> list = articleTypeRepository.findByIdAndLang(lang);
        List<ArticleTypeResponseDTO> dtoList = new LinkedList<>();
        list.forEach(articleTypeMapperDTO -> {
            ArticleTypeResponseDTO dto = new ArticleTypeResponseDTO();
            dto.setId(articleTypeMapperDTO.getId());
            dto.setKey(articleTypeMapperDTO.getKey());
            dto.setLang(articleTypeMapperDTO.getName());
            dtoList.add(dto);
        });


       return dtoList;
    }
    public ArticleTypeDTO getArticleTypeDTO(Integer id){
        ArticleTypeEntity entity =  isValidArticleType(id);
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEng(entity.getNameEng());
        dto.setNameRu(entity.getNameRu());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
