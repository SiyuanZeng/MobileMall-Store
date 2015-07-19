package com.mindtree.mcse.mobilemall.domain;

import java.io.Serializable;


public class Signon implements Serializable {

	public Signon(String username, String password, boolean admin,
			Account account) {
		super();
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.account = account;
	}

	private static final long serialVersionUID = -2564201233114314076L;
	private String username;
	private String password;
	private boolean admin;

	private Account account;
	
	public Signon() {
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	public Account getAccount() {
		return this.account;
	}
	
	public void setAccount(Account newAccount) {
		this.account = newAccount;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}
}
