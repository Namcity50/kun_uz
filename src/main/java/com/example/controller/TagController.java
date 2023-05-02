package com.example.controller;


import com.example.dto.tag.TagDTO;
import com.example.enums.ProfileRole;
import com.example.service.TagService;
import com.example.util.JwtSecurityUtil;
import com.example.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@AllArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping("/")
    public ResponseEntity<TagDTO> create(@RequestBody TagDTO dto,
                                         @RequestHeader("Authorization") String authorization){
        JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(tagService.create(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> update(@PathVariable("id") Integer id, @RequestBody TagDTO dto,
                                            @RequestHeader ("Authorization") String authorization){
        JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(tagService.update(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String auth){
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(tagService.deleteById(id));
    }
    @GetMapping("")
    public ResponseEntity<List<TagDTO>> getAll(@RequestHeader("Authorization") String auth ){
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(tagService.getAll());
    }

}
