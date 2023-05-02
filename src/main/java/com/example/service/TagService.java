package com.example.service;


import com.example.dto.tag.TagDTO;
import com.example.entity.TagEntity;
import com.example.exps.ItemNotFoundException;
import com.example.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public TagDTO create(TagDTO dto) {
        TagEntity entity = new TagEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());
        tagRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public TagDTO update(Integer id, TagDTO dto) {
        TagEntity entity = getById(id);
        entity.setName(dto.getName());
        tagRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean deleteById(Integer id) {
        TagEntity entity = getById(id);
        tagRepository.delete(entity);
        return true;
    }

    public List<TagDTO> getAll() {
        Iterable<TagEntity> iterable = tagRepository.findAll();
        List<TagDTO> list = new LinkedList<>();
        for (TagEntity entity : iterable) {
            TagDTO dto = new TagDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        }
        return list;
    }

    public TagEntity getById(Integer id) {
        Optional<TagEntity> optional = tagRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("not found tage: ");
        }
        TagEntity entity = optional.get();
        return entity;
    }
    public TagDTO getByIdDto(Integer id) {
        TagEntity entity = getById(id);
        TagDTO dto = new TagDTO();
        dto.setName(entity.getName());
        dto.setId(id);
        return dto;
    }
}
