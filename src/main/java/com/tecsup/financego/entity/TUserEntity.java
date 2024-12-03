package com.tecsup.financego.entity;

import com.tecsup.financego.common.type.UserDto;

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
@Table(name = "t_user", indexes = {@Index(columnList = "t_code,t_email")})
public class TUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "t_code", nullable = false, length = 45)
    private String code;

    @Column(name = "t_name", nullable = false, length = 100)
    private String name;

    @Column(name = "t_last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "t_email", nullable = false, length = 60)
    private String email;

    @Column(name = "t_password", nullable = false, length = 60)
    private String password;

    @Column(name = "t_phone", length = 45)
    private String phone;

    @Column(name = "d_created", nullable = false)
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "h_created", nullable = false)
    private LocalTime timeCreated = LocalTime.now();

    @Column(name = "d_updated", nullable = false)
    private LocalDate dateUpdated = LocalDate.now();

    @Column(name = "h_updated", nullable = false)
        private LocalTime timeUpdated = LocalTime.now();

    @ManyToOne
    @JoinColumn(name = "id_role")
    private TRoleEntity role;

    public TUserEntity(UserDto user, TRoleEntity role) {
        this.code = user.getCode();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.role = role;
    }

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setCode(this.code);
        userDto.setName(this.name);
        userDto.setLastName(this.lastName);
        userDto.setEmail(this.email);
        userDto.setPhone(this.phone);
        userDto.setDateCreated(this.dateCreated);
        userDto.setRole(this.role != null ? this.role.toDto() : null);
        return userDto;
    }

    public void update(UserDto user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.dateUpdated = LocalDate.now();
        this.timeUpdated = LocalTime.now();
    }
}