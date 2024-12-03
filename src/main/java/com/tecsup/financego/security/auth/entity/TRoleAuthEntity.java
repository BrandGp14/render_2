package com.tecsup.financego.security.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Table(name = "t_role")
public class TRoleAuthEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_description", nullable = false, unique = true)
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }
}
