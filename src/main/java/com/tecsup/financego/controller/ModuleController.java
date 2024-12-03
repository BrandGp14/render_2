package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.ModuleDto;
import com.tecsup.financego.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    // Buscar módulos
    @GetMapping("/search")
    public ApiResponse<List<ModuleDto>> search(@RequestParam(value = "description", required = false) String description,
                                               @RequestParam(value = "enabled", required = false) String code,
                                               @RequestParam(value = "duration", required = false) String content) {
        return new ApiResponse<List<ModuleDto>>().toSuccess(moduleService.search(description, code, content));
    }

    // Obtener 3 resultados aleatorios
    @GetMapping("/random")
    public ApiResponse<List<ModuleDto>> getThreeResultRandom() {
        return new ApiResponse<List<ModuleDto>>().toSuccess(moduleService.getFourResultRandom());
    }

    // Obtener todos los módulos
    @GetMapping
    public ApiResponse<List<ModuleDto>> getAllModules() {
        return new ApiResponse<List<ModuleDto>>().toSuccess(moduleService.getAll());
    }

    // Obtener un módulo por ID
    @GetMapping("/{id}")
    public ApiResponse<ModuleDto> getModule(@PathVariable Long id) {
        return new ApiResponse<ModuleDto>().toSuccess(moduleService.get(id));
    }

    // Crear un nuevo módulo
    @PostMapping
    public ApiResponse<ModuleDto> createModule(@RequestBody ModuleDto moduleDto) {
        return new ApiResponse<ModuleDto>().toSuccess(moduleService.create(moduleDto));
    }

    // Actualizar un módulo existente
    @PutMapping("/{id}")
    public ApiResponse<ModuleDto> updateModule(@PathVariable Long id, @RequestBody ModuleDto moduleDto) {
        return new ApiResponse<ModuleDto>().toSuccess(moduleService.update(id, moduleDto));
    }

    // Eliminar un módulo
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteModule(@PathVariable Long id) {
        moduleService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
