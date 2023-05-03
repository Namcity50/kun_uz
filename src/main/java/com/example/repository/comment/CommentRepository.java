package com.example.repository.comment;

import com.example.entity.CommentEntity;
import com.example.mapper.CommentMapperDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    @Query(value = "select c.content," +
            "              c.id            as id, " +
            "              c.profile_id    as profileId, " +
            "              p.name          as profileName, " +
            "              p.surname       as profileSurname, " +
            "              c.article_id    as articleId, " +
            "              c.update_date   as updateDate, " +
            "              c.created_date  as createdDate " +
            "   from comment as c " +
            "  inner join profile as p " +
            "   on p.id = c.profile_id " +
            "   where c.article_id = :articleId ", nativeQuery = true)
    List<CommentMapperDTO> getArticleCommentList(@Param("articleId") String articleId);

    @Query(value = "select c.id            as id,  " +
            "              p.id            as profileId, " +
            "              p.name          as profileName,  " +
            "              p.surname       as profileSurname, " +
            "              c.created_date  as createdDate,  " +
            "              c.update_date   as updateDate " +
            "              from comment as c inner join profile as p " +
            "              on p.id = c.profile_id  where c.id = ?1 "
            , nativeQuery = true)
    List<CommentMapperDTO> getCommentReplyAll(Integer id);

    @Query(value = "select c.id             as id,  " +
            "              p.id             as profileId, " +
            "              p.name           as profileName,  " +
            "              p.surname        as profileSurname, " +
            "              a.id             as articleId, " +
            "              a.title          as articleTitle, " +
            "              c.created_date   as createdDate, " +
            "              c.update_date    as updateDate  " +
            " from comment as c inner join profile as p " +
            " on p.id = c.profile_id inner join article as a " +
            " on a.id = c.article_id ", nativeQuery = true)
    Page<CommentMapperDTO> findBydPagination(Pageable pageable);

    Optional<CommentEntity> findByArticleIdAndProfileId(String id, Integer profileId);

}
