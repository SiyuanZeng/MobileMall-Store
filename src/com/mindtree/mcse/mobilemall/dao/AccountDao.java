package com.mindtree.mcse.mobilemall.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mindtree.mcse.mobilemall.domain.Account;

@SuppressWarnings("rawtypes")
public interface AccountDao {

	Account getAccount(String username) throws DataAccessException;

	Account getAccount(String username, String password) throws DataAccessException;

	void insertAccount(Account account) throws DataAccessException;

	void updateAccount(Account account) throws DataAccessException;

	List getUsernameList() throws DataAccessException;
	
	List getRegisteredUsers() throws DataAccessException;

	void deleteAll() throws DataAccessException;

	void deleteAccount(Account acc) throws DataAccessException;

}
