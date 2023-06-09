package com.example.service;

import com.example.dto.region.RegionDTO;
import com.example.dto.region.RegionResponseDTO;
import com.example.entity.RegionEntity;
import com.example.exps.ItemNotFoundException;
import com.example.mapper.RegionMapperDTO;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    public RegionDTO create(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEng(dto.getNameEng());
        regionRepository.save(entity);
        return dto;
    }

    public RegionDTO update(Integer id, RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException(" Not found Id: ");
        }
        RegionEntity entity = optional.get();
        entity.setKey(dto.getKey());
        entity.setNameEng(dto.getNameEng());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        regionRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean deleteById(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("not found region: ");
        }
        regionRepository.deleteById(id);
        return true;
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> list = new LinkedList<>();
        for (RegionEntity entity: iterable){
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setVisible(entity.getVisible());
            dto.setNameRu(entity.getNameRu());
            dto.setNameEng(entity.getNameEng());
            dto.setNameUz(entity.getNameUz());
            dto.setKey(entity.getKey());
            list.add(dto);
        }
        return list;
    }

    public List<RegionResponseDTO> getLang(String lang) {
       List<RegionMapperDTO> mapperDTOS = regionRepository.findByLang(lang);
       List<RegionResponseDTO> dtoList = new LinkedList<>();
       mapperDTOS.forEach(regionMapperDTO -> {
           System.out.println(regionMapperDTO.getName());
           RegionResponseDTO dto = new RegionResponseDTO();
           dto.setId(regionMapperDTO.getId());
           dto.setKey(regionMapperDTO.getKey());
           dto.setLang(regionMapperDTO.getName());
           dtoList.add(dto);
       });
       return dtoList;
    }
    public RegionResponseDTO getLang(Integer id,String lang) {
        RegionMapperDTO mapperDTOS = regionRepository.findByIdAndLang(id,lang);
            RegionResponseDTO dto = new RegionResponseDTO();
            dto.setId(mapperDTOS.getId());
            dto.setKey(mapperDTOS.getKey());
            dto.setLang(mapperDTOS.getName());


        return dto;
    }
    public RegionDTO getRegionId(Integer id){
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException(" Not found region: ");
        }
        RegionEntity entity = optional.get();
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEng(entity.getNameEng());
        dto.setNameRu(entity.getNameRu());
        return dto;
    }
}
