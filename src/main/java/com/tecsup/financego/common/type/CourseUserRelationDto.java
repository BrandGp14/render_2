package com.tecsup.financego.common.type;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class CourseUserRelationDto {

    private Long id;
    private UserDto user;
    private CourseDto course;
    private String dateInscription;
    private String timeInscription;
}

