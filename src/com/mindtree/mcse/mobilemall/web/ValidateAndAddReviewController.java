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
		setCommandClass(HReview.class);
		setCommandName("hReview");
		setSessionForm(true);
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		// Get Item and populate
		return new HReview();
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		HReview review = (HReview)command;
		itemService.addReviewHibernateAnnotation(review);
		HItem hItem = this.itemService.getHItem(review.getItemId());
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yy");	
		System.out.println(hItem);
		String userInfo = "<table>";
		for(HReview rev: hItem.gethReviews()){
			userInfo += "<tr>"
							+"<td>"
								+ "<div class='review-name'>" + rev.getName()+ "</div>"
								+ "<div class='review-time'>" + sdf.format(rev.getTimeStamp())+ "</div>"
							+"<td>"
								+ "<div class='review-title'>" + rev.getTitle()+ "</div>"
								+ "<div class='review-description'>" + rev.getDescription()+ "</div>"
								+ "<div class='review-helpful'>0 of 0 users found this review helpful</div>"
							+"</td>"
					      + "</tr>";
		}
		userInfo += "</table>";
		ModelAndView modelAndView = new ModelAndView("Review");
        modelAndView.addObject("review", userInfo);
        return modelAndView;
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		logger.info("Siyuan Zeng");
		Map referenceData = new HashMap();
		String itemId = request.getParameter("itemId");
		HItem hItem = this.itemService.getHItem(itemId);
		referenceData.put("product", hItem.getHProduct());
		referenceData.put("item", hItem);
		return referenceData;
	}
}