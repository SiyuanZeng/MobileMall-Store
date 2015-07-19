package com.mindtree.mcse.mobilemall.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.domain.Cart;
import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.service.AccountService;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.service.OrderService;
import com.mindtree.mcse.mobilemall.web.validators.OrderValidator;

@SuppressWarnings({"deprecation","rawtypes","unchecked"})
public class OrderFormController extends AbstractWizardFormController {

	private AccountService accountService;
	private OrderService orderService;
	private ItemService itemService;

	public void setOrderService(OrderService os) {
		this.orderService = os;
	}
	
	public void setAccountService(AccountService as) {
		this.accountService = as;
	}
	

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public OrderFormController() {
		setCommandName("orderForm");
		setPages(new String[] {"NewOrderForm", "ShippingForm", "ConfirmOrder"});
	}

	protected Object formBackingObject(HttpServletRequest request) throws ModelAndViewDefiningException {
		UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
		Cart cart = (Cart) request.getSession().getAttribute("sessionCart");
		if (cart != null) {
			// Re-read account from DB at team's request.
			Account account = this.accountService.getAccount(userSession.getAccount().getUsername());
			OrderForm orderForm = new OrderForm();
			orderForm.getOrder().init(account, cart);
			return orderForm;
		}
		else {
			ModelAndView modelAndView = new ModelAndView("Error");
			modelAndView.addObject("message", "An order could not be created because a cart could not be found.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) {
		if (page == 0 && request.getParameter("shippingAddressRequired") == null) {
			OrderForm orderForm = (OrderForm) command;
			orderForm.setShippingAddressRequired(false);
		}
	}

	protected Map referenceData(HttpServletRequest request, int page) {
		if (page == 0) {
			List creditCardTypes = new ArrayList();
			creditCardTypes.add("Visa");
			creditCardTypes.add("MasterCard");
			creditCardTypes.add("American Express");
			Map model = new HashMap();
			model.put("creditCardTypes", creditCardTypes);
			return model;
		}
		return null;
	}

	protected int getTargetPage(HttpServletRequest request, Object command, Errors errors, int currentPage) {
		OrderForm orderForm = (OrderForm) command;
		if (currentPage == 0 && orderForm.isShippingAddressRequired()) {
			return 1;
		}
		else {
			return 2;
		}
	}

	protected void validatePage(Object command, Errors errors, int page) {
		OrderForm orderForm = (OrderForm) command;
		OrderValidator orderValidator = (OrderValidator) getValidator();
		errors.setNestedPath("order");
		switch (page) {
			case 0:
				orderValidator.validateCreditCard(orderForm.getOrder(), errors);
				orderValidator.validateBillingAddress(orderForm.getOrder(), errors);
				break;
			case 1:
				orderValidator.validateShippingAddress(orderForm.getOrder(), errors);
		}
		errors.setNestedPath("");
	}

	protected ModelAndView processFinish(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) {
		OrderForm orderForm = (OrderForm) command;
		this.orderService.insertOrder(orderForm.getOrder());
		request.getSession().removeAttribute("sessionCart");
		Map model = new HashMap();
		model.put("order", orderForm.getOrder());
		model.put("message", "Thank you, your order has been submitted.");
		return new ModelAndView("ViewOrder", model);
	}
}
