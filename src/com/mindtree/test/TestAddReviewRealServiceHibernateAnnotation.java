package com.mindtree.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.service.exception.ItemNotFoundException;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:/WEB-INF/applicationContext.xml",
								"classpath:/WEB-INF/testDataAccessContext.xml",
								"classpath:/WEB-INF/webServicesContext.xml", 
								"classpath:/WEB-INF/dataAccessContext.xml"
								}) 
public class TestAddReviewRealServiceHibernateAnnotation {

	@Autowired
	private ItemService itemService;
	
	@Test
	public void testAddReviewHibernateAnnotation(){
		HReview review = new HReview("1", "EST-5", "Nokia", new Date(), "TitleReal", "Description");
		try {
			itemService.addReviewHibernateAnnotation(review);
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
		}
	}

}
