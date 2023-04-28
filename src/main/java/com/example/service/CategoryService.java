package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.CategoryResponseDTO;
import com.example.dto.RegionResponseDTO;
import com.example.entity.CategoryEntity;
import com.example.exps.ItemNotFoundException;
import com.example.mapper.CategoryMapperDTO;
import com.example.mapper.RegionMapperDTO;
import com.example.repository.CategoryRepository;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto) {
        Optional<CategoryEntity> optional = categoryRepository.findByKey(dto.getKey());
        if (optional.isPresent()) {
            throw new ItemNotFoundException("Key exist");
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        categoryRepository.save(entity);
        return dto;
    }

    public CategoryDTO update(Integer id, CategoryDTO dto) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException(" Not found Id: ");
        }
        CategoryEntity entity = optional.get();
        entity.setKey(dto.getKey());
        entity.setNameEng(dto.getNameEng());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean deleteById(Integer id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException(" Not found region: ");
        }
        categoryRepository.deleteById(id);
        return true;
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> iterable = categoryRepository.findAll();
        List<CategoryDTO> list = new LinkedList<>();
        for (CategoryEntity entity : iterable) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setVisible(entity.getVisible());
            dto.setNameRu(entity.getNameRu());
            dto.setNameEng(entity.getNameEng());
            dto.setNameUz(entity.getNameUz());
            dto.setKey(entity.getKey());
            list.add(dto);
        }
        return list;
    }

    public List<CategoryResponseDTO> getLang(String lang) {
        List<CategoryMapperDTO> mapperDTOS = categoryRepository.findByLang(lang);
        List<CategoryResponseDTO> dtoList = new LinkedList<>();
        mapperDTOS.forEach(categoryMapperDTO -> {
            System.out.println(categoryMapperDTO.getName());
            CategoryResponseDTO dto = new CategoryResponseDTO();
            dto.setId(categoryMapperDTO.getId());
            dto.setKey(categoryMapperDTO.getKey());
            dto.setLang(categoryMapperDTO.getName());
            dtoList.add(dto);
        });
        return dtoList;
    }
}

