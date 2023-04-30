/**
 * @(#)RoleServiceImpl.java 2021/09/10.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/10.
 * Version 1.00.
 */
package poly.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import poly.store.baseResponse.BaseResponese;
import poly.store.dao.EmployeeDao;
import poly.store.entity.Employee;
import poly.store.service.EmployeeService;

/**
 * Class trien khai theo interface EmployeeService, Thao tac voi Class
 * EmployeeDao de thuc hien cac tac vu tuong ung
 * 
 * @author KHOA-PH
 * @version 1.00
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void save(Employee employee) {
		employeeDao.save(employee);
	}

	@Override
	public BaseResponese getAll() {
		return new BaseResponese("1", "Get Data Success", employeeDao.findAll());
	}

}
