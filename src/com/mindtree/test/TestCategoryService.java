package com.mindtree.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.mcse.mobilemall.dao.CategoryDao;
import com.mindtree.mcse.mobilemall.domain.Category;
import com.mindtree.mcse.mobilemall.event.InventoryCheckEvent;
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
	
	@Mock
	CategoryDao categoryDaoMock;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this); 
		categoryService.setCategoryDao(categoryDaoMock);
	}

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
	public void testGetCategory() throws CategoryNotFoundException{
		Category category = new Category("HTC", "HTCName", "");
//		when(invService.checkItemInventory(any(InventoryCheckEvent.class))).thenReturn(10);
		when(categoryDaoMock.getCategory("HTC")).thenReturn(category);
		Category categoryOutput = categoryService.getCategory("HTC");
		assertThat("HTCName", is(categoryOutput.getName()));
	}

}
