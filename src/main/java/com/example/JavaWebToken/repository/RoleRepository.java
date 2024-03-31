package com.example.JavaWebToken.repository;

import com.example.JavaWebToken.entity.RoleName;
import com.example.JavaWebToken.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, RoleName> {
}
