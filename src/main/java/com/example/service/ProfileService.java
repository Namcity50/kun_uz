package com.example.service;

import com.example.dto.profile.ProfileDTO;
import com.example.dto.profile.ProfileRequestCustomDTO;
import com.example.entity.AttachEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.GeneralStatus;
import com.example.exps.AppBadRequestException;
import com.example.exps.ItemNotFoundException;
import com.example.repository.profile.ProfileCustomRepository;
import com.example.repository.profile.ProfileRepository;
import com.example.util.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileCustomRepository profileCustomRepository;
    private final AttachService attachService;

    public ProfileDTO create(ProfileDTO dto, Integer adminId) {
        // check - homework
        ProfileEntity exist = isValidProfile(dto.getId());
        if (exist != null) {
            throw new AppBadRequestException("Profile exist: ");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(MD5Util.getMd5Hash(dto.getPassword())); // MD5 ?
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        entity.setStatus(GeneralStatus.ACTIVE);
        entity.setPrtId(adminId);
        profileRepository.save(entity); // save profile
        dto.setPassword(null);
        dto.setId(entity.getId());
        return dto;
    }

    public ProfileEntity isValidProfile(Integer dto) {
        Optional<ProfileEntity> optional = profileRepository.findById(dto);
        ProfileEntity entity = optional.get();
        return entity;

    }

    public ProfileDTO update(Integer id, ProfileDTO dto) {
        ProfileEntity entity = isValidProfile(id);
        if (entity == null) {
            throw new AppBadRequestException("Not Found Profile: ");
        }
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setVisible(true);
        if (dto.getName() == null) {
            throw new AppBadRequestException("Not found profile name: ");
        }
        if (dto.getSurname() == null) {
            throw new AppBadRequestException("Not found profile name: ");
        }

        profileRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public Page<ProfileDTO> getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "CreatedDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> entityPage = profileRepository.findAll(pageable);
        Long totalCount = entityPage.getTotalElements();
        List<ProfileEntity> entityList = entityPage.getContent();
        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            ProfileDTO dto = new ProfileDTO();
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setRole(entity.getRole());
            dto.setEmail(entity.getEmail());
            dto.setPassword(entity.getPassword());
            dto.setId(entity.getId());
            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    public Boolean deleteById(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        ProfileEntity entity = optional.get();
        if (entity.getId() == null) {
            throw new AppBadRequestException("Not found profile ");
        }
        profileRepository.deleteById(id);
        return true;
    }

    public void filter(ProfileRequestCustomDTO filterDTO) {
        List<ProfileEntity> list = profileCustomRepository.filter(filterDTO);
        System.out.println(list);
    }

    public Boolean updatePhoto(Integer id, ProfileDTO dto) {

        Optional<ProfileEntity> optional = profileRepository.findById(id);
        ProfileEntity entity = optional.get();
        int lastIndex = dto.getImage().lastIndexOf(".");
        String name = dto.getImage().substring(0, lastIndex);
        AttachEntity attachEntity = attachService.get(name);
        if (!entity.getImage().equals(attachEntity)) {
            entity.setImage(attachEntity);
        }
        profileRepository.deleteById(entity.getId());
        Path file = Paths.get("attaches/" + dto.getImage());
        try {                                                     // attaches/2023/4/25/20f0f915-93ec-4099-97e3-c1cb7a95151f.jpg
            Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        entity.setImage(attachEntity);
        profileRepository.save(entity);
        return true;
    }
    public ProfileDTO getByProfileId(Integer id){
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        ProfileEntity entity = optional.get();
        if (entity.getId() == null){
            throw new ItemNotFoundException(" Not found profile");
        }
        ProfileDTO dto = new ProfileDTO();
        dto.setEmail(entity.getEmail());
        dto.setSurname(entity.getSurname());
        dto.setName(entity.getName());
        dto.setRole(entity.getRole());
        dto.setImage(entity.getImage().getId());
        dto.setPassword(entity.getPassword());
        dto.setPhone(entity.getPhone());
        dto.setId(entity.getId());
        return dto;
    }
}
