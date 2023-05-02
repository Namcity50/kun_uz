package com.example.controller;

import com.example.dto.region.RegionDTO;
import com.example.dto.region.RegionResponseDTO;
import com.example.service.RegionService;
import com.example.util.JwtSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                                 @RequestHeader("Authorization") String authorization){
        JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(regionService.create(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto,
                                                 @RequestHeader ("Authorization") String authorization){
        JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(regionService.update(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String auth){
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(regionService.deleteById(id));
    }
    @GetMapping("")
    public ResponseEntity<List<RegionDTO>> getAll(@RequestHeader("Authorization") String auth ){
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(regionService.getAll());
    }
    @GetMapping("/getLang")
    public ResponseEntity<List<RegionResponseDTO>> getLang(@RequestParam("lang") String lang){
        return ResponseEntity.ok(regionService.getLang(lang));
    }
}
