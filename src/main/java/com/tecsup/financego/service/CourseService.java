package com.tecsup.financego.service;

import com.tecsup.financego.common.type.CourseDto;
import com.tecsup.financego.entity.TCourseEntity;
import com.tecsup.financego.repository.TCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final TCourseRepository courseRepository;

    public List<CourseDto> search(String description, String code, String content) {
        List<TCourseEntity> courseEntityList = courseRepository.searchByThreeParameters(description, code, content);
        return courseEntityList.stream().map(TCourseEntity::toDto).toList();
    }

    public CourseDto get(Long id) {
        Optional<TCourseEntity> courseEntityOptional = courseRepository.findById(id);
        return courseEntityOptional.map(TCourseEntity::toDto).orElse(null);
    }

    public List<CourseDto> getThreeResultRandom(){
        List<TCourseEntity> courseDtoList = courseRepository.getThreeResultRandom();
        return courseDtoList.stream().map(TCourseEntity::toDto).toList();
    }

    // Método para crear un nuevo curso
    public CourseDto create(CourseDto courseDto) {
        TCourseEntity courseEntity = new TCourseEntity();
        courseEntity.setCode(courseDto.getCode());
        courseEntity.setDescription(courseDto.getDescription());
        courseEntity.setContent(courseDto.getContent());
        courseEntity = courseRepository.save(courseEntity);
        return courseEntity.toDto();
    }

    // Método para actualizar un curso
    public CourseDto update(Long id, CourseDto courseDto) {
        Optional<TCourseEntity> courseEntityOptional = courseRepository.findById(id);
        if (courseEntityOptional.isPresent()) {
            TCourseEntity courseEntity = courseEntityOptional.get();
            courseEntity.setCode(courseDto.getCode());
            courseEntity.setDescription(courseDto.getDescription());
            courseEntity.setContent(courseDto.getContent());
            courseEntity = courseRepository.save(courseEntity);
            return courseEntity.toDto();
        }
        return null;  // O podrías lanzar una excepción si prefieres
    }

    // Método para eliminar un curso
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
