package com.tecsup.financego.security.auth.repository;

import com.tecsup.financego.security.auth.entity.TUserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<TUserAuthEntity, String> {

    @Query("SELECT DISTINCT u FROM TUserAuthEntity u " +
            "WHERE u.email = :username OR u.code = :username")
    Optional<TUserAuthEntity> findByEmailOrCode(String username);
}
