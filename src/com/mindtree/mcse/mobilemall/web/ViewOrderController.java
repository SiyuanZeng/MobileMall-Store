package com.mindtree.mcse.mobilemall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.domain.Order;
import com.mindtree.mcse.mobilemall.service.OrderService;

public class ViewOrderController implements Controller {

	private OrderService orderService;

	public void setOrderService(OrderService os) {
		this.orderService = os;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		Order order = this.orderService.getOrder(orderId);
		if (userSession.getAccount().getUsername().equals(order.getUsername())) {
			return new ModelAndView("ViewOrder", "order", order);
		}
		else {
			return new ModelAndView("Error", "message", "You may only view your own orders.");
		}
	}

}
