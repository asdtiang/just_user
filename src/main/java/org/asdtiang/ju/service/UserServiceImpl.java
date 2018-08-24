package org.asdtiang.ju.service;

import java.util.List;
import java.util.Optional;

import org.asdtiang.ju.domain.Role;
import org.asdtiang.ju.domain.User;
import org.asdtiang.ju.repository.RoleRepository;
import org.asdtiang.ju.repository.UserRepository;
import org.asdtiang.ju.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author abel.lee 2014年11月14日 上午10:11:14
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public User updateUser(User user) {
        // TODO
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        if (username == null) {
            return null;
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public void loginAction() {
        // TODO Auto-generated method stub

    }

    @Override
    public User currentUser() {
        User user = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("principal-->" + principal.getClass().getName() + "   --->value:" + principal.toString());
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                user = userRepository.findByUsername(username);
                if (user == null) {
                    user = findByUsername(username);
                }
            }
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));//// 加密密码
        user = userRepository.save(user);
        return user;
    }

   
   
    @Override
    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()) {
        	return null;
        }
        return user.get();
    }

    @Override
    public List<Role> findRoleByUser(User user) {
        List<Role> roleList = null;
        if (user != null) {
            roleList = roleRepository.findAllRoleByUserId(user.getId());
        }
        return roleList;
    }

   

    @Override
    public void updateFirstStatus(User user) {
        if (user.getLoginCount() == null) {
            user.setLoginCount(0);
        } else {
            user.setLoginCount(user.getLoginCount() + 1);
        }
        userRepository.saveAndFlush(user);
    }

   
    @Override
    public String findRoleStrByUser(User user) {
        List<Role> roleList = this.findRoleByUser(user);
        String result = "";
        if (roleList != null && roleList.size() > 0) {
            for (Role r : roleList) {
                result = result + "," + r.getAuthority();
            }
            result = result.substring(1);
        }
        return result;
    }
}
