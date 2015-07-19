package com.mindtree.mcse.mobilemall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.service.AccountService;
import com.mindtree.mcse.mobilemall.service.ProductService;


public class SignonController implements Controller {

	private ProductService productService;
	private AccountService accountService;

	public void setProductService(ProductService ps) {
		this.productService = ps;
	}
	
	public void setAccountService(AccountService as) {
		this.accountService = as;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = this.accountService.getAccount(username, password);
		
		if (account == null || account.isAdmin()) {
			return new ModelAndView("Error", "message", "Invalid username or password.  Signon failed.");
		}
		else {
			UserSession userSession = new UserSession(account);

			PagedListHolder myList = new PagedListHolder(this.productService.getProductListByCategory(account.getFavoriteCategoryId()));
			
			myList.setPageSize(4);
			userSession.setMyList(myList);
			request.getSession().setAttribute("userSession", userSession);
			String forwardAction = request.getParameter("forwardAction");
			if (forwardAction != null) {
				response.sendRedirect(forwardAction);
				return null;
			}
			else {
				return new ModelAndView("index");
			}
		}
	}

}
