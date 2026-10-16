package com.project.qrclub.repository;

import com.project.qrclub.entity.UsersUUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserUUIDRepository extends JpaRepository<UsersUUID,Long> {
    @Query("SELECT  FROM users_uuid WHERE uuid = :uuid")
    Optional<UsersUUID> findByUUID(UUID uuid);
}
