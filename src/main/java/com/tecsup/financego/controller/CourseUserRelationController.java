package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.CourseUserRelationDto;
import com.tecsup.financego.service.CourseUserRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-user-relations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseUserRelationController {

    private final CourseUserRelationService courseUserRelationService;

    // Obtener todos los Course User Relations
    @GetMapping
    public ApiResponse<List<CourseUserRelationDto>> getAllCourseUserRelations() {
        return new ApiResponse<List<CourseUserRelationDto>>().toSuccess(courseUserRelationService.getAll());
    }

    // Buscar un Course User Relation por ID
    @GetMapping("/{id}")
    public ApiResponse<CourseUserRelationDto> getCourseUserRelation(@PathVariable Long id) {
        return new ApiResponse<CourseUserRelationDto>().toSuccess(courseUserRelationService.get(id));
    }

    // Crear un nuevo Course User Relation
    @PostMapping
    public ApiResponse<CourseUserRelationDto> createCourseUserRelation(@RequestBody CourseUserRelationDto courseUserRelationDto) {
        return new ApiResponse<CourseUserRelationDto>().toSuccess(courseUserRelationService.create(courseUserRelationDto));
    }

    // Actualizar un Course User Relation existente
    @PutMapping("/{id}")
    public ApiResponse<CourseUserRelationDto> updateCourseUserRelation(@PathVariable Long id, @RequestBody CourseUserRelationDto courseUserRelationDto) {
        return new ApiResponse<CourseUserRelationDto>().toSuccess(courseUserRelationService.update(id, courseUserRelationDto));
    }

    // Eliminar un Course User Relation
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCourseUserRelation(@PathVariable Long id) {
        courseUserRelationService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
