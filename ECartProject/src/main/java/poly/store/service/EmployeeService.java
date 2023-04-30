/**
 * @(#)RoleService.java 2021/09/10.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/10.
 * Version 1.00.
 */
package poly.store.service;

import poly.store.baseResponse.BaseResponese;
import poly.store.entity.Employee;

/**
 * Class cung cap cac dich vu thao tac voi table Employee trong database
 * 
 * @author khoa-ph
 * @version 1.00
 */
public interface EmployeeService {

	BaseResponese getAll();

	void save(Employee employee);
	
}
