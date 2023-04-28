package com.example.repository;

import com.example.dto.CategoryResponseDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.mapper.CategoryMapperDTO;
import com.example.mapper.RegionMapperDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {

        Optional<CategoryEntity> findByKey(String key);

        Optional<CategoryEntity> findByIdAndKey(Integer id, String key);
        @Query(value = " select id as id, case ?1 when 'uz' then name_uz " +
                " when 'en' then name_eng " +
                " else name_ru end as name, key as key from category ",nativeQuery = true)
        List<CategoryMapperDTO> findByLang(String lang);
    }


