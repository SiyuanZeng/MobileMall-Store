package com.mindtree.mcse.mobilemall.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindtree.mcse.mobilemall.dao.ItemDao;
import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.LineItem;
import com.mindtree.mcse.mobilemall.domain.Order;
import com.mindtree.mcse.mobilemall.domain.Product;
import com.mindtree.mcse.mobilemall.domain.Review;
import com.mindtree.mcse.mobilemall.domain.hibernateannotation.HReview;

@SuppressWarnings({"rawtypes","unchecked"})
public class ItemDaoHibernateImpl extends HibernateDaoSupport implements ItemDao {

	@Override
	public void updateQuantity(Order order) throws DataAccessException {
		
		Query query = this.getSession().createSQLQuery(" UPDATE inventory SET qty = qty - :value WHERE itemid = :id");
		
		Iterator<LineItem> iter = order.getLineItems().iterator();
		LineItem currLineItem = null;
		String itemId;
		int quantity;
		while(iter.hasNext()) {
			currLineItem = iter.next();
			itemId = currLineItem.getItemId();
			quantity = currLineItem.getQuantity();
			
			query.setParameter("value", quantity);
			query.setParameter("id", itemId);
			query.executeUpdate();
		
		} //end of while
		
	} //end of updateQuantity
	
	@Override
	public int addReview(Review review) throws DataAccessException {
		return Integer.parseInt((String) this.getSession().save(review));
	}

	@Override
	public boolean isItemInStock(String itemId) throws DataAccessException {
		// TODO Auto-generated method stub7
		return true;
	}


	@Override
	public List getItemListByProduct(final String productId) throws DataAccessException {
		return (List)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				
				Query query = session.createQuery("SELECT item FROM Item item WHERE item.product.productId = :prodId ");
				query.setParameter("prodId", productId);
				List<Product> resultList = query.list();
				System.out.println(resultList);
				return resultList;
				
			}
		});
	}

	@Override
	public Item getItem(String itemId) {
		Item item = (Item)this.getHibernateTemplate().get(Item.class, itemId);
		System.out.println(item);
		return item;
	}

	@Override
	public void addReviewHibernateAnnotation(HReview review)
			throws DataAccessException {
		// TODO Auto-generated method stub
		
	}
	

}
