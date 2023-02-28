/**
 * @(#)CategoryModel.java 2021/09/29.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/29.
 * Version 1.00.
 */
package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class thong tin truy van cua bang Information Shop
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopModel {
	private int id;
	private String name;
	private String time;
	private String phone;
	private String fax;
	private String email;
	private String logo;
	private String logoFooter;
	private String address;
	private boolean active;
}
