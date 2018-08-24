package org.asdtiang.ju.repository;

import java.util.List;

import org.asdtiang.ju.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByAuthority(String role);

	@Query("select r.id,r.authority from Role r where r.available=true")
	List<Role> findBylistAvailable();

	@Query("select role from UserRole ur left join ur.role role left join ur.user u where u.id=?1")
	Role findRoleByUser(Long userId);
	@Query("select role from UserRole ur left join ur.role role left join ur.user u where u.id=?1")
	List<Role> findAllRoleByUserId(Long userId);
}
