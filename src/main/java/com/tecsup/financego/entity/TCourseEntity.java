package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.CourseDto;
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
@Table(name = "t_course")
public class TCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_code", nullable = false)
    private String code;

    @Column(name = "t_description", length = 250)
    private String description;

    @Column(name = "t_content", length = 250)
    private String content;

    @OneToMany(mappedBy = "course")
    private Set<TModuleEntity> modules = new LinkedHashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<TProgressEntity> progresses = new LinkedHashSet<>();

    public TCourseEntity(CourseDto tCourseDto) {
        this.code = tCourseDto.getCode();
        this.description = tCourseDto.getDescription();
        this.content = tCourseDto.getContent();
    }

    public CourseDto toDto() {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(this.id);
        courseDto.setCode(this.code);
        courseDto.setDescription(this.description);
        courseDto.setContent(this.content);
        return courseDto;
    }

    public void update(CourseDto tCourseDto) {
        this.code = tCourseDto.getCode();
        this.description = tCourseDto.getDescription();
        this.content = tCourseDto.getContent();
    }
}
