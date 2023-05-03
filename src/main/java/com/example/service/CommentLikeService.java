package com.example.service;

import com.example.entity.CommentLikeEntity;
import com.example.enums.LikeStatus;
import com.example.repository.comment.CommentLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    public boolean createLike(Integer commentId , Integer profileId){
        makeEmotion(commentId,profileId,LikeStatus.LIKE);
        return true;
    }
    public boolean createDislike(Integer commentId,Integer profileId){
        makeEmotion(commentId,profileId,LikeStatus.DISLIKE);
        return true;
    }
    public boolean deleteLike(Integer commentId , Integer profileId){
        commentLikeRepository.delete( commentId , profileId);
        return true;
    }

   private void makeEmotion(Integer commentId, Integer profileId, LikeStatus status){
       Optional<CommentLikeEntity> optional = commentLikeRepository.findByCommentIdAndProfileId(commentId,profileId);
       if (optional.isEmpty()){
           CommentLikeEntity entity = new CommentLikeEntity();
           entity.setProfileId(profileId);
           entity.setCommentId(commentId);
           entity.setStatus(status);
           entity.setCreatedDate(LocalDateTime.now());
           commentLikeRepository.save(entity);
       }
       else {
           commentLikeRepository.update(status,profileId,commentId);
       }
   }
}
