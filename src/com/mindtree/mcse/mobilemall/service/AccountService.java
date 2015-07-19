package com.mindtree.mcse.mobilemall.service;

import java.util.List;

import com.mindtree.mcse.mobilemall.dao.AccountDao;
import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.service.exception.AccountNotFoundException;

public class AccountService {

	private AccountDao accountDao;


	//-------------------------------------------------------------------------
	// Setter methods for dependency injection
	//-------------------------------------------------------------------------

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public Account getAccount(String username) {
		return this.accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return this.accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) {
		this.accountDao.insertAccount(account);
	}

	public void updateAccount(Account account) {
		this.accountDao.updateAccount(account);
	}

	@SuppressWarnings("rawtypes")
	public List getUsernameList() throws AccountNotFoundException{
		List accounts = this.accountDao.getUsernameList();
		if(null != accounts && accounts.size() > 0){
			return accounts;
		} else {
			throw new AccountNotFoundException("No users were found");
		}
	}
	
	public List<Account> getRegisteredUsers() throws AccountNotFoundException {
		
		List accounts = this.accountDao.getRegisteredUsers();
		if(null != accounts && accounts.size() > 0){
			return accounts;
		} else {
			throw new AccountNotFoundException("No users were found");
		}
	}
	
	public void deleteAllAccounts(){
		this.accountDao.deleteAll();
	}
	
	public void deleteAccount(Account acc){
		this.accountDao.deleteAccount(acc);
	}
}
