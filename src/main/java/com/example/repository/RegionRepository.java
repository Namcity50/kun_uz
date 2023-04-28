package com.example.repository;

import com.example.entity.RegionEntity;
import com.example.mapper.RegionMapperDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
    Optional<RegionEntity> findByKey(String key);

    Optional<RegionEntity> findByIdAndKey(Integer id, String key);
     @Query(value = " select id as id, case ?1 when 'uz' then name_uz " +
             " when 'en' then name_eng " +
             " else name_ru end as name, key as key from region ",nativeQuery = true)
    List<RegionMapperDTO> findByLang(String lang);
}
