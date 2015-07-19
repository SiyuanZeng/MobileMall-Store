package com.mindtree.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.mcse.mobilemall.dao.CategoryDao;
import com.mindtree.mcse.mobilemall.domain.Category;
import com.mindtree.mcse.mobilemall.service.CategoryService;
import com.mindtree.mcse.mobilemall.service.exception.CategoryNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:/WEB-INF/applicationContext.xml",
								"classpath:/WEB-INF/testDataAccessContext.xml",
								"classpath:/WEB-INF/webServicesContext.xml", 
								"classpath:/WEB-INF/dataAccessContext.xml"
								}) 
public class TestCategoryService {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CategoryDao categoryDao;

	@Test
	public void testGetCategoryListException() {
		categoryService.deleteAllCategories();
		try {
			List categories = categoryService.getCategoryList();
			fail("Exception Expected");
		} catch (CategoryNotFoundException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetCategoryException() {
		categoryService.deleteAllCategories();
		try {
			Category category = categoryService.getCategory("dummy");
			fail("Exception Expected");
		} catch (CategoryNotFoundException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetCategory(){
		categoryDao.save(new Category("testCategory", "test", "this is for testing only"));
		try {
			Category category = categoryService.getCategory("testCategory");
			if(category.getName().equals("test")){
				assertTrue(true);
			} else {
				fail("Category not found");
			}
			
		} catch (CategoryNotFoundException e) {
			fail("Exception not Expected");
		}
		
	}

}
