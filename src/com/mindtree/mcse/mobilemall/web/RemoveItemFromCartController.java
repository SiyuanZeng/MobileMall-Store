package com.mindtree.mcse.mobilemall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.domain.Cart;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public class RemoveItemFromCartController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cart cart = (Cart) WebUtils.getOrCreateSessionAttribute(request.getSession(), "sessionCart", Cart.class);
		cart.removeItemById(request.getParameter("workingItemId"));
		return new ModelAndView("Cart", "cart", cart);
	}

}
