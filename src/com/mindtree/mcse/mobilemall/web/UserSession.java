 package com.mindtree.mcse.mobilemall.web;

import java.io.Serializable;

import org.springframework.beans.support.PagedListHolder;

import com.mindtree.mcse.mobilemall.domain.Account;

public class UserSession implements Serializable {

	private static final long serialVersionUID = -764544970182460051L;

	private Account account;

	private PagedListHolder myList;

	public UserSession(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setMyList(PagedListHolder myList) {
		this.myList = myList;
	}

	public PagedListHolder getMyList() {
		return myList;
	}

}
