package com.tecsup.financego.common.type;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseRateDto {

    private Long id;
    private double points;
    private int attempt;
    private LocalDateTime dateRealization;
    private EvaluationDto evaluation;
    private UserDto user;
}
