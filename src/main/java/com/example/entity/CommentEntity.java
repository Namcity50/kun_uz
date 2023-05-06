package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Table(name = "comment")
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile",insertable = false,updatable = false)
    private ProfileEntity profile;
    @Column(name = "article_id")
    private String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article",insertable = false,updatable = false)
    private ArticleEntity article;
    @Column(name = "reply_id")
    private Integer replyId;
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "dislike_count")
    private Integer dislikeCount;
}
