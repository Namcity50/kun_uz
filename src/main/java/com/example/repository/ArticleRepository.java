package com.example.repository;

import com.example.entity.ArticleEntity;
import com.example.enums.ProfileRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity,String> {

    Optional<ArticleEntity> findById(Integer id);

    Optional<ArticleEntity> findByIdAndStatus(Integer id, ProfileRole status);
    @Query("update ArticleEntity set status = ?2 where id = ?1 ")
    ArticleEntity updateStatus(Integer id,ProfileRole status);
    List<ArticleEntity> findTop5ByArticleType_IdOrderByCreatedDateDesc(Integer articleType_id);

    List<ArticleEntity> findTop3ByArticleType_IdOrderByCreatedDateDesc(Integer id);

    List<ArticleEntity> findTop8ByArticleType_IdOrderByCreatedDateDesc(Integer id);
}
