package com.mindtree.test;

import static org.junit.Assert.*;

import javax.xml.ws.WebServiceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.mcse.mobilemall.dao.ItemDao;
import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.event.AddReviewEvent;
import com.mindtree.mcse.mobilemall.event.InventoryCheckEvent;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.service.exception.ItemNotFoundException;
import com.mindtree.mcse.mobilemall.ws.InventoryWS;

import static org.mockito.Mockito.*;

public class TestItemService {
	
	ItemDao itemDao;
	InventoryWS invService;
	ItemService itemService;
	
	@Before
	public void init(){
		itemDao = mock(ItemDao.class);
		invService = mock(InventoryWS.class);
		itemService = new ItemService();
		itemService.setInvService(invService);
		itemService.setItemDao(itemDao);
	}

	@Test
	public void testIsItemInStock() {
		when(invService.checkItemInventory(any(InventoryCheckEvent.class))).thenReturn(10);
		int result = itemService.isItemInStock("abc");
		verify(invService).checkItemInventory(any(InventoryCheckEvent.class));
		assertEquals(10, result);
	} 
	
	@Test
	public void testGetItemException(){
		when(itemDao.getItem(any(String.class))).thenReturn(null);
		try {
			itemService.getItem(any(String.class));
			fail("Exception Expected");
		} catch (ItemNotFoundException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetItem(){
		Item item = new Item();
		item.setItemId("myitem");
		when(itemDao.getItem(any(String.class))).thenReturn(item);
		try {
			Item it = itemService.getItem("trial");
			verify(itemDao).getItem(any(String.class));
		} catch (ItemNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddReview(){
		Review review = new Review();
		review.setItemId("myitem");
		when(invService.addReview(any(AddReviewEvent.class))).thenReturn(1);
		try {
			int result = (int)itemService.addReview(review);
			verify(invService).addReview(any(AddReviewEvent.class));
			assertEquals(1, result);
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=ItemNotFoundException.class)
	public void testaddReviewWithItemNotFoundException() throws ItemNotFoundException{
		Review review = new Review();
		review.setItemId("myitem");
		when(invService.addReview(any(AddReviewEvent.class))).thenReturn(-1);
		itemService.addReview(review);
	}

}
