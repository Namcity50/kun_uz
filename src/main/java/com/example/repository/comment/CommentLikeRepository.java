package com.example.repository.comment;

import com.example.entity.CommentLikeEntity;
import com.example.enums.LikeStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,Integer> {

    Optional<CommentLikeEntity> findByCommentIdAndProfileId(Integer articleId, Integer profileId);
    @Modifying
    @Transactional
    @Query("update CommentLikeEntity set status = :status where profileId = :pid and commentId = :cid ")
    void update(@Param("status") LikeStatus status,
                @Param("pid") Integer profileId,
                @Param("cid") Integer commentId);
    @Modifying
    @Transactional
    @Query("delete from CommentLikeEntity where commentId=:cid and profileId=:pid ")
    int delete(@Param("pid") Integer profileId,
               @Param("cid") Integer commentId);


}
