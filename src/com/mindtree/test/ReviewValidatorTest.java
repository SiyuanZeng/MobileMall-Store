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
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;
import com.mindtree.mcse.mobilemall.service.ItemAndReviewService;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.web.AddReviewController;
import com.mindtree.mcse.mobilemall.web.ValidateAndAddReviewController;
import com.mindtree.mcse.mobilemall.web.validators.ReviewValidator;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//		"classpath:/WEB-INF/applicationContext.xml",
//		"classpath:/WEB-INF/testDataAccessContext.xml",
//		"classpath:/WEB-INF/webServicesContext.xml",
//		"classpath:/WEB-INF/dataAccessContext.xml" })
public class ReviewValidatorTest {
	ValidateAndAddReviewController controller = new ValidateAndAddReviewController();
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock ItemService itemService;
	@Mock ItemAndReviewService itemAndReviewService;
	@Mock Object command; 
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this); 
		controller.setItemService(itemService);
	}

	@Test
	public void testGetUsersListException() {
		try {
			HReview hReview = new HReview("1", null, "Nokia", new Date(), null, "Description");
			ReviewValidator validator = new ReviewValidator();  
			BindException errors = new BindException(hReview, "review");
			validator.validate(hReview, errors);
			assertEquals(2, errors.getFieldErrorCount());
		} catch (Exception e) {
			fail();
		}
	}
}
