package com.mm.service;

import java.util.List;

import com.mm.entity.User;

public interface UserService {
	User findUserById(String id);
	List<User> findUsersByName(String name);
	List<User> findAllUsers();
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(String id);
	

}
