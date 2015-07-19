package com.mindtree.mcse.mobilemall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mindtree.mcse.mobilemall.domain.Product;

@SuppressWarnings("rawtypes")
public interface ProductDao {

	List getProductListByCategory(String categoryId) throws DataAccessException;

	List searchProductList(String keywords) throws DataAccessException;

	Product getProduct(String productId) throws DataAccessException;

}
