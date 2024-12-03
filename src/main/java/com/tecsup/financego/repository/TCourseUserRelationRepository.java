package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TCourseUserRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCourseUserRelationRepository extends JpaRepository<TCourseUserRelationEntity, Long> {
}
