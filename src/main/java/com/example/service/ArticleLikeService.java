package com.example.service;

import com.example.entity.ArticleLikeEntity;
import com.example.enums.LikeStatus;
import com.example.repository.article.ArticleLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleLikeService {
    private final ArticleLikeRepository articleLikeRepository;

    public boolean like(String articleId, Integer profileId) {
        makeEmotion(articleId, profileId, LikeStatus.LIKE);
        return true;
    }

    public boolean dislike(String articleId, Integer profileId) {
        makeEmotion(articleId, profileId, LikeStatus.DISLIKE);
        return true;
    }

    public boolean delete(String articleId, Integer profileId) {
        articleLikeRepository.delete(articleId, profileId);
        return true;
    }

    private void makeEmotion(String articleId, Integer profileId, LikeStatus status) {
        Optional<ArticleLikeEntity> optional = articleLikeRepository
                .findByArticleIdAndProfileId(articleId, profileId);
        if (optional.isEmpty()) {
            ArticleLikeEntity entity = new ArticleLikeEntity();
            entity.setArticleId(articleId);
            entity.setProfileId(profileId);
            entity.setStatus(status);
            articleLikeRepository.save(entity);
            // article like count dislike count larni trigger orqali qilasizlar.
        } else {
            articleLikeRepository.update(status, articleId, profileId);
        }
    }

}
