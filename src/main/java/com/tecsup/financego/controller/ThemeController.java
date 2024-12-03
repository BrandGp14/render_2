package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.ThemeDto;
import com.tecsup.financego.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theme")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ThemeController {

    private final ThemeService themeService;

    // Endpoint para buscar temas según ciertos parámetros
    @GetMapping("/search")
    public ApiResponse<List<ThemeDto>> search(@RequestParam(value = "description", required = false) String description,
                                              @RequestParam(value = "content", required = false) String content) {
        return new ApiResponse<List<ThemeDto>>().toSuccess(themeService.search(description, content));
    }

    // Endpoint para obtener un tema por su ID
    @GetMapping("/{id}")
    public ApiResponse<ThemeDto> getTheme(@PathVariable(value = "id") Long id) {
        return new ApiResponse<ThemeDto>().toSuccess(themeService.get(id));
    }

    // Endpoint para crear un nuevo tema
    @PostMapping
    public ApiResponse<ThemeDto> createTheme(@RequestBody ThemeDto themeDto) {
        return new ApiResponse<ThemeDto>().toSuccess(themeService.create(themeDto));
    }

    // Endpoint para actualizar un tema existente
    @PutMapping("/{id}")
    public ApiResponse<ThemeDto> updateTheme(@PathVariable(value = "id") Long id,
                                             @RequestBody ThemeDto themeDto) {
        return new ApiResponse<ThemeDto>().toSuccess(themeService.update(id, themeDto));
    }

    // Endpoint para eliminar un tema por su ID
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTheme(@PathVariable(value = "id") Long id) {
        themeService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
