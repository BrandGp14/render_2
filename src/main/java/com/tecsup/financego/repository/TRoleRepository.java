package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TRoleRepository extends JpaRepository<TRoleEntity, Long> {

    Optional<TRoleEntity> findByDescription(String description);
}
