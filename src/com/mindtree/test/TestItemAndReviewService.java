package com.mindtree.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.service.ItemAndReviewService;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.web.AddReviewController;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/WEB-INF/applicationContext.xml",
		"classpath:/WEB-INF/testDataAccessContext.xml",
		"classpath:/WEB-INF/webServicesContext.xml",
		"classpath:/WEB-INF/dataAccessContext.xml" })
public class TestItemAndReviewService {
	AddReviewController addReviewController = new AddReviewController();
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock ItemService itemService;
	@Mock ItemAndReviewService itemAndReviewService;
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this); 
		addReviewController.setItemAndReviewService(itemAndReviewService);
		addReviewController.setItemService(itemService);
	}

	@Test
	public void testGetUsersListException() {
		try {
			Item item = new Item();
			Review review = new Review("1", "EST-5", "Nokia", new Date(), "Title", "Description");
			Set<Review> reviews = new HashSet<Review>();
			reviews.add(review);
			item.setReviews(reviews);
			
			when(request.getParameter("title")).thenReturn("Review Title");
			when(request.getParameter("description")).thenReturn("Description");
			when(request.getParameter("name")).thenReturn("Name");
			when(request.getParameter("itemId")).thenReturn("EST-5");
			when(itemService.addReview(any(Review.class))).thenReturn(1);
			when(itemAndReviewService.getItem("EST-5")).thenReturn(item);

			
			ModelAndView model = addReviewController.handleRequest(request, response);
			String html = (String)model.getModelMap().get("review");
			assertEquals(html,"<table><tr><td><div class='review-name'>Nokia</div><div class='review-time'>Jun 02 15</div><td><div class='review-title'>Title</div><div class='review-description'>Description</div><div class='review-helpful'>0 of 0 users found this review helpful</div></td></tr></table>");
		} catch (Exception e) {
			fail();
		}
	}
}
