package com.pwp.restapi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwp.restapi.model.Contest;
import com.pwp.restapi.model.User;

@Repository
@Transactional
public class ContestDaoImpl implements ContestDao {

	private static final Logger logger = LoggerFactory.getLogger(ContestDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addContest(Contest c, int userId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		
		User user = (User)session.load(User.class, new Integer(1));
		
		c.setUser(user);
		
		try {
			session.persist(c);
			logger.info("Contest added successfully, Contest Details="+c);

		} catch( Exception e ) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Contest> getAllContests() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		List<Contest> contestList = session.createQuery(" from Contest").list();
		for(Contest contest : contestList){
			logger.info("Contest List::"+contest);
		}

		return contestList;
	}
}