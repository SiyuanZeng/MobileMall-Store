package com.mindtree.mcse.mobilemall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.service.AccountService;
import com.mindtree.mcse.mobilemall.service.ProductService;
import com.mindtree.mcse.mobilemall.web.dto.LoginDto;

@SuppressWarnings("deprecation")
public class AdminSignonController extends SimpleFormController {

	private ProductService productService;
	private AccountService accountService;

	public void setProductService(ProductService ps) {
		this.productService = ps;
	}
	
	public void setAccountService(AccountService as) {
		this.accountService = as;
	}
	
	public AdminSignonController(){
		setCommandClass(LoginDto.class);
		setCommandName("loginform");
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
		HttpServletResponse response, Object command, BindException errors)
		throws Exception {
 
		LoginDto login = (LoginDto)command;
		Account account = this.accountService.getAccount(login.getUsername(), login.getPassword());
		
		if (account == null || !account.isAdmin()) {
			return new ModelAndView("Error", "message", "Invalid username or password.  Signon failed.");
		}
		else {
			UserSession userSession = new UserSession(account);
			request.getSession().setAttribute("userSession", userSession);
			String forwardAction = request.getParameter("forwardAction");
			if (forwardAction != null) {
				response.sendRedirect(forwardAction);
				return null;
			}
			else {
				return new ModelAndView("redirect:/admin/listAllOrders.do");
			}
		}
 
	}
	

}
