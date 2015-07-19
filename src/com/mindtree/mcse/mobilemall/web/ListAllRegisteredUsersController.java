package com.mindtree.mcse.mobilemall.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.service.AccountService;
import com.mindtree.mcse.mobilemall.service.OrderService;
import com.mindtree.mcse.mobilemall.service.exception.AccountNotFoundException;

@SuppressWarnings({"rawtypes","unchecked"})
public class ListAllRegisteredUsersController implements Controller {

	private AccountService accountService;


	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}


	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map model = new HashMap();
	
		try {
			model.put("accountList", this.accountService.getRegisteredUsers());
		} catch (AccountNotFoundException e) {
			// TODO Auto-generated catch block
			model.put("message", "No accounts were found");
			model.put("accountList", new ArrayList<Account>());
		}
		return new ModelAndView("ListAllUsers", model);
	}

}
