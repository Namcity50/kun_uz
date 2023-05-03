package com.example.dto.region;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionResponseDTO {
    private Integer id;
    private String key;
    private String lang;

    public RegionResponseDTO(Integer id, String lang) {
        this.id = id;

        this.lang = lang;
    }
}
