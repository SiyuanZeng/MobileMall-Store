package com.mindtree.mcse.mobilemall.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindtree.mcse.mobilemall.dao.AccountDao;
import com.mindtree.mcse.mobilemall.domain.Account;
import com.mindtree.mcse.mobilemall.domain.Order;
import com.mindtree.mcse.mobilemall.domain.Signon;


public class AccountDaoHibernateImpl extends HibernateDaoSupport implements AccountDao {

	@Override
	public Account getAccount(String accountId) throws DataAccessException {
		return (Account)this.getHibernateTemplate().get(Account.class, accountId);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Account getAccount(final String username, final String password) throws DataAccessException {

		Signon signon =  (Signon) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
			
				Query query = session.createQuery("SELECT signon FROM Signon signon WHERE signon.username = :un AND signon.password = :pw ");
				query.setParameter("un", username);
				query.setParameter("pw", password);
				List<Signon> so = query.list();
				if(so.size() > 0)
					return so.get(0);
				else return null;
				
			}
		});
		if(signon != null)
			return signon.getAccount();
		else
			return null;
		
		
	}

	@Override
	public void insertAccount(Account account) throws DataAccessException {
			this.getHibernateTemplate().save(account);
	}

	@Override
	public void updateAccount(Account account) throws DataAccessException {
		
		try{
			this.getSession().merge(account);
			System.out.println(account.getFavoriteCategoryId());
			this.getHibernateTemplate().saveOrUpdate(account);
		} catch(DataAccessException e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getUsernameList() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List getRegisteredUsers() throws DataAccessException {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				
				Query query = session.createQuery("FROM Account");
				List resultList = query.list();
				System.out.println(resultList);
				return resultList;
				
			}
		});	
	}
	
	@Override
	public void deleteAll() throws DataAccessException {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("DELETE FROM Account");
				query.executeUpdate();
				return null;
			}
		});	
	}
	
	@Override
	public void deleteAccount(final Account acc) throws DataAccessException {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {				
				Query query = session.createQuery("DELETE FROM Signon WHERE username = :userName");
				query.setParameter("userName", acc.getUsername());
				query.executeUpdate();
				return null;
			}
		});	
	}
}
