package com.example.repository;

import com.example.mapper.ArticleTypeMapperDTO;
import com.example.entity.ArticleTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity,Integer> {
    Page<ArticleTypeEntity> findAll(Pageable pageable);

    Optional<ArticleTypeEntity> findByKey(String dto);
    @Query(value = "select id     as id, " +
            "              case  ?1 when 'uz' then name_uz " +
            "              when 'en' then name_eng else name_ru " +
            "              end as name , key as key  " +
            " from article_type  ",nativeQuery = true)
    List<ArticleTypeMapperDTO> findByIdAndLang(String lang);
}
