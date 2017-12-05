package com.mm.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.dao.UserDao;
import com.mm.entity.User;
import com.mm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User findUserById(String id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> findUsersByName(String name) {	
		return userDao.getUsersByName(name);
	}
	@Override
	public List<User> findAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public boolean addUser(User user) {
		int i = 0;
		i = userDao.addUser(user);
		return (i>0?true:false);
	}

	@Override
	public boolean deleteUser(String id) {
		int i = 0;
		i = userDao.deleteUser(id);
		return (i>0?true:false);
		
	}

	@Override
	public boolean updateUser(User user) {
		int i = 0;
		i = userDao.updateUser(user);
		return (i>0?true:false);
	}


}
