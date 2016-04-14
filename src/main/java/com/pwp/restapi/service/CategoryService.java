package com.pwp.restapi.service;

import java.util.List;

import com.pwp.restapi.model.Category;
import com.pwp.restapi.model.Contest;

public interface CategoryService {

	public Boolean addCategory(Category c);
	public List<Category> getCategoryList();
	
}
