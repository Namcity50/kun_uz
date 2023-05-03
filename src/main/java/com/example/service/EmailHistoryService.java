package com.example.service;

import com.example.dto.email.EmailHistoryDTO;
import com.example.entity.EmailHistoryEntity;
import com.example.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    public EmailHistoryDTO create(EmailHistoryDTO dto) {
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());
        entity.setCreatedData(LocalDateTime.now());
        emailHistoryRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<EmailHistoryDTO> getEmail(String email) {
        List<EmailHistoryEntity> list = emailHistoryRepository.findAllByEmail(email);
        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        for (EmailHistoryEntity entity : list) {
            EmailHistoryDTO dto = new EmailHistoryDTO();
            dto.setId(entity.getId());
            dto.setMessage(entity.getMessage());
            dto.setEmail(entity.getEmail());
            dto.setCreatedData(entity.getCreatedData());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<EmailHistoryDTO> getDate(LocalDate date) {
        LocalDateTime dateFrom = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime dateTo = LocalDateTime.of(date, LocalTime.MAX);

        List<EmailHistoryEntity> list = emailHistoryRepository.findByCreatedDataBetween(dateFrom, dateTo);
        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        for (EmailHistoryEntity entity : list) {
            EmailHistoryDTO dto = new EmailHistoryDTO();
            dto.setId(entity.getId());
            dto.setMessage(entity.getMessage());
            dto.setEmail(entity.getEmail());
            dto.setCreatedData(entity.getCreatedData());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public Page<EmailHistoryDTO> getPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "CreatedDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EmailHistoryEntity> entityPage = emailHistoryRepository.findAllBy(pageable);
        Long totalCount = entityPage.getTotalElements();
        List<EmailHistoryEntity> entityList = entityPage.getContent();
        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        for (EmailHistoryEntity entity : entityList) {
            EmailHistoryDTO dto = new EmailHistoryDTO();
            dto.setId(entity.getId());
            dto.setEmail(entity.getEmail());
            dto.setMessage(entity.getMessage());
            dto.setCreatedData(entity.getCreatedData());
            dtoList.add(dto);
        }
        Page<EmailHistoryDTO> result = new PageImpl<>(dtoList, pageable, totalCount);
        return result;
    }
}
