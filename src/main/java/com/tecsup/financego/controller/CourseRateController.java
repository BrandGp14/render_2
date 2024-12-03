package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.CourseRateDto;
import com.tecsup.financego.service.CourseRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-rates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseRateController {

    private final CourseRateService courseRateService;

    // Obtener todos los Course Rates
    @GetMapping
    public ApiResponse<List<CourseRateDto>> getAllCourseRates() {
        return new ApiResponse<List<CourseRateDto>>().toSuccess(courseRateService.getAll());
    }

    // Buscar un Course Rate por ID
    @GetMapping("/{id}")
    public ApiResponse<CourseRateDto> getCourseRate(@PathVariable Long id) {
        return new ApiResponse<CourseRateDto>().toSuccess(courseRateService.get(id));
    }

    // Crear un nuevo Course Rate
    @PostMapping
    public ApiResponse<CourseRateDto> createCourseRate(@RequestBody CourseRateDto courseRateDto) {
        return new ApiResponse<CourseRateDto>().toSuccess(courseRateService.create(courseRateDto));
    }

    // Actualizar un Course Rate existente
    @PutMapping("/{id}")
    public ApiResponse<CourseRateDto> updateCourseRate(@PathVariable Long id, @RequestBody CourseRateDto courseRateDto) {
        return new ApiResponse<CourseRateDto>().toSuccess(courseRateService.update(id, courseRateDto));
    }

    // Eliminar un Course Rate
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCourseRate(@PathVariable Long id) {
        courseRateService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
