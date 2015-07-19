package com.mindtree.mcse.mobilemall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mindtree.mcse.mobilemall.domain.Category;

@SuppressWarnings("rawtypes")
public interface CategoryDao {

	List getCategoryList() throws DataAccessException;

	Category getCategory(String categoryId) throws DataAccessException;

	void deleteAll() throws DataAccessException;

	void save(Category category) throws DataAccessException;

}
