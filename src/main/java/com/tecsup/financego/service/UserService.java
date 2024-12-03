package com.tecsup.financego.service;

import com.tecsup.financego.common.type.UserDto;
import com.tecsup.financego.entity.TRoleEntity;
import com.tecsup.financego.entity.TUserEntity;
import com.tecsup.financego.error.BadRequestException;
import com.tecsup.financego.repository.TRoleRepository;
import com.tecsup.financego.repository.TUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TUserRepository userRepository;
    private final TRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // Registro de usuario
    @Transactional
    public UserDto register(UserDto userDto) {
        Optional<TRoleEntity> role = roleRepository.findByDescription(userDto.getRole().getDescription());

        if (role.isEmpty()) throw new BadRequestException("Role not found");

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        TUserEntity userEntity = new TUserEntity(userDto, role.get());
        userRepository.save(userEntity);

        return userEntity.toDto();
    }

    // Obtener usuario por ID
    public UserDto getUser(Long id) {
        return userRepository.findById(id).map(TUserEntity::toDto).orElseThrow();
    }

    // Obtener usuario autenticado
    public UserDto getMe(Principal principal) {
        TUserEntity userEntity = userRepository.findByEmail(principal.getName()).orElseThrow();
        return userEntity.toDto();
    }

    // Obtener todos los usuarios
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(TUserEntity::toDto).collect(Collectors.toList());
    }

    // Crear un usuario (adicional al registro)
    public UserDto createUser(UserDto userDto) {
        return register(userDto); // Reutiliza la lógica de registro
    }

    // Actualizar un usuario
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        TUserEntity existingUser = userRepository.findById(id).orElseThrow();

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        existingUser.update(userDto);
        userRepository.save(existingUser);

        return existingUser.toDto();
    }

    // Eliminar un usuario
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
