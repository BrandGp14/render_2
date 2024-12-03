package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.ProgressDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_progress")
public class TProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "n_percentage", nullable = false, length = 3)
    private int percentage = 0;

    @Column(name = "d_start", nullable = false)
    private Instant dateStart = Instant.now();

    @Column(name = "d_end", nullable = false)
    private Instant dateEnd = Instant.now();

    @Column(name = "t_status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_course")
    private TCourseEntity course;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private TUserEntity user;

    public TProgressEntity(ProgressDto progressDto, TCourseEntity course, TUserEntity user) {
        this.percentage = progressDto.getPercentage();
        this.status = progressDto.getStatus();
        this.course = course;
        this.user = user;
    }

    public ProgressDto toDto() {
        ProgressDto progressDto = new ProgressDto();
        progressDto.setId(this.id);
        progressDto.setPercentage(this.percentage);
        progressDto.setDateStart(this.dateStart.toString());
        progressDto.setDateEnd(this.dateEnd.toString());
        progressDto.setStatus(this.status);
        progressDto.setCourse(this.course.toDto());
        progressDto.setUser(this.user.toDto());
        return progressDto;
    }

    public void update(ProgressDto progressDto) {
        this.percentage = progressDto.getPercentage();
        this.dateEnd = Instant.parse(progressDto.getDateEnd());
        this.status = progressDto.getStatus();
    }

}
