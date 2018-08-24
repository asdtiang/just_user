package org.asdtiang.ju.service;

import java.util.List;

import org.asdtiang.ju.domain.Role;
import org.asdtiang.ju.domain.User;

/**
 * @author asdtiang
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param user
     */
    

    User createUser(User user);

    User currentUser();

    User updateUser(User user);

    void deleteUser(User user);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    void changePassword(User user, String newPassword);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 匹配的用户
     */
    User findByUsername(String username);

    /**
     * login success call ,store dbInfo key to shiro session
     */
    void loginAction();


    User findById(Long userId);

    List<Role> findRoleByUser(User user);

    /**
     * @param user
     * @return ROLE1, ROLE2...
     */
    String findRoleStrByUser(User user);



    void updateFirstStatus(User user);

   
}
