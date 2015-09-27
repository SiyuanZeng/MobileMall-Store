package com.mindtree.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HItem;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HProduct;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;
import com.mindtree.mcse.mobilemall.service.ItemAndReviewService;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.web.AddReviewController;
import com.mindtree.mcse.mobilemall.web.ValidateAndAddReviewController;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/WEB-INF/applicationContext.xml",
		"classpath:/WEB-INF/testDataAccessContext.xml",
		"classpath:/WEB-INF/webServicesContext.xml",
		"classpath:/WEB-INF/dataAccessContext.xml" })
public class TestValidateAndAddReviewController {
	ValidateAndAddReviewController controller = new ValidateAndAddReviewController();
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock ItemService itemService;
	@Mock ItemAndReviewService itemAndReviewService;
	@Mock BindException errors;
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this); 
		controller.setItemService(itemService);
	}

	@Test
	public void testGetUsersListException() {
		try {
			HItem hItem = new HItem();
			HReview hReview = new HReview("1", "EST-5", "Nokia", new Date(), "Title", "Description");
			SortedSet<HReview> reviews = new TreeSet<HReview>();
			reviews.add(hReview);
			hItem.sethReviews(reviews);
			
			when(request.getParameter("title")).thenReturn("Review Title");
			when(request.getParameter("description")).thenReturn("Description");
			when(request.getParameter("name")).thenReturn("Name");
			when(request.getParameter("itemId")).thenReturn("EST-5");
			
			//Mock void method
			doNothing().when(itemService).addReviewHibernateAnnotation(any(HReview.class));
			when(itemService.getHItem("EST-5")).thenReturn(hItem);

			
			ModelAndView model = controller.onSubmit(request, response, hReview, errors);
			String html = (String)model.getModelMap().get("review");
			assertThat(html,is(equalTo("<ul id='holder' class='two-col-special review-table'><li><div class='review-name'>Nokia</div><div class='review-time'>Sep 27 15</div></li><li><div class='review-title'>Title</div><div class='review-description'>Description</div><div class='review-helpful'>0 of 0 users found this review helpful</div></li></ul>")));
		} catch (Exception e) {
			fail();
		}
	}
	
	
	@Test
	public void testReferenceData() {
	// Protected method can not be tested. 
	}
}
