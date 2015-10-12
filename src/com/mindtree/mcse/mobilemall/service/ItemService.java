package com.mindtree.mcse.mobilemall.service;

import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mindtree.mcse.mobilemall.dao.ItemDao;
import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HItem;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;
import com.mindtree.mcse.mobilemall.event.AddReviewEvent;
import com.mindtree.mcse.mobilemall.event.GetHItemEvent;
import com.mindtree.mcse.mobilemall.event.GetItemEvent;
import com.mindtree.mcse.mobilemall.event.InventoryCheckEvent;
import com.mindtree.mcse.mobilemall.service.exception.ItemNotFoundException;
import com.mindtree.mcse.mobilemall.ws.InventoryWS;

public class ItemService {
	private Logger logger = Logger.getLogger(ItemService.class);
	private ItemDao itemDao;
	private InventoryWS invService;
	Gson gson = new Gson();
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setInvService(InventoryWS service){
		this.invService = service;
	}
	
	@SuppressWarnings("rawtypes")
	public List getItemListByProduct(String productId) throws ItemNotFoundException{
		List itList = this.itemDao.getItemListByProduct(productId);
		if(null != itList && itList.size() > 0){
			return itList;
		} else {
			throw new ItemNotFoundException("No items found");
		}
	}

	public Item getItem(String itemId) throws ItemNotFoundException {
		GetItemEvent event = new GetItemEvent();
		event.setItemId(itemId);
		Item it = invService.getItem(event);
//		Item it = gson.fromJson(str, Item.class);
		if(null != it){
			return it;
		} else {
			throw new ItemNotFoundException("Item with id : "+itemId+" was not found");
		}
	}
	
	public HItem getHItem(String hItemId) throws ItemNotFoundException {
		GetHItemEvent event = new GetHItemEvent();
		event.setHItemId(hItemId);
		HItem it = invService.getHItem(event);
		if(null != it){
			return it;
		} else {
			throw new ItemNotFoundException("Item with id : "+hItemId+" was not found");
		}
	}
	
	public int addReview(Review review) throws ItemNotFoundException {
		AddReviewEvent event = new AddReviewEvent();
		String json = gson.toJson(review);
		event.setReview(json);
		int result = -1;
		result = invService.addReview(event);
		logger.debug("WS Returned for " + result);
		if (-1 != result) {
			return result;
		} else {
			throw new ItemNotFoundException("Review is not saved.");
		}
	}
	
	public void addReviewHibernateAnnotation(HReview hReview) throws ItemNotFoundException {
		AddReviewEvent event = new AddReviewEvent();
		String json = gson.toJson(hReview);
		event.sethReview(json);
		int result = -1;
		invService.addReviewHibernateAnnotation(event);
		logger.debug("WS Returned for " + result);
	}

	public int isItemInStock(String itemId) throws WebServiceException {
		InventoryCheckEvent event = new InventoryCheckEvent();
		event.setItemId(itemId);
		event.setQuantity(1);
		try{
			int inStock = invService.checkItemInventory(event);
			logger.debug("WS Returned for " + itemId + "::" + inStock);
			return inStock;
			
		} catch(Exception e){
			throw new WebServiceException("Error in webservice call", e);
		}
		
	}
	
	public void updateInventory(String itemId, int quantity) throws WebServiceException {
		InventoryCheckEvent event = new InventoryCheckEvent();
		event.setItemId(itemId);
		event.setQuantity(quantity);
		try{
			invService.updateItemInventory(event);
			System.out.println("WS Returned");
			
		}
		catch(Exception e){
			throw new WebServiceException("Error in webservice call", e);
		}
	}
}//eof
