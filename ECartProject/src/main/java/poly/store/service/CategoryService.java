package poly.store.service;

import java.util.List;

import poly.store.baseResponse.BaseResponese;
import poly.store.entity.Category;
import poly.store.model.CategoryModel;

public interface CategoryService {

	BaseResponese getAll();

	BaseResponese create(Category category);

	BaseResponese update(Category category);

	BaseResponese delete(Integer id);

	CategoryModel createCategory(CategoryModel categoryModel);

	CategoryModel getOneCategoryById(Integer id);

	CategoryModel updateCategory(CategoryModel categoryModel);

	Category getCategoryByNameSearch(String nameSearch);

}
