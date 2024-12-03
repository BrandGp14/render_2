package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TProgressRepository extends JpaRepository<TProgressEntity, Long> {
}
