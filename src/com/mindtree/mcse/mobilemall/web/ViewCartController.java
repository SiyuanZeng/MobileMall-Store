package com.mindtree.mcse.mobilemall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.domain.Cart;

public class ViewCartController implements Controller {

	private String successView;

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Cart cart = (Cart) WebUtils.getOrCreateSessionAttribute(request.getSession(), "sessionCart", Cart.class);
		String page = request.getParameter("page");
		if (userSession != null) {
			if ("next".equals(page)) {
				userSession.getMyList().nextPage();
			}
			else if ("previous".equals(page)) {
				userSession.getMyList().previousPage();
			}
		}
		
		return new ModelAndView(this.successView, "cart", cart);
	}

}
