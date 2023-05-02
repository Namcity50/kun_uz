package com.example.repository;

import com.example.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer> {
    List<EmailHistoryEntity> findAllByEmail(String email);
    List<EmailHistoryEntity> findByCreatedDataBetween(LocalDateTime dateTime, LocalDateTime dateTo);

    Page<EmailHistoryEntity> findAllBy(Pageable pageable);

}
