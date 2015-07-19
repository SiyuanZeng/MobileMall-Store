package com.mindtree.mcse.mobilemall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Order;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;

@SuppressWarnings("rawtypes")
public interface ItemDao {

  public void updateQuantity(Order order) throws DataAccessException;

  boolean isItemInStock(String itemId) throws DataAccessException;

  List getItemListByProduct(String productId) throws DataAccessException;

  Item getItem(String itemId) throws DataAccessException;

  int addReview(Review review) throws DataAccessException;
  void addReviewHibernateAnnotation(HReview review) throws DataAccessException;
}
