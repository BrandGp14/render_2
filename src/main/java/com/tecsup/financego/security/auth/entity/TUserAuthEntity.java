package com.tecsup.financego.security.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tecsup.financego.security.auth.reference.EPermissionReference;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Entity
@Table(name = "t_user")
public class TUserAuthEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_email", unique = true, nullable = false)
    private String email;

    @Column(name = "t_code", columnDefinition = "VARCHAR(10)", unique = true, nullable = false)
    private String code;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "t_password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private TRoleAuthEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<String> _permissions = Arrays.stream(EPermissionReference.values())
                .flatMap(ePermissionReference -> role.getAuthority().equals(ePermissionReference.name())
                        ? ePermissionReference.getPermissions().stream()
                        : Stream.empty()
                ).toList();

        return Stream.concat(Stream.of(role.getAuthority()), _permissions.stream()).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
