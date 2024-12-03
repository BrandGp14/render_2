package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.EvaluationDto;
import com.tecsup.financego.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EvaluationController {

    private final EvaluationService evaluationService;

    // Obtener todas las Evaluaciones
    @GetMapping
    public ApiResponse<List<EvaluationDto>> getAllEvaluations() {
        return new ApiResponse<List<EvaluationDto>>().toSuccess(evaluationService.getAll());
    }

    // Buscar una Evaluación por ID
    @GetMapping("/{id}")
    public ApiResponse<EvaluationDto> getEvaluation(@PathVariable Long id) {
        return new ApiResponse<EvaluationDto>().toSuccess(evaluationService.get(id));
    }

    // Crear una nueva Evaluación
    @PostMapping
    public ApiResponse<EvaluationDto> createEvaluation(@RequestBody EvaluationDto evaluationDto) {
        return new ApiResponse<EvaluationDto>().toSuccess(evaluationService.create(evaluationDto));
    }

    // Actualizar una Evaluación existente
    @PutMapping("/{id}")
    public ApiResponse<EvaluationDto> updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDto evaluationDto) {
        return new ApiResponse<EvaluationDto>().toSuccess(evaluationService.update(id, evaluationDto));
    }

    // Eliminar una Evaluación
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
