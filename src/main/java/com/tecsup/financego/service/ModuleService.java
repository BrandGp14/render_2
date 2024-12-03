package com.tecsup.financego.service;

import com.tecsup.financego.common.type.ModuleDto;
import com.tecsup.financego.entity.TModuleEntity;
import com.tecsup.financego.entity.TCourseEntity;
import com.tecsup.financego.entity.TThemeEntity;
import com.tecsup.financego.repository.TModuleRepository;
import com.tecsup.financego.repository.TCourseRepository;
import com.tecsup.financego.repository.TThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final TModuleRepository moduleRepository;
    private final TCourseRepository courseRepository;
    private final TThemeRepository themeRepository;

    // Buscar módulos por descripción, código o contenido
    public List<ModuleDto> search(String description, String code, String content) {
        List<TModuleEntity> courseEntityList = moduleRepository.findAll();
        return courseEntityList.stream().map(TModuleEntity::toDto).toList();
    }

    // Obtener 4 módulos aleatorios
    public List<ModuleDto> getFourResultRandom() {
        List<TModuleEntity> moduleDtoList = moduleRepository.getFourResultRandom();
        return moduleDtoList.stream().map(TModuleEntity::toDto).toList();
    }

    // Obtener todos los módulos
    public List<ModuleDto> getAll() {
        List<TModuleEntity> moduleList = moduleRepository.findAll();
        return moduleList.stream().map(TModuleEntity::toDto).toList();
    }

    // Obtener un módulo por ID
    public ModuleDto get(Long id) {
        Optional<TModuleEntity> moduleEntity = moduleRepository.findById(id);
        return moduleEntity.map(TModuleEntity::toDto).orElse(null);
    }

    // Crear un nuevo módulo
    public ModuleDto create(ModuleDto moduleDto) {
        Optional<TCourseEntity> courseEntityOptional = courseRepository.findById(moduleDto.getCourse().getId());
        Optional<TThemeEntity> themeEntityOptional = themeRepository.findById(moduleDto.getTheme() != null ? moduleDto.getTheme().getId() : null);

        if (courseEntityOptional.isPresent()) {
            TCourseEntity courseEntity = courseEntityOptional.get();
            TThemeEntity themeEntity = themeEntityOptional.orElse(null); // El tema puede ser nulo

            TModuleEntity moduleEntity = new TModuleEntity(moduleDto, courseEntity, themeEntity);
            moduleEntity = moduleRepository.save(moduleEntity);
            return moduleEntity.toDto();
        }
        return null; // O lanzar una excepción personalizada si no se encuentra el curso
    }

    // Actualizar un módulo existente
    public ModuleDto update(Long id, ModuleDto moduleDto) {
        Optional<TModuleEntity> moduleEntityOptional = moduleRepository.findById(id);
        if (moduleEntityOptional.isPresent()) {
            TModuleEntity moduleEntity = moduleEntityOptional.get();

            // Buscar el curso y el tema asociados
            Optional<TCourseEntity> courseEntityOptional = courseRepository.findById(moduleDto.getCourse().getId());
            Optional<TThemeEntity> themeEntityOptional = themeRepository.findById(moduleDto.getTheme() != null ? moduleDto.getTheme().getId() : null);

            if (courseEntityOptional.isPresent()) {
                TCourseEntity courseEntity = courseEntityOptional.get();
                TThemeEntity themeEntity = themeEntityOptional.orElse(null); // El tema puede ser nulo

                moduleEntity.setCode(moduleDto.getCode());
                moduleEntity.setDescription(moduleDto.getDescription());
                moduleEntity.setCourse(courseEntity);
                moduleEntity.setTheme(themeEntity);
                moduleEntity = moduleRepository.save(moduleEntity);
                return moduleEntity.toDto();
            }
        }
        return null; // O lanzar una excepción personalizada si no se encuentra el módulo o el curso
    }

    // Eliminar un módulo
    public void delete(Long id) {
        Optional<TModuleEntity> moduleEntityOptional = moduleRepository.findById(id);
        moduleEntityOptional.ifPresent(moduleRepository::delete);
    }
}
