package com.lefnds.loremshop.repositories;

import com.lefnds.loremshop.enums.RoleName;
import com.lefnds.loremshop.model.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,UUID> {

    Optional<Role> findByRoleName(RoleName roleName);

}
