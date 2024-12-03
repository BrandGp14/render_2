package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.RoleDto;
import com.tecsup.financego.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;

    // Obtener todos los roles
    @GetMapping
    public ApiResponse<List<RoleDto>> getAllRoles() {
        return new ApiResponse<List<RoleDto>>().toSuccess(roleService.search(null));
    }

    // Buscar rol por ID
    @GetMapping("/{id}")
    public ApiResponse<RoleDto> getRole(@PathVariable Long id) {
        return new ApiResponse<RoleDto>().toSuccess(roleService.get(id));
    }

    // Crear un nuevo rol
    @PostMapping
    public ApiResponse<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        return new ApiResponse<RoleDto>().toSuccess(roleService.create(roleDto));
    }

    // Actualizar un rol existente
    @PutMapping("/{id}")
    public ApiResponse<RoleDto> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return new ApiResponse<RoleDto>().toSuccess(roleService.update(id, roleDto));
    }

    // Eliminar un rol
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
