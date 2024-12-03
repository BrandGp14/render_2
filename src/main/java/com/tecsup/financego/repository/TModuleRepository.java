package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TModuleRepository extends JpaRepository<TModuleEntity, Long> {

    @Query("SELECT DISTINCT m FROM TModuleEntity m " +
            "ORDER BY RAND() LIMIT 4")
    List<TModuleEntity> getFourResultRandom();
}
