package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.CourseRateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_course_rate")
public class TCourseRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "n_point", nullable = false, length = 4)
    private double points = 0d;

    @Column(name = "t_attempt", nullable = false)
    private int attempt = 0;

    @Column(name = "d_realization", nullable = false)
    private LocalDateTime dateRealization = LocalDateTime.now();  // Cambiar a LocalDateTime

    @ManyToOne
    @JoinColumn(name = "id_evaluation", nullable = false)
    private TEvaluationEntity evaluation;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private TUserEntity user;

    public TCourseRateEntity(CourseRateDto courseRateDto, TEvaluationEntity evaluation, TUserEntity user) {
        // Validación de puntos entre 0 y 20
        if (courseRateDto.getPoints() < 0 || courseRateDto.getPoints() > 20) {
            throw new IllegalArgumentException("Los puntos deben estar entre 0 y 20");
        }

        this.points = courseRateDto.getPoints();
        this.attempt = courseRateDto.getAttempt();
        this.dateRealization = courseRateDto.getDateRealization();  // Cambiar a LocalDateTime
        this.evaluation = evaluation;
        this.user = user;
    }

    public CourseRateDto toDto() {
        CourseRateDto courseRateDto = new CourseRateDto();
        courseRateDto.setId(this.id);
        courseRateDto.setPoints(this.points);
        courseRateDto.setAttempt(this.attempt);
        courseRateDto.setDateRealization(this.dateRealization);  // Pasar LocalDateTime
        courseRateDto.setEvaluation(this.evaluation.toDto());
        courseRateDto.setUser(this.user.toDto());
        return courseRateDto;
    }

    public void update(CourseRateDto courseRateDto) {
        // Validación de los puntos
        if (courseRateDto.getPoints() < 0 || courseRateDto.getPoints() > 20) {
            throw new IllegalArgumentException("Los puntos deben estar entre 0 y 20");
        }

        this.points = courseRateDto.getPoints();
        this.attempt = courseRateDto.getAttempt();
        this.dateRealization = courseRateDto.getDateRealization();  // Cambiar a LocalDateTime
    }
}
