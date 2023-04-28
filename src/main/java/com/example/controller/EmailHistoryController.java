package com.example.controller;

import com.example.dto.EmailHistoryDTO;
import com.example.enums.ProfileRole;
import com.example.service.EmailHistoryService;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emailHistory")
public class EmailHistoryController {
      @Autowired
    private EmailHistoryService emailHistoryService;
  @GetMapping("/getEmail")
    public ResponseEntity<List<EmailHistoryDTO>> getEmail(@RequestBody String email){
      return ResponseEntity.ok(emailHistoryService.getEmail(email));
  }
    @GetMapping("/getDate")
    public ResponseEntity<List<EmailHistoryDTO>> getEmail(@RequestParam("date") LocalDate date){
        return ResponseEntity.ok(emailHistoryService.getDate(date));
    }
   @GetMapping("/getByPegination")
    public ResponseEntity<Page<EmailHistoryDTO>> getPage(@RequestParam("id") Integer id,
                                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                                         @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                         @RequestHeader("Authorization") String auth){
       JwtUtil.getJwtDTO(auth,ProfileRole.ADMIN);
       return ResponseEntity.ok(emailHistoryService.getPagination(id,page,size));
   }
}
