package com.mindtree.mcse.mobilemall.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.service.ItemAndReviewService;
import com.mindtree.mcse.mobilemall.service.ItemService;

@SuppressWarnings({"rawtypes","unchecked"})
public class AddReviewController implements Controller {

	private ItemService itemService;
	private ItemAndReviewService itemAndReviewService;
	
	public void setItemService(ItemService is){
		this.itemService = is;
	}
	
	public void setItemAndReviewService(ItemAndReviewService itemAndReviewService){
		this.itemAndReviewService = itemAndReviewService;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Review review = new Review("0", request.getParameter("itemId"), request.getParameter("name"), new Date(), request.getParameter("title"), request.getParameter("description"));
		itemService.addReview(review);
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
