package com.example.controller;

import com.example.dto.article.ArticleDTO;
import com.example.dto.article.ArticleFilterDTO;
import com.example.dto.article.ArticleFullInfoDTO;
import com.example.dto.article.ArticleShortInfoDTO;
import com.example.dto.jwt.JwtDTO;
import com.example.enums.ArticleStatus;
import com.example.enums.ProfileRole;
import com.example.service.ArticleService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Boolean> update(@RequestParam("id") String id,
                                          @RequestBody ArticleDTO dto,
                                          @RequestHeader("Authorization") String auth) {
        JwtUtil.getJwtDTO(auth, ProfileRole.MODERATOR, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleService.update(dto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String auth) {
        JwtUtil.getJwtDTO(auth, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> change(@PathVariable("id") String id,
                                              @RequestParam("status") ArticleStatus status,
                                              @RequestHeader("Authorization") String auth) {
        JwtDTO jwtDTO = JwtUtil.getJwtDTO(auth, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.changeStatus(status,id,  jwtDTO.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ArticleShortInfoDTO>> getArticles5(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getArticles5(id));
    }

    @GetMapping("/get3/{id}")
    public ResponseEntity<List<ArticleShortInfoDTO>> getArticles3(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getArticles3(id));
    }
//
    @GetMapping("/get8")
    public ResponseEntity<List<ArticleShortInfoDTO>> getArticles8(@RequestParam("ids") List<String> id) {
        return ResponseEntity.ok(articleService.getArticles8(id));
    }
    @GetMapping("/getLang")
    public ResponseEntity<List<ArticleFullInfoDTO>> getLangName(@RequestParam("lang") String lang) {
        return ResponseEntity.ok(articleService.getAllByLang(lang));
    }
    @GetMapping("/last/{id}")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLastArticle(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.getLast4Articles(id));
    }
    @GetMapping("/mostRead")
    public ResponseEntity<List<ArticleShortInfoDTO>> getMostReadArticle() {
        return ResponseEntity.ok(articleService.getMostReadArticles());
    }
    @GetMapping("/getLangTag")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLangTag( @RequestParam("id") Integer tagId) {
        return ResponseEntity.ok(articleService.getLast4ArticlesTagName(tagId));
    }
    @GetMapping("/getKeyRegion")
    public ResponseEntity<List<ArticleShortInfoDTO>> getRegionKeyArticle(@RequestParam("key") Integer id) {
        return ResponseEntity.ok(articleService.getRegionKeyArticles(id));
    }
    @GetMapping("/getByPaginationRegion")
    public ResponseEntity<Page<ArticleShortInfoDTO>> getPageRegion(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                                   @RequestParam("key") Integer key){
        return ResponseEntity.ok(articleService.getPaginationRegion(page,size,key));
    }
    @GetMapping("/getKeyCategory")
    public ResponseEntity<List<ArticleShortInfoDTO>> getCategoryKeyArticle(@RequestParam("key") Integer key) {
        return ResponseEntity.ok(articleService.getCategoryKeyArticles(key));
    }
    @GetMapping("/getByPaginationCategory")
    public ResponseEntity<Page<ArticleShortInfoDTO>> getPageCategory(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                                   @RequestParam("key") Integer key){
        return ResponseEntity.ok(articleService.getPaginationCategory(page,size,key));
    }
    @GetMapping("/viewCount")
    public ResponseEntity<Boolean> increaseViewCount(@RequestParam("id") String id ) {
        return ResponseEntity.ok(articleService.increaseViewCount(id));
    }
    @GetMapping("/shareCount")
    public ResponseEntity<Boolean> increaseShareCount(@RequestParam("id") String id ) {
        return ResponseEntity.ok(articleService.increaseShareCount(id));
    }
    @PostMapping("/filter")
    public ResponseEntity<Page<ArticleShortInfoDTO>> filter(@RequestBody ArticleFilterDTO dto,
                                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(articleService.filter(dto, page, size));
    }
}
