package com.example.util;

import com.example.dto.jwt.JwtDTO;
import com.example.enums.ProfileRole;
import com.example.exps.MethodNotAllowedException;

public class JwtSecurityUtil {
    public static void authorization(String auth){
        String[] split = auth.split(" ");
        String jwt = split[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            throw new MethodNotAllowedException("Method not allowed: ");
        }
    }
}
