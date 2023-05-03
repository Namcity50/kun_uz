package com.example.controller;


import com.example.dto.jwt.JwtDTO;
import com.example.service.CommentLikeService;
import com.example.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/like/comment")
@AllArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") Integer commentId,
                                        @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.createLike(commentId, jwt.getId()));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") Integer commentId,
                                           @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.createDislike(commentId, jwt.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer commentId,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.deleteLike(commentId, jwt.getId()));
    }
}
