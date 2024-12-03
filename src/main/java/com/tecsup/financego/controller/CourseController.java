package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.CourseDto;
import com.tecsup.financego.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/search")
    public ApiResponse<List<CourseDto>> search(@RequestParam(value = "description", required = false) String description,
                                               @RequestParam(value = "enabled", required = false) String code,
                                               @RequestParam(value = "duration", required = false) String content) {
        return new ApiResponse<List<CourseDto>>().toSuccess(courseService.search(description, code, content));
    }

    @GetMapping("/{id}")
    public ApiResponse<CourseDto> getCourse(@PathVariable(value = "id") Long id) {
        return new ApiResponse<CourseDto>().toSuccess(courseService.get(id));
    }

    @GetMapping("/random")
    public ApiResponse<List<CourseDto>> getThreeResultRandom() {
        return new ApiResponse<List<CourseDto>>().toSuccess(courseService.getThreeResultRandom());
    }

    // Método para crear un curso
    @PostMapping
    public ApiResponse<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return new ApiResponse<CourseDto>().toSuccess(courseService.create(courseDto));
    }

    // Método para actualizar un curso
    @PutMapping("/{id}")
    public ApiResponse<CourseDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        return new ApiResponse<CourseDto>().toSuccess(courseService.update(id, courseDto));
    }

    // Método para eliminar un curso
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
