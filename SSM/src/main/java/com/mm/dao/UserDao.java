package com.mm.dao;

import java.util.List;

import com.mm.entity.User;

public interface UserDao {
	User getUser(String id);//
	List<User> getAllUsers();
	int addUser(User user);
	int updateUser(User user);
	int deleteUser(String id);
	List<User> getUsersByName(String name);
}
