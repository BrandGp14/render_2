package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.CommentaryDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_commentary")
public class TCommentaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_description", nullable = false, length = 255)
    private String description;

    @Column(name = "d_commentary", nullable = false)
    private Instant dateCommentary = Instant.now();

    @Column(name = "d_commentary_update", nullable = false)
    private Instant dateCommentaryUpdate = Instant.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private TUserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private TCourseEntity course;

    public TCommentaryEntity(CommentaryDto commentaryDto, TCourseEntity course, TUserEntity user) {
        this.description = commentaryDto.getDescription();
        this.dateCommentary = Instant.parse(commentaryDto.getDateCommentary());
        this.user = user;
        this.course = course;
    }

    public CommentaryDto toDto() {
        CommentaryDto commentaryDto = new CommentaryDto();
        commentaryDto.setId(this.id);
        commentaryDto.setDescription(this.description);
        commentaryDto.setDateCommentary(this.dateCommentary.toString());
        commentaryDto.setUser(this.user.toDto());
        commentaryDto.setCourse(this.course.toDto());
        return commentaryDto;
    }

    public void update(CommentaryDto commentaryDto) {
        // Actualizamos solo la descripción
        this.description = commentaryDto.getDescription();

        // Si la descripción cambia, actualizamos la fecha de actualización
        this.dateCommentaryUpdate = Instant.now();
    }
}
