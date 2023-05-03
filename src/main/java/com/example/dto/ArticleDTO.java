package com.example.dto;

import com.example.enums.ProfileRole;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ArticleDTO {

    private String id;
    @Size(min = 10, max = 225, message = "Title must be between 10 and 225 characters")
    private String title;
    private String description;
    private String content;
    private Long shared_count;
    private String imageId;
    private Integer regionId;
    private Integer categoryId;
    private Integer moderatorId;
    private Integer publisherId;
    private ProfileRole status; //(Published,NotPublished)
    private LocalDateTime created_date;
    private LocalDateTime published_date;
    private Boolean visible;
    private Long view_count;

//    @Getter
//    @Setter
//    public class ArticleRequestDTO {
//        private String title;
//        private String description;
//        private String content;
//        private Integer attachId;
//        private Integer regionId;
//        private Integer categoryId;
//        private List<Integer> typeList;
//    }
//@NotNull(message = "title required")
//@Size(max = 225, message = "Title must be between 10 and 225 characters")
//private String title;
//    @NotBlank(message = "Field must have some value")
//    private String description;
//    @NotEmpty(message = "Content qani")
//    private String content;
//    private Integer attachId;
//    private Integer regionId;
//    private Integer categoryId;
//    @NotEmpty(message = "Should provide value")
//    private List<Integer> typeList;
}
