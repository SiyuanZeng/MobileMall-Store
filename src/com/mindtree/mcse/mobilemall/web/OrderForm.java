package com.mindtree.mcse.mobilemall.web;

import java.io.Serializable;

import com.mindtree.mcse.mobilemall.domain.Order;


public class OrderForm implements Serializable {


	private static final long serialVersionUID = -7040745993897371990L;

	private final Order order = new Order();

	private boolean shippingAddressRequired;

	private boolean confirmed;

	public Order getOrder() {
		return order;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

}
