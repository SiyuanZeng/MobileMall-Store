package com.mindtree.mcse.mobilemall.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.service.OrderService;

@SuppressWarnings({"rawtypes","unchecked"})
public class ListOrdersController implements Controller {

	private OrderService orderService;

	public void setOrderService(OrderService os) {
		this.orderService = os;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String username = userSession.getAccount().getUsername();
		Map model = new HashMap();
		model.put("orderList", this.orderService.getOrdersByUsername(username));
		return new ModelAndView("ListOrders", model);
	}

}
