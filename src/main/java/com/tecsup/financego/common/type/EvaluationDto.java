package com.tecsup.financego.common.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationDto {

    private Long id;
    private String code;
    private String description;
    private int duration;
    private String content;
    private ModuleDto module;
}