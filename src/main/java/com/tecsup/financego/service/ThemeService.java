package com.tecsup.financego.service;

import com.tecsup.financego.common.type.ThemeDto;
import com.tecsup.financego.entity.TThemeEntity;
import com.tecsup.financego.repository.TThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final TThemeRepository themeRepository;

    /**
     * Buscar temas por parámetros opcionales.
     *
     * @param description Descripción del tema (opcional).
     * @param content     Contenido del tema (opcional).
     * @return Lista de temas que coinciden con los parámetros.
     */
    public List<ThemeDto> search(String description, String content) {
        List<TThemeEntity> themeEntityList = themeRepository.searchByParameters(description, content);
        return themeEntityList.stream().map(TThemeEntity::toDto).toList();
    }

    /**
     * Obtener un tema por su ID.
     *
     * @param id ID del tema.
     * @return Objeto ThemeDto correspondiente o null si no se encuentra.
     */
    public ThemeDto get(Long id) {
        Optional<TThemeEntity> themeEntityOptional = themeRepository.findById(id);
        return themeEntityOptional.map(TThemeEntity::toDto).orElse(null);
    }

    /**
     * Crear un nuevo tema.
     *
     * @param themeDto Datos del tema a crear.
     * @return El objeto ThemeDto creado.
     */
    public ThemeDto create(ThemeDto themeDto) {
        TThemeEntity themeEntity = new TThemeEntity(themeDto);
        themeEntity = themeRepository.save(themeEntity);
        return themeEntity.toDto();
    }

    /**
     * Actualizar un tema existente.
     *
     * @param id       ID del tema a actualizar.
     * @param themeDto Datos actualizados del tema.
     * @return El objeto ThemeDto actualizado o null si no se encuentra.
     */
    public ThemeDto update(Long id, ThemeDto themeDto) {
        Optional<TThemeEntity> themeEntityOptional = themeRepository.findById(id);
        if (themeEntityOptional.isPresent()) {
            TThemeEntity themeEntity = themeEntityOptional.get();
            themeEntity.update(themeDto);
            themeEntity = themeRepository.save(themeEntity);
            return themeEntity.toDto();
        }
        return null; // Opcional: lanzar excepción si el tema no se encuentra.
    }

    /**
     * Eliminar un tema por su ID.
     *
     * @param id ID del tema a eliminar.
     */
    public void delete(Long id) {
        themeRepository.deleteById(id);
    }
}
