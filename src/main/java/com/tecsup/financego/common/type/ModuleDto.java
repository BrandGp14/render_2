package com.tecsup.financego.common.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleDto {

    private Long id;
    private String code;
    private String description;
    private CourseDto course;
    private ThemeDto theme;
}