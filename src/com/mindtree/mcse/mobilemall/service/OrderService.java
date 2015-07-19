package com.mindtree.mcse.mobilemall.service;

import java.util.List;

import com.mindtree.mcse.mobilemall.dao.ItemDao;
import com.mindtree.mcse.mobilemall.dao.OrderDao;
import com.mindtree.mcse.mobilemall.domain.Order;
import com.mindtree.mcse.mobilemall.service.exception.OrderNotFoundException;

public class OrderService {
	
	private ItemDao itemDao;

	private OrderDao orderDao;

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void insertOrder(Order order) {
		this.orderDao.insertOrder(order);
		this.itemDao.updateQuantity(order);
	}

	public Order getOrder(int orderId) {
		return this.orderDao.getOrder(orderId);
	}

	@SuppressWarnings("rawtypes")
	public List getOrdersByUsername(String username) {
		return this.orderDao.getOrdersByUsername(username);
	}
	
	public List<Order> getAllOrders() throws OrderNotFoundException{
		List orders = this.orderDao.getAllOrders();
		if(null != orders && orders.size() > 0){
			return orders;
		} else {
			throw new OrderNotFoundException("No orders were found");
		}
	}

}
