package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.ProgressDto;
import com.tecsup.financego.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProgressController {

    private final ProgressService progressService;

    // Obtener todos los progresos
    @GetMapping
    public ApiResponse<List<ProgressDto>> getAllProgress() {
        return new ApiResponse<List<ProgressDto>>().toSuccess(progressService.getAll());
    }

    // Buscar progreso por ID
    @GetMapping("/{id}")
    public ApiResponse<ProgressDto> getProgress(@PathVariable Long id) {
        return new ApiResponse<ProgressDto>().toSuccess(progressService.get(id));
    }

    // Crear un nuevo progreso
    @PostMapping
    public ApiResponse<ProgressDto> createProgress(@RequestBody ProgressDto progressDto) {
        return new ApiResponse<ProgressDto>().toSuccess(progressService.create(progressDto));
    }

    // Actualizar un progreso existente
    @PutMapping("/{id}")
    public ApiResponse<ProgressDto> updateProgress(@PathVariable Long id, @RequestBody ProgressDto progressDto) {
        return new ApiResponse<ProgressDto>().toSuccess(progressService.update(id, progressDto));
    }

    // Eliminar un progreso
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProgress(@PathVariable Long id) {
        progressService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
