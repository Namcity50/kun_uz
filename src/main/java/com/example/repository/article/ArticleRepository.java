package com.example.repository.article;

import com.example.entity.ArticleEntity;
import com.example.mapper.ArticleFullInfoMapper;
import com.example.mapper.ArticleShortInfoMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
    //    ------------delete-----------------------------------------------------------------------------------
    @Transactional
    @Modifying
    @Query("update  ArticleEntity  set visible = false where id = ?1 ")
    int deleteArticle(String articleId);

    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id as attachId,a.published_date " +
            " FROM article AS a  where  a.type_id =:typeId and status =:status order by created_date desc Limit :limit ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find5ByTypeIdNative(@Param("typeId") Integer typeId,
                                                     @Param("status") String status,
                                                     @Param("limit") Integer limit);

    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id as attachId,a.published_date " +
            " FROM article AS a  where  a.type_id =?1 and status =?2 order by created_date desc Limit ?3 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find3ByTypeIdNative(Integer id, String name, int i);

    @Query(value = "SELECT a.id,a.title,a.description,a.attach_id as attachId,a.published_date " +
            " FROM article AS a where  a.id not in (?1) limit 8 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find8ByTypeIdNative(List<String> ids);

    @Query(value = " select a.id as id, case ?2 when 'uz' then name_uz " +
            " when 'en' then name_eng else name_ru end as name from article as a " +
            " where a.id = ?1 ", nativeQuery = true)
    ArticleFullInfoMapper findIdAndLangNative(String id, String name);

    @Query(value = " select a.id,a.title,a.description,a.attach_id as attachId,a.published_date from article as a " +
            " inner join article_type as t on t.id = a.type_id where a.id not in (?1) order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleType(String id, int count);

    @Query(value = " select a.id,a.title,a.description,a.attach_id as attachId, " +
            " a.published_date,a.view_count from article as a " +
            " order by a.view_count desc limit ?1 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getMostRead4ArticleType(int count);

    @Query(value = " select a.id,a.title,a.description,a.attach_id as attachId, " +
            " a.published_date,a.region_id as regId, a.tag_id as tagId from article as a inner join tag as t " +
            " on t.id = a.tag_id where t.id = ?1 " +
            " order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleTagName(Integer tagId, int i);

    @Query(value = " select a.id,a.title,a.description,a.attach_id as attachId, " +
            " a.published_date,a.tag_id as tagId, a.region_id as regId from article as a inner join region as r " +
            " on r.id = a.region_id where r.id = ?1 " +
            " order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleRegionId(Integer id, int i);

    @Query(value = " select a.id,a.title,a.description,a.attach_id as attachId, " +
            " a.published_date,a.tag_id as tagId, a.region_id as regId from article as a inner join region as r " +
            " on r.id = a.region_id where r.id = ?1  ",
            nativeQuery = true)
    Page<ArticleShortInfoMapper> findByRegionIdPagination(Integer key, Pageable pageable);

    @Query(value = " select a.id,a.title,a.description,a.attach_id as attachId, " +
            " a.published_date,a.tag_id as tagId, a.region_id as regId from article as a inner join category as c " +
            " on c.id = a.category_id where c.id = ?1 " +
            " order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleCategoryId(Integer key, int i);

    @Query(value = " select a.id,a.title,a.description,a.attach_id as attachId, " +
            " a.published_date,a.tag_id as tagId, a.region_id as regId from article as a inner join category as c " +
            " on c.id = a.region_id where c.id = ?1  ",
            nativeQuery = true)
    Page<ArticleShortInfoMapper> findByCategoryIdPagination(Integer key, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = " update article as a set view_count = a.view_count + 1 where a.id = ?1 ",nativeQuery = true)
    int increaseViewCount(String id);
    @Transactional
    @Modifying
    @Query(value = " update article as a set shared_count = a.shared_count + 1 where a.id = ?1 ",nativeQuery = true)
    int increaseShareCount(String id);



//    List<ArticleEntity> findTop8ByArticleType_IdOrderByCreatedDateDesc(Integer id);
//
//    List<ArticleEntity> findTop3ByArticleType_IdOrderByCreatedDateDesc(Integer id);
//    Optional<ArticleEntity> findById(Integer id);
//
//    Optional<ArticleEntity> findByIdAndStatus(Integer id, ProfileRole status);
//@Query("update ArticleEntity set status = ?2 where id = ?1 ")
//ArticleEntity updateStatus(Integer id, ProfileRole status);
//
//    List<ArticleEntity> findTop4ByArticleType_IdOrderByCreatedDateDesc(Integer id);
//    List<ArticleEntity> findTop4ByArticleType_IdOrderByViewCountDesc(Integer id);
//    List<ArticleEntity> findTop5ByRegion_KeyOrderByCreatedDateDesc(String key);
//    Page<ArticleEntity> findByRegion_Key(Pageable pageable,String key);
//
//    List<ArticleEntity> findTop5ByCategory_KeyOrderByCreatedDateDesc(String key);
//    Page<ArticleEntity> findByCategory_Key(Pageable pageable,String key);
//    List<ArticleEntity> findTop5ByArticleType_IdOrderByCreatedDateDesc(Integer articleType_id);

//    @Query("From ArticleEntity where status =:status and visible = true and typeId =:typeId order by createdDate desc limit 5")
//    List<ArticleEntity> find5ByTypeId(@Param("typeId") Integer typeId, @Param("status") ArticleStatus status);
//    -----------------------------------------------------------------------------------------------------------------
//@Query("SELECT new ArticleEntity(id,title,description,attachId,publishedDate) From ArticleEntity where status =:status and visible = true and typeId =:typeId order by createdDate desc limit 5")
//List<ArticleEntity> find5ByTypeId(@Param("typeId") Integer typeId, @Param("status") ArticleStatus status);
//    ------------------------------------------------------------------------------------------------------------------
//@Query(value = "SELECT a.id,a.title,a.description,a.attach_id,a.published_date " +
//        " FROM article AS a  where  a.type_id =:t_id and status =:status order by created_date desc Limit :limit",
//        nativeQuery = true)
//List<ArticleShortInfoMapper> getTopNative(@Param("t_id") Integer t_id, @Param("status") String status, @Param("limit") Integer limit);
//    ------------------------------------------------------------------------------------------------------------------
}
