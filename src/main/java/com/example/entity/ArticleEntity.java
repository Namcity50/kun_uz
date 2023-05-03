package com.example.entity;

import com.example.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "article")
@Entity
@Getter
@Setter
public class ArticleEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        @Column(name = "title", columnDefinition = "text")
        private String title;
        @Column(name = "description", columnDefinition = "text")
        private String description;
        @Column(name = "content", columnDefinition = "text")
        private String content;

        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private ArticleStatus status = ArticleStatus.NOT_PUBLISHED;
        @Column(name = "shared_count")
        private Integer sharedCount = 0;

        @Column(name = "attach_id")
        private String attachId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "attach", insertable = false, updatable = false)
        private AttachEntity attach;

        @Column(name = "region_id")
        private Integer regionId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "region", insertable = false, updatable = false)
        private RegionEntity region;

        @Column(name = "category_id")
        private Integer categoryId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category", insertable = false, updatable = false)
        private CategoryEntity category;

        @Column(name = "tag_id")
        private Integer tagId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "tag", insertable = false, updatable = false)
        private TagEntity tag;

        @Column(name = "moderator_id")
        private Integer moderatorId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "moderator", insertable = false, updatable = false)
        private ProfileEntity moderator;

        @Column(name = "type_id")
        private Integer typeId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "type", insertable = false, updatable = false)
        private ArticleTypeEntity type;

        @Column(name = "publisher_id")
        private Integer publisherId;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "publisher", insertable = false, updatable = false)
        private ProfileEntity publisher;

        @Column(name = "created_date")
        private LocalDateTime createdDate = LocalDateTime.now();
        @Column(name = "published_date")
        private LocalDate publishedDate;
        @Column(name = "visible")
        private Boolean visible = Boolean.TRUE;
        @Column(name = "view_count")
        private Integer viewCount;
        @Column(name = "like_count")
        private Integer likeCount;
        @Column(name = "dislike_count")
        private Integer dislikeCount;

    }
//    @Getter
//    @Setter
//    @Table(name = "article")
//    @Entity
//    public class ArticleEntity {

//    ------------------------------------------------------------------------------------------------------------------
//public ArticleEntity() {
//
//}
//
//    public ArticleEntity(String id, String title, String description, String attachId, LocalDateTime publishedDate) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.attachId = attachId;
//        this.publishedDate = publishedDate;
//    }
//    ------------------------------------------------------------------------------------------------------------------

