package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.RoleDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_role")
public class TRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_description", nullable = false, length = 45)
    private String description;

    public TRoleEntity(RoleDto roleDto) {
        this.description = roleDto.getDescription();
    }

    public RoleDto toDto() {
        RoleDto rolesDto = new RoleDto();
        rolesDto.setId(this.id);
        rolesDto.setDescription(this.description);
        return rolesDto;
    }

    public void update(RoleDto rolesDto) {
        this.description = rolesDto.getDescription();
    }
}