package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.CourseUserRelationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_course_user_relation")
public class TCourseUserRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "d_inscription", nullable = false)
    private LocalDate dateInscription = LocalDate.now();

    @Column(name = "h_inscription", nullable = false)
    private LocalTime timeInscription = LocalTime.now();

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private TCourseEntity course;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private TUserEntity user;

    public TCourseUserRelationEntity(CourseUserRelationDto courseUserRelationDto, TUserEntity user, TCourseEntity course) {
        this.dateInscription = LocalDate.parse(courseUserRelationDto.getDateInscription());
        this.timeInscription = LocalTime.parse(courseUserRelationDto.getTimeInscription());
        this.user = user;
        this.course = course;
    }

    public CourseUserRelationDto toDto() {

        CourseUserRelationDto courseUserRelationDto = new CourseUserRelationDto();
        courseUserRelationDto.setId(this.id);
        courseUserRelationDto.setDateInscription(this.dateInscription.toString());
        courseUserRelationDto.setTimeInscription(this.timeInscription.toString());
        courseUserRelationDto.setUser(this.user.toDto());
        courseUserRelationDto.setCourse(this.course.toDto());

        return courseUserRelationDto;
    }

    public void update(CourseUserRelationDto courseUserRelationDto) {
        courseUserRelationDto.setDateInscription(this.dateInscription.toString());
        courseUserRelationDto.setTimeInscription(this.timeInscription.toString());
    }

}
