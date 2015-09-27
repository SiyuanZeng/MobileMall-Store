package com.mindtree.mcse.mobilemall.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HItem;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.service.exception.ItemNotFoundException;

public class ValidateAndAddReviewController extends SimpleFormController{
	private Logger logger = Logger.getLogger(ValidateAndAddReviewController.class);
	private ItemService itemService;
	
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ValidateAndAddReviewController(){
		logger.debug("ValidateAndAddReviewController, set command class ************************************* ");
		setCommandClass(HReview.class);
		setCommandName("hReview");
		setSessionForm(true);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request){
		logger.debug("formBackingObject() method ************************************* ");
		return new HReview();
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
		logger.debug("onSubmit() method ************************************* ");
		ModelAndView modelAndView = new ModelAndView("Review");
		
		try {
			HReview hReview = (HReview)command;
			itemService.addReviewHibernateAnnotation(hReview);
			HItem hItem = this.itemService.getHItem(hReview.getItemId());
			logger.debug("hItem ************************************* "+hItem);
			modelAndView.addObject("item", hItem);
		} catch (ItemNotFoundException e) {
			logger.error("IOEXception occured:", e);
		}
		return modelAndView;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) {
		logger.debug("referenceData() method ************************************* ");
		Map referenceData = new HashMap();
		try {
			logger.debug("Siyuan Zeng");
			String itemId = request.getParameter("itemId");
			HItem hItem = this.itemService.getHItem(itemId);
			referenceData.put("product", hItem.getHProduct());
			referenceData.put("item", hItem);
		} catch (Exception e) {
			logger.error("IOEXception occured:", e);
		}
		return referenceData;
	}
}