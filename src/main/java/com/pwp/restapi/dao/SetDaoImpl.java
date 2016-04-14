package com.pwp.restapi.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwp.restapi.model.Stringsets;

@Repository
@Transactional
public class SetDaoImpl implements SetDao {

	private static final Logger logger = LoggerFactory.getLogger(SetDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addSet(Stringsets s) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		try {
			session.persist(s);
			logger.info("Set updated successfully, Set Details="+s);
			
		} catch( Exception e ) {
			return false;
		}
		
		return true;
	}

	public List<Stringsets> listStringSets() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Stringsets> stringSetsList = session.createQuery(" from Stringsets").list();
		for(Stringsets s : stringSetsList){
			logger.info("Person List::"+s);
		}
		return stringSetsList;
	}

	public Boolean removeSet(String str) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("delete Stringsets WHERE stringset=:str");
		query.setParameter("str", new String(str));
		int result = query.executeUpdate();
		
		
		return result>0 ? true:false;
		
	}

	
}