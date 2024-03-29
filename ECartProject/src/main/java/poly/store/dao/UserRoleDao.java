/**
 * @(#)UserRoleDao.java 2021/09/07.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/10.
 * Version 1.00.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.UserRole;

/**
 * Class thuc hien truy van thong tin bang User_Role trong database
 * 
 * @author khoa-ph
 * @version 1.00
 */
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
	//@Query(value = "SELECT * FROM User_Role LEFT JOIN Users ON User_Role.Role_Id = Users.Id LEFT JOIN Employees ON Employees.User_Id = Users.Id WHERE Role_Id = 2 OR Role_Id = 3 OR Users.DeleteDay = NULL", nativeQuery = true)
	@Query("SELECT u FROM UserRole u WHERE (u.role.id = 2 or u.role.id = 3) and u.user.Deleteday = null")
	List<?>findAllAdminOrDirector();
}
