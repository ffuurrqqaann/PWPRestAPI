package com.pwp.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwp.restapi.dao.CategoryDao;
import com.pwp.restapi.dao.ContestDao;
import com.pwp.restapi.dao.UserDao;
import com.pwp.restapi.model.Category;
import com.pwp.restapi.model.Contest;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDAO;
	
	public void setCategoryDAO(CategoryDao categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public Boolean addCategory(Category c) {
		// TODO Auto-generated method stub
		Boolean isCategoryAdded = this.categoryDAO.addCategory(c);
		
		return isCategoryAdded;
	}

	public List<Category> getCategoryList() {
		// TODO Auto-generated method stub
		List<Category> category = this.categoryDAO.getCategoryList();
		
		return category;
	}

	public Category getCategoryById(Category c) {
		// TODO Auto-generated method stub
		Category category = this.categoryDAO.getCategory(c);
		
		return category;
	}

}