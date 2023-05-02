package com.example.controller;

import com.example.dto.articleType.ArticleTypeResponseDTO;
import com.example.dto.articleType.ArticleTypeDTO;
import com.example.service.ArticleTypeService;
import com.example.util.JwtSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("/")
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody ArticleTypeDTO dto,
                                                 @RequestHeader("Authorization") String authorization){
       JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(articleTypeService.create( dto ));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ArticleTypeDTO> update(@PathVariable("id") Integer id, @RequestBody ArticleTypeDTO dto,
                                                 @RequestHeader ("Authorization") String authorization){
        JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(articleTypeService.update(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String auth){
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(articleTypeService.deleteById(id));
    }
    @GetMapping("")
    public ResponseEntity<Page<ArticleTypeDTO>> getAll(@RequestParam(value = "page",defaultValue = "1") int page,
                                                          @RequestParam(value = "size",defaultValue = "10") int size,
                                                          @RequestHeader("Authorization") String auth ){
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(articleTypeService.getAll(page,size));
    }
    @GetMapping("/getLang")
    public ResponseEntity<List<ArticleTypeResponseDTO>> getLang(@RequestParam("lang") String lang){
        return ResponseEntity.ok(articleTypeService.getLang(lang));
    }


}
