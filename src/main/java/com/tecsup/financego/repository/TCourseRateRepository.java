package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TCourseRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCourseRateRepository extends JpaRepository<TCourseRateEntity, Long> {
}
