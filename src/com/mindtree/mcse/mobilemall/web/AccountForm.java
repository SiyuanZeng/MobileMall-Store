package com.mindtree.mcse.mobilemall.web;

import java.io.Serializable;

import com.mindtree.mcse.mobilemall.domain.Account;


public class AccountForm implements Serializable {


	private static final long serialVersionUID = -158774860175886211L;

	private Account account;

	private boolean newAccount;

	private String repeatedPassword;

	public AccountForm(Account account) {
		this.account = account;
		this.newAccount = false;
	}

	public AccountForm() {
		this.account = new Account();
		this.newAccount = true;
	}

	public Account getAccount() {
		return account;
	}

	public boolean isNewAccount() {
		return newAccount;
	}
	
	public void setIsNewAccount(boolean val){
		this.newAccount = val;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

}
