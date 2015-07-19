package com.mindtree.mcse.mobilemall.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.domain.Order;
import com.mindtree.mcse.mobilemall.service.OrderService;
import com.mindtree.mcse.mobilemall.service.exception.OrderNotFoundException;

@SuppressWarnings({"rawtypes","unchecked"})
public class ListAllOrdersController implements Controller {

	private OrderService orderService;

	public void setOrderService(OrderService os) {
		this.orderService = os;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		
		try {
			model.put("orderList", this.orderService.getAllOrders());
		} catch (OrderNotFoundException e) {
			model.put("message", "No orders were found");
			model.put("accountList", new ArrayList<Order>());
		}
		return new ModelAndView("ListAllOrders", model);
	}

}
