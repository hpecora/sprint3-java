package com.mottu.mototracker.repository;

import com.mottu.mototracker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name); // "ROLE_USER", "ROLE_ADMIN"
}
