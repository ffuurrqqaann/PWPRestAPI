package com.pwp.restapi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwp.restapi.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addUser(User u) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		try {
			session.persist(u);
			logger.info("Set updated successfully, Set Details="+u);
			
		} catch( Exception e ) {
			System.out.println(e.getMessage());
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public User getUser(User u) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		
		List<User> userList = session.createQuery(" from User WHERE id="+u.getId()).list();
		for(User user : userList){
			logger.info("User List::"+user);
		}
		
		if(userList.size()>0)
			return userList.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public User checkUserExist(User u) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery(" FROM User WHERE email='"+u.getEmail()+"' OR name='"+u.getName()+"'").list();
		
		if(userList.size()>0)
			return userList.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public User authenticateUser(User u) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery(" FROM User WHERE name='"+u.getName()+"' AND pass_hash='"+u.getPass_hash()+"'").list();
		
		if(userList.size()>0)
			return userList.get(0);
		else
			return null;
	}

	public Boolean changeEmail(User u) {
		// TODO Auto-generated method stub
		
		try {
			Session session = this.sessionFactory.getCurrentSession();
			User user = (User) session.load(User.class, new Long(u.getId()));
			
			//updating user's email.
			user.setEmail(u.getEmail());
			
			//saving the object
			session.saveOrUpdate(user);
			
			//return true if saved successfully.
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}