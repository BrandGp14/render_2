package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TThemeRepository extends JpaRepository<TThemeEntity, Long> {

    @Query("SELECT t FROM TThemeEntity t " +
            "WHERE (:description IS NULL OR t.description LIKE %:description%) " +
            "AND (:content IS NULL OR t.content LIKE %:content%)")
    List<TThemeEntity> searchByParameters(@Param("description") String description,
                                          @Param("content") String content);
}
