package com.msb.ngs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.msb.ngs.exception.UserNotFoundException;
import com.msb.ngs.model.user.User;

public class RdbmsUserDao implements UserDao{

	@Override
	public void createUser(SessionFactory sf, User user) {
		try(Session sess = sf.openSession()){		
			sess.beginTransaction();
			System.out.println("Service:"+ user);
			sess.save(user);

			sess.getTransaction().commit();

		}
		
	}

	@Override
	public void deleteUser(SessionFactory sf, String name) throws UserNotFoundException {
		int res = 0;
		try(Session sess = sf.openSession()){		
			sess.beginTransaction();

			Query q = sess.createQuery("delete user_table where name = :name ");

			q.setParameter("name", name);

			res = q.executeUpdate();

			sess.getTransaction().commit();
		}

		if(res == 0) {
			throw new UserNotFoundException("User not present in database !!");
		}
		
	}

	@Override
	public void updateUser(SessionFactory sf, int id, String password) {
		try(Session sess = sf.openSession()){		
			sess.beginTransaction();
			
			
			User user = sess.load(User.class,id);

			user.setPassword(password);
			
			
			sess.getTransaction().commit();
		}
		
	}

	@Override
	public void updateUserRole(SessionFactory sf, int id, String role) {
		try(Session sess = sf.openSession()){		
			sess.beginTransaction();
			
			
			User user = sess.load(User.class,id);

			user.setRole(role);
			
			
			sess.getTransaction().commit();
		}
	}


}
