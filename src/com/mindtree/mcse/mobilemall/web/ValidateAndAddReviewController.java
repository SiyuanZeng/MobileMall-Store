package com.mindtree.mcse.mobilemall.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HItem;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;
import com.mindtree.mcse.mobilemall.service.ItemService;

public class ValidateAndAddReviewController extends SimpleFormController{
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
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		// Get Item and populate
		return new HReview();
	}
	/* Used to get item with review and return to ValidatorWithItem.jsp
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// Get Item and populate
		String itemId = request.getParameter("itemId");
		System.out.println("form backing Object HItem");
		HItem hItem = this.itemService.getHItem(itemId);
		System.out.println("After form backing Object HItem");
		Map model = new HashMap();
		model.put("item", hItem);
		model.put("product", hItem.getHProduct());
		return new ModelAndView("ValidatorItem", model);
	}
*/	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		HReview review = new HReview("0", request.getParameter("itemId"), request.getParameter("name"), new Date(), request.getParameter("title"), request.getParameter("description"));
		itemService.addReviewHibernateAnnotation(review);
		Item item = this.itemService.getItem(request.getParameter("itemId"));
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yy");	
		String userInfo = "<table>";
		for(Review rev: item.getReviews()){
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
}