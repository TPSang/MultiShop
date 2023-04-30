/**
 * @(#)AdminEmployeeController.java 2021/09/10.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/13.
 * Version 1.00.
 */
package poly.store.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.baseResponse.BaseResponese;
import poly.store.common.Constants;
import poly.store.service.UserRoleService;
import poly.store.service.UserService;

/**
 * Class dung de quan ly nhan vien
 * 
 * @author khoa-ph
 * @version 1.00
 */
@CrossOrigin("*")
@Controller
public class AdminEmployeeController {

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	@GetMapping("/admin/employees/list")
	public String list(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		model.addAttribute("username", username);
		return Constants.USER_DISPLAY_ADMIN_EMPLOYEE_LIST;
	}

	@GetMapping("/admin/employees/listUser")
	public String listUser(Model model) {
		return Constants.USER_DISPLAY_ADMIN_USER_LIST;
	}

	@GetMapping("/admin/employee/getAll")
	public ResponseEntity<BaseResponese> getAll() {
		BaseResponese baseResponese = new BaseResponese();
		try {
			baseResponese.setCode("1");
			baseResponese.setMessage("Get Data Success");
			baseResponese.setData(userRoleService.findAllAdminOrDirector());
			return ResponseEntity.ok().body(baseResponese);
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Get Data Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}

	@GetMapping("/admin/user/getAll")
	public ResponseEntity<BaseResponese> getAllUser() {
		BaseResponese baseResponese = new BaseResponese();
		try {
			baseResponese.setCode("1");
			baseResponese.setMessage("Get Data Success");
			baseResponese.setData(userService.findUser());
			return ResponseEntity.ok().body(baseResponese);
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Get Data Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}

	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	@GetMapping("/admin/employees/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return Constants.USER_DISPLAY_ADMIN_EMPLOYEE_FORM;
	}

	@GetMapping("/admin/employees/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		System.out.println(id);
		model.addAttribute("userId", id);
		model.addAttribute("enableBtnUpdate", true);
		return Constants.USER_DISPLAY_ADMIN_EMPLOYEE_FORM;
	}
}
