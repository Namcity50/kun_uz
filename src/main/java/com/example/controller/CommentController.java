package com.example.controller;

import com.example.dto.comment.CommentCustomDTO;
import com.example.dto.comment.CommentDTO;
import com.example.dto.comment.CommentResponseDTO;
import com.example.dto.comment.CommentShortInfoDTO;
import com.example.enums.ProfileRole;
import com.example.service.CommentService;
import com.example.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("")
    public ResponseEntity<CommentDTO> create(@RequestBody CommentDTO dto ){
        return ResponseEntity.ok(commentService.create(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") String articleId, @RequestBody CommentDTO dto){
        return ResponseEntity.ok(commentService.update(dto,articleId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String auth){
        JwtUtil.getJwtDTO(auth, ProfileRole.ADMIN,ProfileRole.USER,ProfileRole.MODERATOR,ProfileRole.PUBLISHER);
        return ResponseEntity.ok(commentService.delete(id));
    }
    @GetMapping("/getList")
    public ResponseEntity<List<CommentResponseDTO>> getCommentList(@RequestParam("id") String id ) {
        return ResponseEntity.ok(commentService.getArticleCommentList(id));
    }
    @GetMapping("/getByPagination")
    public ResponseEntity<Page<CommentResponseDTO>> getPageRegion(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                                   @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                                   @RequestHeader("Authorization") String auth){
        JwtUtil.getJwtDTO(auth, ProfileRole.ADMIN);

        return ResponseEntity.ok(commentService.getPagination(page,size));
    }
    @PostMapping("/filter")
    public ResponseEntity<Page<CommentShortInfoDTO>> filter(@RequestBody CommentCustomDTO dto,
                                                            @RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(commentService.filter(dto, page, size));
    }
    @GetMapping("/getListReplyId")
    public ResponseEntity<List<CommentResponseDTO>> getCommentListReplyById(@RequestParam("id") Integer id ) {
        return ResponseEntity.ok(commentService.getCommentReplayList(id));
    }

}
