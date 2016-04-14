package com.pwp.restapi.dao;

import java.util.List;

import com.pwp.restapi.model.Category;

public interface CategoryDao {
	 
    public Boolean addCategory(Category s);
    public List<Category> getCategoryList();
    
}