package com.mindtree.mcse.mobilemall.service;


import java.util.List;

import com.mindtree.mcse.mobilemall.dao.ProductDao;
import com.mindtree.mcse.mobilemall.domain.Product;

public class ProductService {
	
	private ProductDao productDao;

	//-------------------------------------------------------------------------
	// Setter methods for dependency injection
	//-------------------------------------------------------------------------

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@SuppressWarnings("rawtypes")
	public List getProductListByCategory(String categoryId) {
		return this.productDao.getProductListByCategory(categoryId);
	}

	@SuppressWarnings("rawtypes")
	public List searchProductList(String keywords) {
		return this.productDao.searchProductList(keywords);
	}

	public Product getProduct(String productId){
		return this.productDao.getProduct(productId);
	}	
}
