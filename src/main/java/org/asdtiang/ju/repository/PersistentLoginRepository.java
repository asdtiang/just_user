package org.asdtiang.ju.repository;

import java.util.Calendar;

import org.asdtiang.ju.domain.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, String>{
	
	@Modifying()
	@Query("update PersistentLogin set token =?1,series=?1, lastUsed = ?2 where series = ?3")
	void updateToken(String newToken,Calendar lastUsed,String series);
	
	void deleteByName(String name);

}