package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.entity.user.RoleCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role AS r WHERE r.code = :authority")
    Optional<Role> findByAuthority(RoleCode authority);
}
