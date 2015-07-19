package com.mindtree.mcse.mobilemall.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindtree.mcse.mobilemall.dao.CategoryDao;
import com.mindtree.mcse.mobilemall.domain.Category;
import com.mindtree.mcse.mobilemall.domain.Product;



@SuppressWarnings({"rawtypes","unchecked"})
public class CategoryDaoHibernateImpl extends HibernateDaoSupport implements CategoryDao {

	@Override
	public List getCategoryList() throws DataAccessException {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				
				Query query = session.createQuery("FROM Category ");
				List<Product> resultList = query.list();
				System.out.println(resultList);
				return resultList;
			}
		});		
	}
	
	@Override
	public Category getCategory(String categoryId) throws DataAccessException {
		Category cat = (Category)this.getHibernateTemplate().get(Category.class, categoryId);
		System.out.println(cat);
		return cat;
	}
	
	@Override
	public void deleteAll() throws DataAccessException {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				
				Query query = session.createQuery("DELETE FROM Category");
				query.executeUpdate();
				return null;
			}
		});	
	}
	
	@Override
	public void save(Category category) throws DataAccessException {
		getHibernateTemplate().save(category);
	}
	


}
