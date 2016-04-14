package com.comag10.crowdflower;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pwp.restapi.model.Category;
import com.pwp.restapi.model.Setting;
import com.pwp.restapi.service.CategoryService;
import com.pwp.restapi.service.SettingService;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class CategoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CategoryService categoryService;

	@Test()
	public void getCategoriesTest() {
		System.out.println( "Current Execution getCategoriesTest()" );
		System.out.println( "Executing getCategoriesTest() to return the categories that have been created." );
		
		List<Category> categories = this.categoryService.getCategoryList();
		
		System.out.println( "Comparing the size of the records returned from the category database table." );
		Assert.assertEquals(1, categories.size());
	}
	
	@Test()
	public void addCategoryTest() {
		System.out.println( "Current Execution addCategoryTest()" );
		System.out.println( "Executing addCategoryTest() to create a category." );
		
		String categoryCreated = "";
		
		Category category = new Category();
		category.setName("test category");
		
		Boolean isCategoryCreated = this.categoryService.addCategory(category);
		if(isCategoryCreated) {
			categoryCreated = "Created";
		} else {
			categoryCreated = "Not created";
		}
		
		System.out.println( "Setting "+categoryCreated );
		
		Assert.assertEquals("Created", categoryCreated);
	}
}