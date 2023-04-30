/**
 * @(#)UserServiceImpl.java 2021/09/08.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/08.
 * Version 1.00.
 */
package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import poly.store.baseResponse.BaseResponese;
import poly.store.dao.UserDao;
import poly.store.entity.User;
import poly.store.model.ChangePassModel;
import poly.store.model.InformationModel;
import poly.store.service.UserService;

/**
 * Class trien khai theo interface UserService, Thao tac voi Class UserDao de
 * thuc hien cac tac vu tuong ung
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Service
public class UserServiceImpl implements UserService {
	// Thong tin class User Dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Tim user bang email truyen vao
	 * 
	 * @param username thong tin Email
	 * @return User tim duoc
	 */
	@Override
	public User findUserByEmail(String email) {
		// Tra ve User tim duoc
		return userDao.findUserByEmail(email);
	}

	/**
	 * Luu user vao database
	 * 
	 * @param user thong tin user
	 */
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public BaseResponese getAll() {
		return new BaseResponese("1", "Get Data Success", userDao.findAll());
	}

	@Override
	public User create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public List<User> findAllUserAnable() {
		return userDao.findAllUserAnable();
	}

	@Override
	public InformationModel getUserAccount() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = userDao.findUserByEmail(username);

		InformationModel information = new InformationModel();

		information.setPassword(user.getPassword());
		information.setFullName(user.getFullname());
		information.setEmail(user.getEmail());
		information.setBirthday(user.getBirthday());
		information.setGender(user.getSex());
		information.setNews(user.getSubscribe());

		return information;
	}

	@Override
	public InformationModel update(InformationModel informationModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = userDao.findUserByEmail(username);

		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());

		userDao.save(user);

		return informationModel;
	}

	@Override
	public ChangePassModel updatePass(ChangePassModel changePassModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = userDao.findUserByEmail(username);

		user.setPassword(changePassModel.getNewPass());

		userDao.save(user);

		return changePassModel;
	}

	@Override
	public List<?> findUser() {
		return userDao.findUser(); 
	}
}
