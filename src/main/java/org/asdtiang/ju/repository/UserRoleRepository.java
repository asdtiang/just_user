package org.asdtiang.ju.repository;

import org.asdtiang.ju.domain.Role;
import org.asdtiang.ju.domain.User;
import org.asdtiang.ju.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	UserRole findByUser(User user);
	UserRole findByUserAndRole(User user,Role role);
}
