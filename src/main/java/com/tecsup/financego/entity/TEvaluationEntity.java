package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.EvaluationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_evaluation")
public class TEvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_code", nullable = false, length = 100)
    private String code;

    @Column(name = "t_description", nullable = false)
    private String description;

    @Column(name = "n_duration", nullable = false)
    private int duration = 0;

    @Column(name = "t_content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "id_module", nullable = false)
    private TModuleEntity module;

    public TEvaluationEntity(EvaluationDto evaluationDto, TModuleEntity module) {
        this.code = evaluationDto.getCode();
        this.description = evaluationDto.getDescription();
        this.duration = evaluationDto.getDuration();
        this.content = evaluationDto.getContent();
        this.module = module;
    }

    public EvaluationDto toDto() {
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluationDto.setId(this.id);
        evaluationDto.setCode(this.code);
        evaluationDto.setDescription(this.description);
        evaluationDto.setDuration(this.duration);
        evaluationDto.setContent(this.content);

        // Asegurarse de asignar el módulo al DTO
        if (this.module != null) {
            evaluationDto.setModule(this.module.toDto());
        }

        return evaluationDto;
    }

    public void update(EvaluationDto evaluationDto) {
        this.code = evaluationDto.getCode();
        this.description = evaluationDto.getDescription();
        this.duration = evaluationDto.getDuration();
        this.content = evaluationDto.getContent();
    }

}
