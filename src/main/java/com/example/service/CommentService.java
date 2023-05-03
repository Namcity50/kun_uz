package com.example.service;

import com.example.dto.article.ArticleCommentResponseDTO;
import com.example.dto.comment.CommentCustomDTO;
import com.example.dto.comment.CommentDTO;
import com.example.dto.comment.CommentResponseDTO;
import com.example.dto.comment.CommentShortInfoDTO;
import com.example.dto.profile.ProfileResponseDTO;
import com.example.entity.CommentEntity;
import com.example.exps.ItemNotFoundException;
import com.example.mapper.CommentMapperDTO;
import com.example.repository.comment.CommentCustomRepository;
import com.example.repository.comment.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentCustomRepository commentCustomRepository;

    public CommentDTO create(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setProfileId(dto.getProfileId());
        entity.setArticleId(dto.getArticleId());
        entity.setContent(dto.getContent());
        entity.setReplyId(dto.getReplyId());
        entity.setCreatedDate(LocalDateTime.now());
        commentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(CommentDTO dto, String id) {
        CommentEntity entity = getId(id,dto.getProfileId());
        entity.setContent(dto.getContent());
        entity.setReplyId(dto.getReplyId());
        entity.setUpdateDate(LocalDateTime.now());
        commentRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        Optional<CommentEntity> optional = commentRepository.findById(id);
        CommentEntity entity = optional.get();
        commentRepository.delete(entity);
        return true;
    }

    public List<CommentResponseDTO> getArticleCommentList(String articleId) {
        List<CommentMapperDTO> mapper = commentRepository.getArticleCommentList(articleId);
        System.out.println(mapper.toString());
        List<CommentResponseDTO> dtoList = new LinkedList<>();
        mapper.forEach(mapperDTO -> {
            dtoList.add(getCommentResponseDTO(mapperDTO));
        });
        return dtoList;
    }

    public Page<CommentShortInfoDTO> filter(CommentCustomDTO dto, int page, int size) {
        Page<CommentEntity> entityPage = commentCustomRepository.filter(dto, page, size);
        List<CommentShortInfoDTO> dtoList = new LinkedList<>();
        entityPage.forEach(commentEntity -> {
            dtoList.add(getShortInfo(commentEntity));
        });
        return new PageImpl<>(dtoList, PageRequest.of(page, size), entityPage.getTotalElements());

    }

    public List<CommentResponseDTO> getCommentReplayList(Integer id) {
        List<CommentMapperDTO> mapper = commentRepository.getCommentReplyAll(id);
        List<CommentResponseDTO> dtoList = new LinkedList<>();
        mapper.forEach(mapperDTO -> {
            dtoList.add(getCommentResponseDTO(mapperDTO));
        });
        return dtoList;
    }

    public CommentEntity getId(String id,Integer profileId) {
        Optional<CommentEntity> optional = commentRepository.findByArticleIdAndProfileId(id,profileId);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException(" Not found Id: ");
        }
        CommentEntity entity = optional.get();
        return entity;
    }

    public CommentResponseDTO getCommentResponseDTO(CommentMapperDTO mapperDTO) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setProfile(new ProfileResponseDTO(mapperDTO.getProfileId(), mapperDTO.getProfileName(),
                mapperDTO.getProfileSurname()));
        dto.setArticle(new ArticleCommentResponseDTO(mapperDTO.getArticleId(), mapperDTO.getArticleTitle()));
        dto.setUpdateDate(mapperDTO.getUpdateDate());
        dto.setCreatedDate(mapperDTO.getCreatedDate());
        dto.setId(mapperDTO.getId());
        return dto;
    }

    public CommentShortInfoDTO getShortInfo(CommentEntity entity) {
        CommentShortInfoDTO dto = new CommentShortInfoDTO();
        dto.setId(entity.getId());
        dto.setArticleId(entity.getArticleId());
        dto.setReplyId(entity.getReplyId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public Page<CommentResponseDTO> getPagination(Integer page, Integer size) {
        Sort sort = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "created_date");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CommentMapperDTO> entityPage = commentRepository.findBydPagination(pageable);
        Long totalCount = entityPage.getTotalElements();
        List<CommentMapperDTO> entityList = entityPage.getContent();
        List<CommentResponseDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            dtoList.add(getCommentResponseDTO(entity));
        });
        Page<CommentResponseDTO> result = new PageImpl<>(dtoList, pageable, totalCount);
        return result;
    }
}
