package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.UserDto;
import com.tecsup.financego.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    // Registro de nuevo usuario
    @PostMapping("/register")
    public ApiResponse<UserDto> register(@RequestBody UserDto userDto) {
        return new ApiResponse<UserDto>().toSuccess(userService.register(userDto));
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ApiResponse<UserDto> getUser(@PathVariable Long id) {
        return new ApiResponse<UserDto>().toSuccess(userService.getUser(id));
    }

    // Obtener información del usuario autenticado
    @GetMapping("/me")
    public ApiResponse<UserDto> getMe(Principal principal) {
        return new ApiResponse<UserDto>().toSuccess(userService.getMe(principal));
    }

    // Obtener todos los usuarios
    @GetMapping
    public ApiResponse<List<UserDto>> getAllUsers() {
        return new ApiResponse<List<UserDto>>().toSuccess(userService.getAllUsers());
    }

    // Crear un nuevo usuario (similar a registro)
    @PostMapping
    public ApiResponse<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ApiResponse<UserDto>().toSuccess(userService.createUser(userDto));
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ApiResponse<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ApiResponse<UserDto>().toSuccess(userService.updateUser(id, userDto));
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
