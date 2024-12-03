package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TCommentaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCommentaryRepository extends JpaRepository<TCommentaryEntity, Long> {
    
}
