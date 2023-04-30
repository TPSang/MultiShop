package poly.store.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import poly.store.baseResponse.BaseResponese;
import poly.store.common.Constants;
import poly.store.dao.ProductDao;
import poly.store.entity.Category;
import poly.store.service.CategoryService;

@Controller
public class AdminCategoryController {
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductDao productRepository;
	
	@GetMapping("/admin/categories/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return Constants.USER_DISPLAY_ADMIN_CATEGORY_FORM;
	}
	
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	@GetMapping("/admin/categories/list")
	public String list(Model model) {
		return Constants.USER_DISPLAY_ADMIN_CATEGORY_LIST;
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("categoryId", id);
		model.addAttribute("enableBtnUpdate", true);
		return Constants.USER_DISPLAY_ADMIN_CATEGORY_FORM;
	}
	
	@GetMapping("/admin/categories/getAll")
	public ResponseEntity<BaseResponese> getAll() {
		BaseResponese baseResponese = new BaseResponese();
		try {
			return ResponseEntity.ok().body(categoryService.getAll());
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Get Data Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}

	@PostMapping("/admin/categories/create")
	public ResponseEntity<BaseResponese> create(@RequestBody Category category) {
		BaseResponese baseResponese = new BaseResponese();
		try {
			return ResponseEntity.ok().body(categoryService.create(category));
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Create Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}

	@PostMapping("/admin/categories/update")
	public ResponseEntity<BaseResponese> update(@RequestBody Category category) {
		BaseResponese baseResponese = new BaseResponese();
		try {
			return ResponseEntity.ok().body(categoryService.update(category));
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Update Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}

	@PostMapping("/admin/categories/delete")
	public ResponseEntity<BaseResponese> delete(@RequestBody Integer id) {
		BaseResponese baseResponese = new BaseResponese();
		try {
			baseResponese.setMessage(this.checkIdCategoryToDelete(id));
			return ResponseEntity.ok().body(baseResponese);
		} catch (Exception e) {
			baseResponese.setCode("-1");
			baseResponese.setMessage("Delete Failed");
			return ResponseEntity.badRequest().body(baseResponese);
		}
	}

	public String checkIdCategoryToDelete(Integer id) {
		List<?> list = productRepository.checkIdCategoryToDelete();
		for (Object L : list) {
			if (L.equals(id)) {
				return "Không thể xóa Danh mục đã có sản phẩm";
			}
		}
		return categoryService.delete(id).getMessage();
	}
}
