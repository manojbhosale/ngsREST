package com.msb.ngs.dao;

import org.hibernate.SessionFactory;

import com.msb.ngs.exception.UserNotFoundException;
import com.msb.ngs.model.user.User;

public interface UserDao {
	
	public void createUser(SessionFactory sf, User user);
	public void deleteUser(SessionFactory sf, String name) throws UserNotFoundException;
	public void updateUser(SessionFactory sf, int id, String password);
	public void updateUserRole(SessionFactory sf, int id, String role);

}
