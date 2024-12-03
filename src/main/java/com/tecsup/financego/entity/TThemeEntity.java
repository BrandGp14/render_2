package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.ThemeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_theme")
public class TThemeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_description", nullable = false)
    private String description;

    @Column(name = "t_content", nullable = false)
    private String content;

    public TThemeEntity(ThemeDto theme) {
       this.description = theme.getDescription();
       this.content = theme.getContent();
    }

    public ThemeDto toDto() {
        ThemeDto theme = new ThemeDto();
        theme.setId(this.id);
        theme.setDescription(this.description);
        theme.setContent(this.content);
        return theme;
    }

    public void update(ThemeDto theme) {
        this.description = theme.getDescription();
        this.content = theme.getContent();
    }


}
