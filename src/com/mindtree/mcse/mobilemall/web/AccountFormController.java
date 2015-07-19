package com.mindtree.mcse.mobilemall.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.util.WebUtils;

import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.service.AccountService;
import com.mindtree.mcse.mobilemall.service.CategoryService;
import com.mindtree.mcse.mobilemall.service.ProductService;

@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
public class AccountFormController extends SimpleFormController {

	public static final String[] LANGUAGES = { "english", "japanese" };

	private AccountService accountService;
	private ProductService productService;
	private CategoryService categoryService;

	public void setAccountService(AccountService as) {
		this.accountService = as;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	public AccountFormController() {
		setSessionForm(true);
		setValidateOnBinding(false);
		setCommandName("accountForm");
		setFormView("EditAccountForm");
	}

	protected Object formBackingObject(HttpServletRequest request) 	throws Exception {
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {
			return new AccountForm(this.accountService.getAccount(userSession.getAccount().getUsername()));
		} else {
			return new AccountForm();
		}
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		AccountForm accountForm = (AccountForm) command;
		Account account = accountForm.getAccount();

		if (request.getParameter("account.listOption") == null) {
			account.setListOption(false);
		}
		if (request.getParameter("account.bannerOption") == null) {
			account.setBannerOption(false);
		}

		errors.setNestedPath("account");
		getValidator().validate(account, errors);
		errors.setNestedPath("");

		if (accountForm.isNewAccount()) {
			account.setStatus("OK");
			ValidationUtils.rejectIfEmpty(errors, "account.username",
					"USER_ID_REQUIRED", "User ID is required.");
			if (account.getPassword() == null
					|| account.getPassword().length() < 1
					|| !account.getPassword().equals(
							accountForm.getRepeatedPassword())) {
				errors.reject(
						"PASSWORD_MISMATCH",
						"Passwords did not match or were not provided. Matching passwords are required.");
			}
		} else if (account.getPassword() != null && account.getPassword().length() > 0) {
			if (!account.getPassword()
					.equals(accountForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH",
						"Passwords did not match. Matching passwords are required.");
			}
		}
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		model.put("languages", LANGUAGES);
		model.put("categories", this.categoryService.getCategoryList());
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		AccountForm accountForm = (AccountForm) command;
		try {
			if (accountForm.isNewAccount()) {
				System.out.println("Saving a new account.....");
				Account newacc = accountForm.getAccount();
				System.out.println(newacc);
				newacc.getSignon().setUsername(null);
				newacc.getProfile().setUsername(null);
				this.accountService.insertAccount(newacc);
			} else {
				System.out.println("Updating an existing account.....");
				Account acc = accountForm.getAccount();
				//System.out.println(acc);
				this.accountService.updateAccount(acc);
			}
		} catch (DataIntegrityViolationException ex) {
			errors.rejectValue("account.username", "USER_ID_ALREADY_EXISTS", "User ID already exists: choose a different ID.");
			return showForm(request, response, errors);
		}

		UserSession userSession = new UserSession(this.accountService.getAccount(accountForm.getAccount().getUsername()));

		PagedListHolder myList = new PagedListHolder(this.productService.getProductListByCategory(accountForm.getAccount().getFavoriteCategoryId()));
		
		myList.setPageSize(4);
		userSession.setMyList(myList);
		request.getSession().setAttribute("userSession", userSession);
		return super.onSubmit(request, response, command, errors);
	}

}
