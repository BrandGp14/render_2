package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TEvaluationRepository  extends JpaRepository<TEvaluationEntity, Long> {
}
