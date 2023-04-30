/**
 * @(#)UserServiceImpl.java 2021/10/10.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/10/10.
 * Version 1.00.
 */
package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.baseResponse.BaseResponese;
import poly.store.dao.CategoryDao;
import poly.store.dao.ManufacturerDao;
import poly.store.dao.ProductDao;
import poly.store.dao.UserDao;
import poly.store.entity.Category;
import poly.store.entity.Manufacturer;
import poly.store.entity.Product;
import poly.store.entity.User;
import poly.store.model.ProductModel;
import poly.store.model.ShowProduct;
import poly.store.service.CommentService;
import poly.store.service.ProductService;

/**
 * Class trien khai theo interface UserService, Thao tac voi Class UserDao de
 * thuc hien cac tac vu tuong ung
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Service
public class ProductServiceImp implements ProductService {
	@Autowired
	ProductDao productRepository;

	@Autowired
	UserDao userDao;

	@Autowired
	ManufacturerDao manufacturerDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	CommentService commentService;

	@PersistenceContext
	private EntityManager em;

	@Override
	public BaseResponese getAll() {
		return new BaseResponese("1", "Get Data Success", productRepository.findAll());
	}

	@Override
	public BaseResponese create(Product product) {
		productRepository.save(product);
		return new BaseResponese("1", "Create Success");
	}

	@Override
	public BaseResponese update(Product product) {
		if (productRepository.getById(product.getId()) != null) {
			productRepository.save(product);
			return new BaseResponese("1", "Update Success");
		}
		return new BaseResponese("-1", "Update Failed");
	}

	@Override
	public BaseResponese delete(Integer id) {
		if (productRepository.getById(id) != null) {
			productRepository.deleteById(id);
			return new BaseResponese("1", "Delete Success");
		}
		return new BaseResponese("-1", "Delete Failed");
	}

	@Override
	public ProductModel createProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Product product = new Product();
		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setPoint(productModel.getPoint());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setColor(productModel.getColor());
		product.setMemory(productModel.getMemory());
		product.setCreateday(timestamp.toString());
		product.setPersoncreate(temp.getId());

		Manufacturer manufacturer = manufacturerDao.findById(productModel.getManuId()).get();
		Category category = categoryDao.findById(productModel.getCateId()).get();

		product.setCategory(category);
		product.setManufacturer(manufacturer);

		productRepository.save(product);

		return productModel;
	}

	@Override
	public ProductModel updateProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Product product = productRepository.findById(productModel.getId()).get();

		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setPoint(productModel.getPoint());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setColor(productModel.getColor());
		product.setMemory(productModel.getMemory());
		product.setUpdateday(timestamp.toString());
		product.setPersonupdate(temp.getId());

		Manufacturer manufacturer = manufacturerDao.findById(productModel.getManuId()).get();
		Category category = categoryDao.findById(productModel.getCateId()).get();

		product.setCategory(category);
		product.setManufacturer(manufacturer);

		productRepository.save(product);
		return productModel;
	}

	@Override
	public ProductModel getOneProductById(Integer id) {
		Product product = productRepository.findById(id).get();
		ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setCode(product.getCode());
		productModel.setName(product.getName());
		productModel.setPrice(product.getPrice());
		productModel.setPoint(product.getPoint());
		productModel.setQuality(product.getQuality());
		productModel.setImage1(product.getImage1());
		productModel.setImage2(product.getImage2());
		productModel.setImage3(product.getImage3());
		productModel.setNameSearch(product.getNamesearch());
		productModel.setColor(product.getColor());
		productModel.setMemory(product.getMemory());
		productModel.setActive(product.isActive());
		productModel.setManuId(product.getManufacturer().getId());
		productModel.setCateId(product.getCategory().getId());
		productModel.setDescription(product.getDescription());
		productModel.setSpecification(product.getSpecification());
		return productModel;
	}

	@Override
	public List<Product> getListLatestProduct() {
		return productRepository.getListLatestProduct();
	}

	@Override
	public List<Product> getListViewsProduct() {
		return productRepository.getListViewsProduct();
	}

	@Override
	public Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable) {
		return productRepository.getListProductByNameSearch(nameSearch, pageable);
	}

	@Override
	public List<Product> getDemo(String nameSearch) {
		// TODO Auto-generated method stub
		return productRepository.getListDemo(nameSearch);
	}

	@Override
	public Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.getListProductByPrice(nameSearch, minPrice, maxPrice, pageable);
	}

	@Override
	public Page<ShowProduct> getListProductByFilter(String nameSearch, String price, String manu, String sort,
			Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> from = cq.from(Product.class);

		Predicate preNameSearch = cb.like(from.get("category").get("Namesearch"), "%" + nameSearch + "%");

		int check = 0;
		Predicate prePrice = null;
		Predicate preManu = null;

		if (price != null) {
			int min = 0;
			int max = 999999999;
			if (price.equals("1")) {
				max = 3000000;
			} else if (price.equals("2")) {
				min = 3000000;
				max = 5000000;
			} else if (price.equals("3")) {
				min = 5000000;
				max = 8000000;
			} else if (price.equals("4")) {
				min = 8000000;
				max = 13000000;
			} else {
				min = 13000000;
			}
			check = 1;
			prePrice = cb.between(from.get("price"), min, max);
		}

		if (manu != null) {
			preManu = cb.equal(from.get("manufacturer").get("id"), Integer.parseInt(manu));
			if (check == 1) {
				check = 3;
			} else {
				check = 2;
			}
		}

		if (check == 1) {
			cq.where(prePrice, preNameSearch);
		} else if (check == 2) {
			cq.where(preManu, preNameSearch);
		} else if (check == 3) {
			cq.where(prePrice, preManu, preNameSearch);
		} else {
			cq.where(preNameSearch);
			// cq.select(from);
		}

		if (sort != null) {
			if (sort.equals("1")) {
				cq.orderBy(cb.asc(from.get("name")));
			}
			if (sort.equals("2")) {
				cq.orderBy(cb.desc(from.get("name")));
			}
			if (sort.equals("3")) {
				cq.orderBy(cb.asc(from.get("price")));
			}
			if (sort.equals("4")) {
				cq.orderBy(cb.desc(from.get("price")));
			}
		}

		TypedQuery<Product> q = em.createQuery(cq);

		List<Product> countAllItems = q.getResultList();

		q.setFirstResult(Math.toIntExact(pageable.getOffset()));
		q.setMaxResults(pageable.getPageSize());

		List<Product> getAllItems = q.getResultList();

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : getAllItems) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		Page<ShowProduct> page = new PageImpl<ShowProduct>(listProduct, pageable, countAllItems.size());

		return page;
	}

	@Override
	public Product getProductByNameSearch(String nameSearch) {
		return productRepository.getProductByNameSearch(nameSearch);
	}

	@Override
	public List<Product> getListProductRelated(int id) {
		return productRepository.getListProductRelated(id);
	}

	@Override
	public void updateView(String nameSearch) {
		Product product = productRepository.getProductByNameSearch(nameSearch);
		int view = product.getViews();
		product.setViews(view + 1);
		productRepository.save(product);
	}

	@Override
	public void updateQuality(Product product) {
		productRepository.save(product);
	}

}
