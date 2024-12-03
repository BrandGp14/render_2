package com.tecsup.financego.service;

import com.tecsup.financego.common.type.CourseRateDto;
import com.tecsup.financego.entity.TCourseRateEntity;
import com.tecsup.financego.entity.TEvaluationEntity;
import com.tecsup.financego.entity.TUserEntity;
import com.tecsup.financego.repository.TCourseRateRepository;
import com.tecsup.financego.repository.TEvaluationRepository;
import com.tecsup.financego.repository.TUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseRateService {

    private final TCourseRateRepository courseRateRepository;
    private final TEvaluationRepository evaluationRepository;
    private final TUserRepository userRepository;

    // Obtener todos los Course Rates
    public List<CourseRateDto> getAll() {
        List<TCourseRateEntity> courseRateEntities = courseRateRepository.findAll();
        return courseRateEntities.stream().map(TCourseRateEntity::toDto).toList();
    }

    // Obtener un Course Rate por ID
    public CourseRateDto get(Long id) {
        Optional<TCourseRateEntity> courseRateEntityOptional = courseRateRepository.findById(id);
        return courseRateEntityOptional.map(TCourseRateEntity::toDto).orElse(null);
    }

    // Crear un nuevo Course Rate
    public CourseRateDto create(CourseRateDto courseRateDto) {
        // Validación de los puntos
        if (courseRateDto.getPoints() < 0 || courseRateDto.getPoints() > 20) {
            throw new IllegalArgumentException("Los puntos deben estar entre 0 y 20");
        }

        TEvaluationEntity evaluation = evaluationRepository.findById(courseRateDto.getEvaluation().getId())
                .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada"));
        TUserEntity user = userRepository.findById(courseRateDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        TCourseRateEntity courseRateEntity = new TCourseRateEntity(courseRateDto, evaluation, user);
        courseRateEntity = courseRateRepository.save(courseRateEntity);
        return courseRateEntity.toDto();
    }

    // Actualizar un Course Rate existente
    public CourseRateDto update(Long id, CourseRateDto courseRateDto) {
        // Validación de los puntos
        if (courseRateDto.getPoints() < 0 || courseRateDto.getPoints() > 20) {
            throw new IllegalArgumentException("Los puntos deben estar entre 0 y 20");
        }

        TCourseRateEntity courseRateEntity = courseRateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course Rate no encontrado"));

        courseRateEntity.update(courseRateDto);
        courseRateEntity = courseRateRepository.save(courseRateEntity);
        return courseRateEntity.toDto();
    }

    // Eliminar un Course Rate por su ID
    public void delete(Long id) {
        if (!courseRateRepository.existsById(id)) {
            throw new IllegalArgumentException("Course Rate no encontrado");
        }
        courseRateRepository.deleteById(id);
    }
}
