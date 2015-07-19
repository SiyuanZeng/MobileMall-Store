package com.mindtree.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.domain.Signon;
import com.mindtree.mcse.mobilemall.service.AccountService;
import com.mindtree.mcse.mobilemall.service.exception.AccountNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:/WEB-INF/applicationContext.xml",
								 "classpath:/WEB-INF/testDataAccessContext.xml",
								 "classpath:/WEB-INF/webServicesContext.xml",
								 "classpath:/WEB-INF/dataAccessContext.xml"
								 })  
public class TestAccountService {

	@Autowired
	private AccountService accountService;
	
	@Before
	public void cleanup(){
		accountService.deleteAllAccounts();
	}
	
	
	@Test
	public void testGetUsersListException(){
		try {
			cleanup();
			List<Account> accounts = accountService.getRegisteredUsers();
			fail("Exception not thrown");
		} catch (AccountNotFoundException e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testGetUserNameListException(){
		try {
			cleanup();
			List accounts = accountService.getUsernameList();
			fail("Exception not thrown");
		} catch (AccountNotFoundException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetUsersList(){
		Account acc = new Account("trial", "my", "", "", "", "", "", "Bangalore", "", "", "", "", "", "", "", null);
		accountService.insertAccount(acc);
		List<Account> accounts = new ArrayList<>();
		try {
			accounts = accountService.getRegisteredUsers();
		} catch (AccountNotFoundException e) {
			fail("Data could not be found");
		}
		if(accounts.size() > 0 && accounts.get(0).getCity().equals("Bangalore")){
			assertTrue(true);
		}
		else {
			fail("Data could not be found");
		}
	}
	
	@Test
	public void testGetAccountByUsername(){
		cleanup();
		Account acc = new Account("trial", "my", "", "", "", "", "", "Bangalore", "", "", "", "", "", "", "", null);
		accountService.insertAccount(acc);
		Account account = accountService.getAccount("trial");
		if(acc.getUsername().equals(account.getUsername())){
			assertTrue(true);
		} else {
			fail("Required data not found");
		}
	}
	
	@Test
	public void testGetAccountByUsernamePassword(){
		cleanup();
		Signon s = new Signon(null, "my", false, null);
		Account acc = new Account("trial1", "my", "", "", "", "", "", "Bangalore", "", "", "", "", "", "", "", s);
		accountService.deleteAccount(acc);
		s.setAccount(acc);
		accountService.insertAccount(acc);
		Account account = accountService.getAccount("trial1", "my");
		if(acc.getUsername().equals(account.getUsername())){
			accountService.deleteAccount(acc);
			assertTrue(true);
		} else {
			fail("Required data not found");
		}
	}
}
