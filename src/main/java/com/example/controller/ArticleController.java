package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.dto.ArticleShortInfoDTO;
import com.example.dto.jwt.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<ArticleDTO> create(@RequestBody ArticleDTO dto, @RequestHeader("Authorization") String auth) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(auth, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, jwtDTO.getId()));
    }

    @PutMapping("")
    public ResponseEntity<Boolean> update(@RequestBody ArticleDTO dto, @RequestHeader("Authorization") String auth) {
        JwtUtil.getJwtDTO(auth, ProfileRole.MODERATOR, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.update(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id, @RequestHeader("Authorization") String auth) {
        JwtUtil.getJwtDTO(auth, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfileRole> change(@PathVariable("id") Integer id,
                                              @RequestParam("status") ProfileRole status,
                                              @RequestHeader("Authorization") String auth) {
        JwtUtil.getJwtDTO(auth, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(id, status));
    }

    @GetMapping("/5/{id}")
    public ResponseEntity<List<ArticleShortInfoDTO>> getArticleType5(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getArticleType5(id));
    }

    @GetMapping("/3/{id}")
    public ResponseEntity<List<ArticleShortInfoDTO>> getArticleType3(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getArticleType3(id));
    }

    @GetMapping("/8/{id}")
    public ResponseEntity<List<ArticleShortInfoDTO>> getArticleType8(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getArticleType8(id));
    }
}
