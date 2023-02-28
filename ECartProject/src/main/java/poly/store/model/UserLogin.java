/**
 * @(#)UserLogin.java 2021/09/09.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/09.
 * Version 1.00.
 */
package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class lam viec voi form login.html
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
	// Thong tin user name
	private String username;
	// Thong tin password
	private String password;
}
