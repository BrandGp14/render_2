package com.tecsup.financego.common.type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProgressDto {

    private Long id;
    private int percentage;
    private String dateStart;
    private String dateEnd;
    private String status;
    private CourseDto course;
    private UserDto user;

}
