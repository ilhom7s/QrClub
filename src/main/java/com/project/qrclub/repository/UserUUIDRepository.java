package com.project.qrclub.repository;

import com.project.qrclub.entity.UsersUUID;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserUUIDRepository extends JpaRepository<UsersUUID,Long> {
    Optional<UsersUUID> findByUuid(UUID uuid);
}
