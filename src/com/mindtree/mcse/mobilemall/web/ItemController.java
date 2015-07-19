package com.mindtree.mcse.mobilemall.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.service.ItemService;

@Controller
public class ItemController {
	final String ADD_REVIEW = "/shop/test.do"; 
	
	private ItemService itemService;
	
	public void setItemService(ItemService itemService){
		this.itemService = itemService;
	}
	
	@RequestMapping(value = ADD_REVIEW, method = RequestMethod.POST)
	protected ModelAndView addReview(
			@RequestParam("title") String title,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("itemId") String itemId) throws Exception {
		
		System.out.println("Hello");
		Map model = new HashMap();
		Review review = new Review("0", itemId, name, new Date(), title, description);
		itemService.addReview(review);
		return new ModelAndView("Item", model);
	}
	
}