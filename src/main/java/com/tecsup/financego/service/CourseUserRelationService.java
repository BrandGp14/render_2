package com.tecsup.financego.service;

import com.tecsup.financego.common.type.CourseUserRelationDto;
import com.tecsup.financego.entity.TCourseEntity;
import com.tecsup.financego.entity.TCourseUserRelationEntity;
import com.tecsup.financego.entity.TUserEntity;
import com.tecsup.financego.repository.TCourseUserRelationRepository;
import com.tecsup.financego.repository.TCourseRepository;
import com.tecsup.financego.repository.TUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseUserRelationService {

    private final TCourseUserRelationRepository courseUserRelationRepository;
    private final TCourseRepository courseRepository;
    private final TUserRepository userRepository;

    // Obtener todos los Course User Relations
    public List<CourseUserRelationDto> getAll() {
        List<TCourseUserRelationEntity> courseUserRelationEntities = courseUserRelationRepository.findAll();
        return courseUserRelationEntities.stream().map(TCourseUserRelationEntity::toDto).toList();
    }

    // Obtener un Course User Relation por ID
    public CourseUserRelationDto get(Long id) {
        Optional<TCourseUserRelationEntity> courseUserRelationEntityOptional = courseUserRelationRepository.findById(id);
        return courseUserRelationEntityOptional.map(TCourseUserRelationEntity::toDto).orElse(null);
    }

    // Crear un nuevo Course User Relation
    public CourseUserRelationDto create(CourseUserRelationDto courseUserRelationDto) {
        TCourseEntity course = courseRepository.findById(courseUserRelationDto.getCourse().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
        TUserEntity user = userRepository.findById(courseUserRelationDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        TCourseUserRelationEntity courseUserRelationEntity = new TCourseUserRelationEntity(courseUserRelationDto, user, course);
        courseUserRelationEntity = courseUserRelationRepository.save(courseUserRelationEntity);
        return courseUserRelationEntity.toDto();
    }

    // Actualizar un Course User Relation existente
    public CourseUserRelationDto update(Long id, CourseUserRelationDto courseUserRelationDto) {
        TCourseUserRelationEntity courseUserRelationEntity = courseUserRelationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course User Relation no encontrado"));

        courseUserRelationEntity.update(courseUserRelationDto);
        courseUserRelationEntity = courseUserRelationRepository.save(courseUserRelationEntity);
        return courseUserRelationEntity.toDto();
    }

    // Eliminar un Course User Relation por su ID
    public void delete(Long id) {
        if (!courseUserRelationRepository.existsById(id)) {
            throw new IllegalArgumentException("Course User Relation no encontrado");
        }
        courseUserRelationRepository.deleteById(id);
    }
}
