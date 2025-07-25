package org.example.custodiacrm.repositories;


import org.example.custodiacrm.models.entities.Role;
import org.example.custodiacrm.models.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
