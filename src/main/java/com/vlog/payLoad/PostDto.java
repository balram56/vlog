package com.vlog.payLoad;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
@NotEmpty
@Size(min = 2, message = "title should be minimum 2 characters")
    private String title;
@NotEmpty
@Size(min = 4, message = "Description should be minimum 4 characters")
    private String description;
@NotEmpty
@Size(min = 5, message = "Content should be minimum 5 characters")
    private String content;

    private String comment;
    private String message;
}
