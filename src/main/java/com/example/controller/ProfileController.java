package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.dto.jwt.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.service.ProfileService;
import com.example.util.JwtSecurityUtil;
import com.example.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping({"", "/"})
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto,
                                             @RequestHeader("Authorization") String authorization) {
       JwtDTO jwtDTO = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.create(dto, jwtDTO.getId()));
    }

    @PutMapping("/updateAdmin")
    public ResponseEntity<ProfileDTO> update(@RequestParam("id") Integer id , @RequestBody ProfileDTO dto,
                                             @RequestHeader("Authorization") String authorization){
        JwtSecurityUtil.authorization(authorization);
        return ResponseEntity.ok(profileService.update(id,dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update2(@PathVariable("id") Integer id , @RequestBody ProfileDTO dto ){
        return ResponseEntity.ok(profileService.update(id,dto));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProfileDTO>> pagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                       @RequestParam(value = "size",defaultValue = "10") int size,
                                                       @RequestHeader("Authorization") String auth) {
        JwtSecurityUtil.authorization(auth);
        Page<ProfileDTO> dtoPage = profileService.getAll(page,size);
        return ResponseEntity.ok(dtoPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String auth) {
        JwtSecurityUtil.authorization(auth);
        return ResponseEntity.ok(profileService.deleteById(id));
    }
}
