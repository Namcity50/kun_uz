package com.example.controller;

import com.example.dto.saveArticle.SavedArticleDTO;
import com.example.service.SavedArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/savedArticle")
@AllArgsConstructor
public class SavedArticleController {
    private final SavedArticleService savedArticleService;
    @PostMapping("/")
    public ResponseEntity<SavedArticleDTO> create(@RequestBody SavedArticleDTO dto ){
        return ResponseEntity.ok(savedArticleService.create(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@RequestBody String id ){
        return ResponseEntity.ok(savedArticleService.delete(id));
    }
}
