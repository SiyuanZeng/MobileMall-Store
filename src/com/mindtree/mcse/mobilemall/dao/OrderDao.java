package com.mindtree.mcse.mobilemall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mindtree.mcse.mobilemall.domain.Order;

@SuppressWarnings("rawtypes")
public interface OrderDao {

  List getOrdersByUsername(String username) throws DataAccessException;

  Order getOrder(int orderId) throws DataAccessException;

  void insertOrder(Order order) throws DataAccessException;
  
  List<Order> getAllOrders() throws DataAccessException;

}
