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
    @Transactional
    @Modifying
    @Query("update  ArticleEntity  set visible = false where id = ?1 ")
    int deleteArticle(String articleId);

    @Query(value = "SELECT a.id,a.title,a.description, " +
            "       a.attach_id as attachId,a.published_date " +
            "       FROM article AS a  where  a.type_id =:typeId " +
            "       and status =:status order by created_date desc Limit :limit ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find5ByTypeIdNative(@Param("typeId") Integer typeId,
                                                     @Param("status") String status,
                                                     @Param("limit") Integer limit);

    @Query(value = "SELECT a.id,a.title,a.description, " +
            "       a.attach_id as attachId,a.published_date " +
            "       FROM article AS a  where  a.type_id =?1 and status =?2 " +
            "       order by created_date desc Limit ?3 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find3ByTypeIdNative(Integer id, String name, int i);

    @Query(value = "SELECT a.id,a.title,a.description, " +
            "       a.attach_id as attachId,a.published_date " +
            "       FROM article AS a where  a.id not in (?1) limit 8 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> find8ByTypeIdNative(List<String> ids);

    @Query(value = "select a.id         as articleId, " +
            "       a.attach_id         as attachId, " +
            "       a.category_id       as categoryId, " +
            "       a.type_id           as typeId, " +
            "       a.content           as content, " +
            "       a.title             as title, " +
            "       a.description       as description, " +
            "       a.created_date      as createdDate, " +
            "       a.moderator_id      as moderatorId, " +
            "       a.region_id         as regionId, " +
            "       (case :lang when 'uz' then r.name_uz when 'en' then r.name_eng else r.name_ru end) as regionName, " +
            "       a.category_id                                                                     as categoryId, " +
            "       (case :lang when 'uz' then c.name_uz when 'en' then c.name_eng else c.name_ru end) as categoryName, " +
            "       a.type_id                                                                          as typeId,  " +
            "       (case :lang when 'uz' then c.name_uz when 'en' then c.name_eng else c.name_ru end) as typeName " +
            "       from article as a " +
            "       inner join region as r on r.id = a.region_id " +
            "       inner join category as c on c.id = a.category_id ", nativeQuery = true)
    List<ArticleFullInfoMapper> findIdAndLangNative(@Param("lang") String name);

    @Query(value = " select a.id,a.title,a.description, " +
            "        a.attach_id as attachId,a.published_date " +
            "        from article as a inner join article_type as t " +
            "        on t.id = a.type_id where a.id not in (?1) " +
            "        order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleType(String id, int count);

    @Query(value = " select a.id,a.title,a.description, " +
            "        a.attach_id as attachId, " +
            "        a.published_date,a.view_count " +
            "        from article as a order by " +
            "        a.view_count desc limit ?1 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getMostRead4ArticleType(int count);

    @Query(value = " select a.id,a.title,a.description," +
            "        a.attach_id as attachId, " +
            "        a.published_date,a.region_id as regId, " +
            "        a.tag_id as tagId from article as a inner join tag as t " +
            "        on t.id = a.tag_id where t.id = ?1 " +
            "        order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleTagName(Integer tagId, int i);

    @Query(value = " select a.id,a.title,a.description, " +
            "        a.attach_id as attachId, " +
            "        a.published_date,a.tag_id as tagId, " +
            "        a.region_id as regId from article as a " +
            "        inner join region as r " +
            "        on r.id = a.region_id where r.id = ?1 " +
            "        order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleRegionId(Integer id, int i);

    @Query(value = " select a.id,a.title,a.description," +
            "        a.attach_id as attachId, " +
            "        a.published_date,a.tag_id as tagId, " +
            "        a.region_id as regId from article as a " +
            "        inner join region as r " +
            "        on r.id = a.region_id where r.id = ?1  ",
            nativeQuery = true)
    Page<ArticleShortInfoMapper> findByRegionIdPagination(Integer key, Pageable pageable);

    @Query(value = " select a.id,a.title,a.description," +
            "        a.attach_id as attachId, " +
            "        a.published_date,a.tag_id as tagId, " +
            "        a.region_id as regId from article as a " +
            "        inner join category as c " +
            "        on c.id = a.category_id where c.id = ?1 " +
            "        order by a.created_date desc limit ?2 ",
            nativeQuery = true)
    List<ArticleShortInfoMapper> getLast4ArticleCategoryId(Integer key, int i);

    @Query(value = " select a.id,a.title,a.description, " +
            "        a.attach_id as attachId, " +
            "        a.published_date,a.tag_id as tagId, " +
            "        a.region_id as regId from article as a " +
            "        inner join region as c " +
            "        on c.id = a.region_id where c.id = ?1  ",
            nativeQuery = true)
    Page<ArticleShortInfoMapper> findByCategoryIdPagination(Integer key, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = " update article as a set view_count = a.view_count + 1 where a.id = ?1 ", nativeQuery = true)
    int increaseViewCount(String id);

    @Transactional
    @Modifying
    @Query(value = " update article as a set shared_count = a.shared_count + 1 where a.id = ?1 ", nativeQuery = true)
    int increaseShareCount(String id);
}
