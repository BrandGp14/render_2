package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TCourseRepository extends JpaRepository<TCourseEntity, Long> {

    @Query("SELECT DISTINCT c FROM TCourseEntity c " +
            "WHERE (:description IS NULL OR c.description = :description) " +
            "AND (:code IS NULL OR c.code = :code) " +
            "AND (:content IS NULL OR c.content = :content)")
    List<TCourseEntity> searchByThreeParameters(String description, String code, String content);

    @Query("SELECT c FROM TCourseEntity c " +
            "ORDER BY RAND() LIMIT 3")
    List<TCourseEntity> getThreeResultRandom();
}
