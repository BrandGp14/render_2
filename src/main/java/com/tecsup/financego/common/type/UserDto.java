package com.tecsup.financego.common.type;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String code;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private LocalDate dateCreated;
    private RoleDto role;

}
