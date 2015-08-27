package com.mindtree.mcse.mobilemall.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.service.ItemService;

@SuppressWarnings({"rawtypes","unchecked"})
public class ViewItemController implements Controller {

	private ItemService itemService;
	
	public void setItemService(ItemService is){
		this.itemService = is;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		Item item = this.itemService.getItem(itemId);
		Map model = new HashMap();
		model.put("item", item);
		model.put("product", item.getProduct());
		return new ModelAndView("Item", model);
	}
/*	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		HItem hItem = this.itemService.getHItem(itemId);
		Map model = new HashMap();
		model.put("hItem", hItem);
		model.put("product", hItem.getHProduct());
		return new ModelAndView("ValidatorItem", model);
	}*/
}
