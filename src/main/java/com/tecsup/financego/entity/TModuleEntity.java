package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.ModuleDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_module")
public class TModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_code", nullable = false, length = 100)
    private String code;

    @Column(name = "t_description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private TCourseEntity course;

    @ManyToOne
    @JoinColumn(name = "id_theme")
    private TThemeEntity theme;

    @OneToMany(mappedBy = "module")
    private Set<TEvaluationEntity> evaluations = new LinkedHashSet<>();

    public TModuleEntity(ModuleDto module, TCourseEntity course, TThemeEntity theme) {
        this.code = module.getCode();
        this.description = module.getDescription();
        this.course = course;
        this.theme = theme;
    }

    public ModuleDto toDto() {
        ModuleDto moduleDto = new ModuleDto();
        moduleDto.setId(this.id);
        moduleDto.setCode(this.code);
        moduleDto.setDescription(this.description);
        moduleDto.setCourse(this.course.toDto());
        if(this.theme != null) moduleDto.setTheme(this.theme.toDto());
        return moduleDto;
    }

    public void update(ModuleDto moduleDto) {
        this.code = moduleDto.getCode();
        this.description = moduleDto.getDescription();
    }

}
