package com.msb.ngs.service;

import com.msb.ngs.dao.DAOFactroy;
import com.msb.ngs.dao.UserDao;
import com.msb.ngs.exception.UserNotFoundException;
import com.msb.ngs.model.user.User;
import com.msb.ngs.utils.NgsUtils;
import com.msb.ngs.utils.UserUtils;

public class UserService {

	private static UserDao userDao = DAOFactroy.getUserDao("RDBMS");
	
	public static void createUser(User user) {

		//UserUtils.createUser(NgsUtils.getSessionFactoryInstance(), user);
		userDao.createUser(NgsUtils.getSessionFactoryInstance(), user);
	}

	public static void updateUser(int id, String pass) {

		//UserUtils.updateUser(NgsUtils.getSessionFactoryInstance(), id, pass);
		userDao.updateUser(NgsUtils.getSessionFactoryInstance(), id, pass);
	}

	public static void deleteUser(String name) throws UserNotFoundException {

		UserUtils.deleteUser(NgsUtils.getSessionFactoryInstance(), name);
		userDao.deleteUser(NgsUtils.getSessionFactoryInstance(), name);
	}
	
	public static void updateRole(int id, String role) {
		
		//UserUtils.updateRole(NgsUtils.getSessionFactoryInstance(), id, role);
		userDao.updateUserRole(NgsUtils.getSessionFactoryInstance(), id, role);
	}

}
