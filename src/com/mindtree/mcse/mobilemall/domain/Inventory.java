
package com.mindtree.mcse.mobilemall.domain;

import java.io.Serializable;

import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HItem;

public class Inventory implements Serializable{

	private static final long serialVersionUID = 2080017291348056747L;
	private String itemId;
	private int quantity;
	private Item item;
	private HItem itemH;

	public Inventory() {
	}
	
	public String getItemId() {
		return this.itemId;
	}
	
	public void setItemId(String newItemId) {
		this.itemId = newItemId;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public void setItem(Item newItem) {
		this.item = newItem;
	}

	public HItem getItemH() {
		return itemH;
	}

	public void setItemH(HItem itemH) {
		this.itemH = itemH;
	}
	
}
