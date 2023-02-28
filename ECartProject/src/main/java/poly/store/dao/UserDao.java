/**
 * @(#)UserDao.java 2021/09/08.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/08.
 * Version 1.00.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.User;

/**
 * Class thuc hien truy van thong tin bang User trong database
 * 
 * @author KHOA-PH
 * @version 1.00
 */
public interface UserDao extends JpaRepository<User, Integer> {
	/**
	 * Tim user theo email
	 * 
	 * @param email thong tin email
	 * @return User tim duoc
	 */
	@Query("SELECT u FROM User u WHERE u.email = :uemail and u.Deleteday = null")
	User findUserByEmail(@Param("uemail") String email);
	
	/**
	 * Tim tat ca cac user co hieu luc
	 * 
	 * @return danh sach User tim duoc
	 */
	@Query("SELECT u FROM User u WHERE u.Deleteday = null")
	List<User> findAllUserAnable();
	
}
