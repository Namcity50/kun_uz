package com.example.repository;

import com.example.entity.SavedArticleEntity;
import com.example.mapper.SaveArticleMapperDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavedArticleRepository extends CrudRepository<SavedArticleEntity, Integer> {
    SavedArticleEntity findByArticleId(String id);

    @Query(value = "select s.id                          as id, " +
            "              a.id                   as articleId," +
            "              a.title                as articleTitle," +
            "              a.description          as articleDesc, " +
            "              a.attach_id            as imageId, " +
            "              i.url                  as imageUrl " +
            " from saved_article as s inner join article as a " +
            " on a.id = s.article_id and inner join attach as i " +
            " on i.id = a.attach_id where s.profile_id = ?1 ", nativeQuery = true)
    List<SaveArticleMapperDTO> getArticleId(Integer profileId);
}
