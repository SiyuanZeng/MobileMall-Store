package com.mindtree.mcse.mobilemall.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.service.ItemService;

@SuppressWarnings({"rawtypes","unchecked"})
public class ViewItemController implements Controller {
	private Logger logger = Logger.getLogger(ViewItemController.class);

	private ItemService itemService;
	
	public void setItemService(ItemService is){
		this.itemService = is;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("ViewItemController, handleRequest() ************************************* ");
		String itemId = request.getParameter("itemId");
		Item item = this.itemService.getItem(itemId);
		Map model = new HashMap();
		model.put("item", item);
		model.put("product", item.getProduct());
		return new ModelAndView("Item", model);
	}
}
