package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.baseResponse.BaseResponese;
import poly.store.dao.CategoryDao;
import poly.store.dao.UserDao;
import poly.store.entity.Category;
import poly.store.entity.User;
import poly.store.model.CategoryModel;
import poly.store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryRepository;

	@Autowired
	UserDao userDao;

	@Override
	public BaseResponese getAll() {
		return new BaseResponese("1", "Get Data Success", categoryRepository.findAll());
	}

	@Override
	public BaseResponese create(Category category) {
		categoryRepository.save(category);
		return new BaseResponese("1", "Create Success");
	}

	@Override
	public BaseResponese update(Category category) {
		if (categoryRepository.getById(category.getId()) != null) {
			categoryRepository.save(category);
			return new BaseResponese("1", "Update Success");
		}
		return new BaseResponese("-1", "Update Failed");
	}

	@Override
	public BaseResponese delete(Integer id) {
		if (categoryRepository.getById(id) != null) {
			categoryRepository.deleteById(id);
			return new BaseResponese("1", "Delete Success");
		}
		return new BaseResponese("-1", "Delete Failed");
	}

	@Override
	public CategoryModel createCategory(CategoryModel categoryModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Category category = new Category();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setPersoncreate(temp.getId());
		category.setCreateday(timestamp.toString());
		categoryRepository.save(category);
		return categoryModel;
	}

	@Override
	public CategoryModel getOneCategoryById(Integer id) {
		Category category = categoryRepository.findById(id).get();
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setName(category.getName());
		categoryModel.setNameSearch(category.getNamesearch());
		categoryModel.setLogo(category.getLogo());
		categoryModel.setBanner(category.getBanner());
		categoryModel.setDescribe(category.getDescription());
		return categoryModel;
	}

	@Override
	public CategoryModel updateCategory(CategoryModel categoryModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Category category = categoryRepository.findById(categoryModel.getId()).get();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setUpdateday(timestamp.toString());
		category.setPersonupdate(temp.getId());
		categoryRepository.save(category);
		return categoryModel;
	}

	@Override
	public Category getCategoryByNameSearch(String nameSearch) {
		return categoryRepository.getCategoryByNameSearch(nameSearch);
	}
}
