package com.tecsup.financego.common.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentaryDto {
    private Long id;
    private String description;
    private String dateCommentary;
    private UserDto user;
    private CourseDto course;
}
