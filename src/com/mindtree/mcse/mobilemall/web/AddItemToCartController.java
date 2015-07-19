package com.mindtree.mcse.mobilemall.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.domain.Cart;
import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.service.ItemService;


public class AddItemToCartController implements Controller {
	
	private ItemService itemService;
	
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}	


	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cart cart = (Cart) WebUtils.getOrCreateSessionAttribute(request.getSession(), "sessionCart", Cart.class);
		Map model = new HashMap();
		String workingItemId = request.getParameter("workingItemId");
		if (cart.containsItemId(workingItemId)) {
			cart.incrementQuantityByItemId(workingItemId);
		}
		else {
			// isInStock is a "real-time" property that must be updated
			// every time an item is added to the cart, even if other
			// item details are cached.
			int inStock = this.itemService.isItemInStock(workingItemId);
			boolean isInStock = inStock > 0;
			if(inStock > 0){
				Item item = this.itemService.getItem(workingItemId);
				model.put("available", inStock);
				cart.addItem(item, isInStock);
			} else {
				model.put("message", "Item is out of stock. Please try later!!");
			}
		}
		model.put("cart", cart);
		return new ModelAndView("Cart",model);
	}




}
