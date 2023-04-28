package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.CategoryResponseDTO;
import com.example.dto.RegionDTO;
import com.example.dto.RegionResponseDTO;
import com.example.service.CategoryService;
import com.example.service.RegionService;
import com.example.util.JwtSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto,
                                              @RequestHeader("Authorization") String authorization) {
        JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id, @RequestBody CategoryDTO dto,
                                            @RequestHeader("Authorization") String authorization) {
        JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String auth) {
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(categoryService.deleteById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAll(@RequestHeader("Authorization") String auth) {
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/getLang")
    public ResponseEntity<List<CategoryResponseDTO>> getLang(@RequestParam("lang") String lang) {
        return ResponseEntity.ok(categoryService.getLang(lang));
    }
}
