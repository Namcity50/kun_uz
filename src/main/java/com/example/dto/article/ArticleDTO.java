package com.example.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {

    private String id;
    @Size(min = 1, max = 225, message = "Title must be between 10 and 225 characters")
    private String title;
    private String description;
    private String content;
    private String imageId;
    private Integer regionId;
    private Integer tagId;
    private Integer categoryId;
    private Integer moderatorId;
    private List<Integer> typeList;


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
