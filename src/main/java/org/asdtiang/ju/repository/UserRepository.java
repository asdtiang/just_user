package org.asdtiang.ju.repository;

import java.util.List;

import org.asdtiang.ju.domain.Role;
import org.asdtiang.ju.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);

    @Query("select u_r.role  from UserRole u_r   where u_r.user.id = ?1")
    List<Role> listRoleByUserId(Long userId);

    @Query("select u.username from User u where u.email = ?1")
    String findUsernameByEmail(String email);

   

    @Modifying
    @Query("update User set nickname=?1 , email=?2 where id=?3")
    void updateUserBaseInfo(String username, String email, Long id);

    @Modifying
    @Query("update User set enabled=?1 where id=?2")
    void enableUser(Boolean enable, Long id);

  
}
