package com.tecsup.financego.security.auth.repository;

import com.tecsup.financego.security.auth.entity.TRoleAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<TRoleAuthEntity, String> {

    List<TRoleAuthEntity> findByDescriptionIsIn(List<String> descriptions);
}
