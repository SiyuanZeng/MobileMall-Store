package com.mindtree.mcse.mobilemall.service;

import java.util.List;

import com.mindtree.mcse.mobilemall.dao.CategoryDao;
import com.mindtree.mcse.mobilemall.domain.Category;
import com.mindtree.mcse.mobilemall.service.exception.CategoryNotFoundException;

public class CategoryService {

	private CategoryDao categoryDao;

	// -------------------------------------------------------------------------
	// Setter methods for dependency injection
	// -------------------------------------------------------------------------

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@SuppressWarnings("rawtypes")
	public List getCategoryList() throws CategoryNotFoundException{
		List categories = this.categoryDao.getCategoryList(); 
		if(null != categories && categories.size() >0){
			return categories;
		} else {
			throw new CategoryNotFoundException("No categories were found");
		}
	}

	public Category getCategory(String categoryId) throws CategoryNotFoundException {
		Category category = this.categoryDao.getCategory(categoryId);
		if(null != category){
			return category;
		} else {
			throw new CategoryNotFoundException("Category for id : "+categoryId+" was not found");
		}
	}
	
	public void deleteAllCategories(){
		this.categoryDao.deleteAll();
	}
}
