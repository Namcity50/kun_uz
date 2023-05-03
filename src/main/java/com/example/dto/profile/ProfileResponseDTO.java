package com.example.dto.profile;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileResponseDTO {
    private Integer id;
    private String name;
    private String surname;
}
