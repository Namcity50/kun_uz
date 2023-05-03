package com.example.repository.comment;

import com.example.dto.comment.CommentCustomDTO;
import com.example.entity.CommentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CommentCustomRepository {
    private final EntityManager entityManager;

    public Page<CommentEntity> filter(CommentCustomDTO dto, int page, int size) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        if (dto.getProfileId() != null) {
            builder.append("and c.profileId = :profileId ");
            params.put("profileId", dto.getProfileId());
        }
        if (dto.getArticleId() != null) {
            builder.append(" and c.articleId = :articleId ");
            params.put("articleId", dto.getArticleId());
        }
        if (dto.getCreatedDateFrom() != null) {
            builder.append(" and c.createdDate between :dateFrom and dateTo ");
            params.put("dateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN));
            params.put("dateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MIN));
        } else if (dto.getCreatedDateFrom() != null) {
            builder.append(" and c.createdDate >= :dateFrom ");
            params.put("dateFrom", LocalDateTime.of(dto.getCreatedDateFrom(), LocalTime.MIN));
        } else if (dto.getCreatedDateTo() != null) {
            builder.append(" and c.createdDate <= :dateTo ");
            params.put("dateTo", LocalDateTime.of(dto.getCreatedDateTo(), LocalTime.MIN));
        }
        StringBuilder selectBuilder = new StringBuilder(" select c from CommentEntity as c where visible = true ");
        selectBuilder.append(builder);
        StringBuilder countBuilder = new StringBuilder(" select count(c) from CommentEntity as c where visible = true ");
        countBuilder.append(builder);

        Query selectQuery = this.entityManager.createQuery(selectBuilder.toString());
        Query countQuery = this.entityManager.createQuery(countBuilder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }
        selectQuery.setFirstResult((page - 1) * size);
        selectQuery.setMaxResults(size);
        List<CommentEntity> commentEntityList = selectQuery.getResultList();
        Long count = (Long) countQuery.getSingleResult();
        return new PageImpl<>(commentEntityList, PageRequest.of(page, size), count);
    }
}
