package com.pwp.restapi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pwp.restapi.model.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public Boolean addCategory(Category c) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.persist(c);
			logger.info("Category added successfully, Setting Details="+c);

		} catch( Exception e ) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		List<Category> categoryList = session.createQuery(" from Category").list();
		for(Category category : categoryList){
			logger.info("Category List::"+category);
		}

		return categoryList;
	}
}