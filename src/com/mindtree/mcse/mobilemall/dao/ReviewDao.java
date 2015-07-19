package com.mindtree.mcse.mobilemall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Product;
import com.mindtree.mcse.mobilemall.domain.Review;

@SuppressWarnings("rawtypes")
public interface ReviewDao {

	Item getItemAndReviewList(String itemId) throws DataAccessException;

	Review getReview(String productId) throws DataAccessException;

}
