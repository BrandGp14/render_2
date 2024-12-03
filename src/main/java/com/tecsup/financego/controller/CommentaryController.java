package com.tecsup.financego.controller;

import com.tecsup.financego.common.type.ApiResponse;
import com.tecsup.financego.common.type.CommentaryDto;
import com.tecsup.financego.service.CommentaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentaryController {

    private final CommentaryService commentaryService;

    // Obtener todos los comentarios
    @GetMapping
    public ApiResponse<List<CommentaryDto>> getAllCommentaries() {
        return new ApiResponse<List<CommentaryDto>>().toSuccess(commentaryService.getAll());
    }

    // Buscar un comentario por ID
    @GetMapping("/{id}")
    public ApiResponse<CommentaryDto> getCommentary(@PathVariable Long id) {
        return new ApiResponse<CommentaryDto>().toSuccess(commentaryService.get(id));
    }

    // Crear un nuevo comentario
    @PostMapping
    public ApiResponse<CommentaryDto> createCommentary(@RequestBody CommentaryDto commentaryDto) {
        return new ApiResponse<CommentaryDto>().toSuccess(commentaryService.create(commentaryDto));
    }

    // Actualizar un comentario existente
    @PutMapping("/{id}")
    public ApiResponse<CommentaryDto> updateCommentary(@PathVariable Long id, @RequestBody CommentaryDto commentaryDto) {
        return new ApiResponse<CommentaryDto>().toSuccess(commentaryService.update(id, commentaryDto));
    }

    // Eliminar un comentario
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCommentary(@PathVariable Long id) {
        commentaryService.delete(id);
        return new ApiResponse<Void>().toSuccess(null);
    }
}
