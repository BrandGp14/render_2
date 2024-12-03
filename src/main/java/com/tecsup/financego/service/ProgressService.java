package com.tecsup.financego.service;

import com.tecsup.financego.common.type.ProgressDto;
import com.tecsup.financego.entity.TCourseEntity;
import com.tecsup.financego.entity.TProgressEntity;
import com.tecsup.financego.entity.TUserEntity;
import com.tecsup.financego.repository.TCourseRepository;
import com.tecsup.financego.repository.TProgressRepository;
import com.tecsup.financego.repository.TUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final TProgressRepository progressRepository;
    private final TCourseRepository courseRepository;
    private final TUserRepository userRepository;

    // Obtener todos los progresos
    public List<ProgressDto> getAll() {
        List<TProgressEntity> progressEntities = progressRepository.findAll();
        return progressEntities.stream().map(TProgressEntity::toDto).toList();
    }

    // Obtener un progreso por su ID
    public ProgressDto get(Long id) {
        Optional<TProgressEntity> progressEntityOptional = progressRepository.findById(id);
        return progressEntityOptional.map(TProgressEntity::toDto).orElse(null);
    }

    // Crear un nuevo progreso
    public ProgressDto create(ProgressDto progressDto) {
        TCourseEntity course = courseRepository.findById(progressDto.getCourse().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
        TUserEntity user = userRepository.findById(progressDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        TProgressEntity progressEntity = new TProgressEntity(progressDto, course, user);
        progressEntity = progressRepository.save(progressEntity);
        return progressEntity.toDto();
    }

    // Actualizar un progreso existente
    public ProgressDto update(Long id, ProgressDto progressDto) {
        TProgressEntity progressEntity = progressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Progreso no encontrado"));

        progressEntity.update(progressDto);
        progressEntity = progressRepository.save(progressEntity);
        return progressEntity.toDto();
    }

    // Eliminar un progreso por su ID
    public void delete(Long id) {
        if (!progressRepository.existsById(id)) {
            throw new IllegalArgumentException("Progreso no encontrado");
        }
        progressRepository.deleteById(id);
    }
}
