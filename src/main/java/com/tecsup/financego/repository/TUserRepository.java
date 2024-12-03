package com.tecsup.financego.repository;

import com.tecsup.financego.entity.TUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TUserRepository extends JpaRepository<TUserEntity, Long> {

    Optional<TUserEntity> findByEmail(String email);
}
