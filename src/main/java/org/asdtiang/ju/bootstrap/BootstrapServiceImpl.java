package org.asdtiang.ju.bootstrap;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.asdtiang.ju.domain.Role;
import org.asdtiang.ju.domain.User;
import org.asdtiang.ju.domain.User.UsernameType;
import org.asdtiang.ju.domain.UserRole;
import org.asdtiang.ju.repository.RoleRepository;
import org.asdtiang.ju.repository.UserRepository;
import org.asdtiang.ju.repository.UserRoleRepository;
import org.asdtiang.ju.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BootstrapServiceImpl implements BootstrapService, ApplicationContextAware {
	private static final Logger log = LoggerFactory.getLogger(BootstrapServiceImpl.class);
	private ApplicationContext applicationContext;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRoleRepository userRoleRepository;	
	String ENV = "";
	boolean isDev = false;

	@PostConstruct
	public void init() {
		initUsers();
		
	}

	private void initUsers() {
		if (userRepository.count() == 0) {
			createDefaultUser("admin", "+86-11111111111", "123456", Role.ROLE_ADMIN);
			createDefaultUser("user", "+86-16666666666", "123456", Role.ROLE_USER);
			log.info("init user admin +86-11111111111  123456");
			log.info("init user user  +86-16666666666  123456");
		}
	}

	private void createDefaultUser(String username, String tel, String password, String authority) {
		Role role = roleRepository.findByAuthority(authority);
		if (role == null) {
			role = new Role();
			role.setAvailable(true);
			role.setAuthority(authority);
			role.setDescription("");
			role = roleRepository.save(role);
		}
		User user = new User();
		user.setPassword(passwordEncoder.encode(password));
		user.setUsername(username);
		user.setNickname(username);
		user.setUsernameType(UsernameType.TEL);
		user.setDateCreated(Calendar.getInstance());
		user.setLastUpdated(Calendar.getInstance());
		user = userRepository.save(user);
		UserRole userAndRole = new UserRole();
		userAndRole.setRole(role);
		userAndRole.setUser(user);
		userRoleRepository.save(userAndRole);    
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
