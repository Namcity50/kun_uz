package com.example.entity;

import com.example.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@Table(name = "article")
@Entity
@Getter
@Setter
public class ArticleEntity {
    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "description")
    private String description;

    @Column(columnDefinition = "content")
    private String content;

    @Column(name = "shared_count")
    private Long sharedCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    private AttachEntity image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region")
    private RegionEntity region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id",insertable = false,updatable = false)
    private ProfileEntity moderator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private ProfileEntity publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articletype_id")
    private ArticleTypeEntity articleType;

    @Column(name = "moderator_id")
    private Integer moderatorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileRole status; //(Published,NotPublished)

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "prt_id")
    private Integer prtId;

//    @Getter
//    @Setter
//    @Table(name = "article")
//    @Entity
//    public class ArticleEntity {
//        @Id
//        @GeneratedValue(strategy = GenerationType.UUID)
//        private String id;
//        @Column(name = "title", columnDefinition = "text")
//        private String title;
//        @Column(name = "description", columnDefinition = "text")
//        private String description;
//        @Column(name = "content", columnDefinition = "text")
//        private String content;
//        @Enumerated(EnumType.STRING)
//        @Column(name = "status")
//        private ArticleStatus status = ArticleStatus.NOT_PUBLISHED;
//        @Column(name = "shared_count")
//        private Integer sharedCount = 0;
//
//        @Column(name = "attach_id")
//        private Integer attachId;
//        @ManyToOne
//        @JoinColumn(name = "attach_id", insertable = false, updatable = false)
//        private AttachEntity attach;
//
//        @Column(name = "region_id")
//        private Integer regionId;
//        @ManyToOne
//        @JoinColumn(name = "region_id", insertable = false, updatable = false)
//        private RegionEntity region;
//
//        @Column(name = "category_id")
//        private Integer categoryId;
//        @ManyToOne
//        @JoinColumn(name = "category_id", insertable = false, updatable = false)
//        private CategoryEntity category;
//
//        @Column(name = "moderator_id")
//        private Integer moderatorId;
//        @ManyToOne
//        @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
//        private ProfileEntity moderator;
//
//        @Column(name = "publisher_id")
//        private Integer publisherId;
//        @ManyToOne
//        @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
//        private ProfileEntity publisher;
//
//        @Column(name = "created_date")
//        private LocalDateTime createdDate = LocalDateTime.now();
//        @Column(name = "published_date")
//        private LocalDate publishedDate;
//        @Column(name = "visible")
//        private Boolean visible = Boolean.TRUE;
//        @Column(name = "view_count")
//        private Integer viewCount;
//    }
}
