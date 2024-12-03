package com.tecsup.financego.service;

import com.tecsup.financego.common.type.RoleDto;
import com.tecsup.financego.entity.TRoleEntity;
import com.tecsup.financego.repository.TRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final TRoleRepository roleRepository;

    /**
     * Buscar roles por descripción.
     *
     * @param description Descripción del rol.
     * @return Lista de roles que coinciden con la descripción.
     */
    public List<RoleDto> search(String description) {
        List<TRoleEntity> roleEntityList;
        if (description != null) {
            roleEntityList = roleRepository.findByDescription(description).stream().toList();
        } else {
            roleEntityList = roleRepository.findAll();
        }
        return roleEntityList.stream().map(TRoleEntity::toDto).toList();
    }

    /**
     * Obtener un rol por su ID.
     *
     * @param id ID del rol.
     * @return Objeto RoleDto correspondiente o null si no se encuentra.
     */
    public RoleDto get(Long id) {
        Optional<TRoleEntity> roleEntityOptional = roleRepository.findById(id);
        return roleEntityOptional.map(TRoleEntity::toDto).orElse(null);
    }

    /**
     * Crear un nuevo rol.
     *
     * @param roleDto Datos del rol a crear.
     * @return El objeto RoleDto creado.
     */
    public RoleDto create(RoleDto roleDto) {
        TRoleEntity roleEntity = new TRoleEntity(roleDto);
        roleEntity = roleRepository.save(roleEntity);
        return roleEntity.toDto();
    }

    /**
     * Actualizar un rol existente.
     *
     * @param id       ID del rol a actualizar.
     * @param roleDto  Datos actualizados del rol.
     * @return El objeto RoleDto actualizado o null si no se encuentra.
     */
    public RoleDto update(Long id, RoleDto roleDto) {
        Optional<TRoleEntity> roleEntityOptional = roleRepository.findById(id);
        if (roleEntityOptional.isPresent()) {
            TRoleEntity roleEntity = roleEntityOptional.get();
            roleEntity.update(roleDto);
            roleEntity = roleRepository.save(roleEntity);
            return roleEntity.toDto();
        }
        return null;
    }

    /**
     * Eliminar un rol por su ID.
     *
     * @param id ID del rol a eliminar.
     */
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
