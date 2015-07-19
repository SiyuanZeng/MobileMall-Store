package com.mindtree.mcse.mobilemall.service;

import java.util.List;

import javax.xml.ws.WebServiceException;

import com.mindtree.mcse.mobilemall.dao.ItemDao;
import com.mindtree.mcse.mobilemall.dao.ReviewDao;
import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.event.InventoryCheckEvent;
import com.mindtree.mcse.mobilemall.service.exception.ItemNotFoundException;
import com.mindtree.mcse.mobilemall.ws.InventoryWS;

public class ItemAndReviewService {

	private ReviewDao reviewDao;
	private ItemDao itemDao;
	private InventoryWS invService;
	
	public ReviewDao getReviewDao() {
		return reviewDao;
	}

	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public InventoryWS getInvService() {
		return invService;
	}

	public void setItemDao(ReviewDao reviewDao, ItemDao itemDao) {
		this.reviewDao = reviewDao;
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

	
	//get Item and review list . 
	public Item getItem(String itemId) throws ItemNotFoundException {				
		Item it = this.reviewDao.getItemAndReviewList(itemId);
		if(null != it){
			return it;
		} else {
			throw new ItemNotFoundException("Item with id : "+itemId+" was not found");
		}
	}

//	public boolean isItemInStock(String itemId) {
//		return this.itemDao.isItemInStock(itemId);
//	}
	
	public int isItemInStock(String itemId) throws WebServiceException {
		
		InventoryCheckEvent event = new InventoryCheckEvent();
		event.setItemId(itemId);
		event.setQuantity(1);
		try{
			int inStock = invService.checkItemInventory(event);
			System.out.println("WS Returned for " + itemId + "::" + inStock);
			return inStock;
		}
		catch(Exception e){
			throw new WebServiceException("Error in webservice call", e);
		}
	}
	
	public void updateInventory(String itemId, int quantity) throws WebServiceException {
		InventoryCheckEvent event = new InventoryCheckEvent();
		event.setItemId(itemId);
		event.setQuantity(quantity);
		try {
			invService.updateItemInventory(event);
			System.out.println("WS Returned");
		} catch(Exception e) {
			throw new WebServiceException("Error in webservice call", e);
		}
	}
	
}//eof
