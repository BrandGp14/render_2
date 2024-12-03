package com.tecsup.financego.service;

import com.tecsup.financego.common.type.CommentaryDto;
import com.tecsup.financego.entity.TCommentaryEntity;
import com.tecsup.financego.entity.TCourseEntity;
import com.tecsup.financego.entity.TUserEntity;
import com.tecsup.financego.repository.TCommentaryRepository;
import com.tecsup.financego.repository.TCourseRepository;
import com.tecsup.financego.repository.TUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentaryService {

    private final TCommentaryRepository commentaryRepository;
    private final TCourseRepository courseRepository;
    private final TUserRepository userRepository;

    // Obtener todos los comentarios
    public List<CommentaryDto> getAll() {
        List<TCommentaryEntity> commentaryEntities = commentaryRepository.findAll();
        return commentaryEntities.stream().map(TCommentaryEntity::toDto).toList();
    }

    // Obtener un comentario por ID
    public CommentaryDto get(Long id) {
        Optional<TCommentaryEntity> commentaryEntityOptional = commentaryRepository.findById(id);
        return commentaryEntityOptional.map(TCommentaryEntity::toDto).orElse(null);
    }

    // Crear un nuevo comentario
    public CommentaryDto create(CommentaryDto commentaryDto) {
        TCourseEntity course = courseRepository.findById(commentaryDto.getCourse().getId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));
        TUserEntity user = userRepository.findById(commentaryDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));


        TCommentaryEntity commentaryEntity = new TCommentaryEntity(commentaryDto, course, user);

        // Aquí ya no es necesario llamar a 'setCreationDate()' ya que el campo 'dateCommentary'
        // se inicializa con 'Instant.now()' en el constructor de la entidad.

        commentaryEntity = commentaryRepository.save(commentaryEntity);
        return commentaryEntity.toDto();
    }

    // Actualizar un comentario existente
    public CommentaryDto update(Long id, CommentaryDto commentaryDto) {
        TCommentaryEntity commentaryEntity = commentaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentario no encontrado"));

        commentaryEntity.update(commentaryDto); // Actualiza la descripción y la fecha de actualización
        commentaryEntity = commentaryRepository.save(commentaryEntity); // Guardamos el comentario actualizado
        return commentaryEntity.toDto();
    }

    // Eliminar un comentario por su ID
    public void delete(Long id) {
        if (!commentaryRepository.existsById(id)) {
            throw new IllegalArgumentException("Comentario no encontrado");
        }
        commentaryRepository.deleteById(id);
    }
}
