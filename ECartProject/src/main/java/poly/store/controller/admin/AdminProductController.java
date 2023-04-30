/**
 * @(#)AdminProductController.java 2021/10/08.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/10/08.
 * Version 1.00.
 */
package poly.store.controller.admin;

import java.io.File;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import poly.store.baseResponse.BaseResponese;
import poly.store.common.Constants;
import poly.store.entity.Category;
import poly.store.entity.Product;
import poly.store.service.ProductService;
import poly.store.service.UploadService;

/**
 * Class dung de quan ly san pham
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Controller
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UploadService uploadService;
	/**
	 * Hien thi trang chu cua giao dien san pham
	 * 
	 * @return trang quan ly san pham
	 */
	@GetMapping("/admin/product/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return Constants.USER_DISPLAY_ADMIN_PRODUCT_FORM;
	}
	
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	@GetMapping("/admin/product/list")
	public String getAll(Model model) {
		return Constants.USER_DISPLAY_ADMIN_PRODUCT_LIST;
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("productId", id);
		model.addAttribute("enableBtnUpdate", true);
		return Constants.USER_DISPLAY_ADMIN_PRODUCT_FORM;
	}
	
	@GetMapping("/admin/product/getAll")
	public ResponseEntity<BaseResponese> getAll(){
		BaseResponese baseResponese = new BaseResponese();
		try {
			return ResponseEntity.ok().body(productService.getAll());
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Get Data Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}
		
	@PostMapping("/admin/product/create")
	public ResponseEntity<BaseResponese> create(@RequestBody Product product) {
		BaseResponese baseResponese = new BaseResponese();
		try {
			return ResponseEntity.ok().body(productService.create(product));
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Create Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}
	
	@PostMapping("/admin/product/update")
	public ResponseEntity<BaseResponese> update(@RequestBody Product product) {
		BaseResponese baseResponese = new BaseResponese();
		try {
			return ResponseEntity.ok().body(productService.update(product));
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Update Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}
	
	@PostMapping("/admin/product/delete")
	public ResponseEntity<BaseResponese> delete(@RequestBody Integer id) {
		BaseResponese baseResponese = new BaseResponese();
		try {
			return ResponseEntity.ok().body(productService.delete(id));
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Delete Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}

	@PostMapping("/admin/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) {
		File savedFile = uploadService.save(file, folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", savedFile.getName());
		node.put("size", savedFile.length());
		return node;
	}
	
}
