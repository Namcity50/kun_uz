package com.example.dto.region;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionResponseDTO {
    private Integer id;
    private String key;
    private String lang;
}
