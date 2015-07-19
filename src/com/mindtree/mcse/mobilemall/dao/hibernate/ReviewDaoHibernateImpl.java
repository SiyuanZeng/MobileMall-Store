package com.mindtree.mcse.mobilemall.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindtree.mcse.mobilemall.dao.ReviewDao;
import com.mindtree.mcse.mobilemall.domain.Item;
import com.mindtree.mcse.mobilemall.domain.Review;

@SuppressWarnings({"rawtypes","unchecked"})
public class ReviewDaoHibernateImpl extends HibernateDaoSupport implements ReviewDao {

	
	// Confusion with product id and item id. 
	@Override
	public Item getItemAndReviewList(final String itemId) throws DataAccessException {
		return (Item) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery("Select i.* from Item i where i.itemid = :itemid")
						.addEntity("i", Item.class);
				query.setParameter("itemid", itemId);
				Item result = (Item)query.uniqueResult();
				System.out.println(result);
				return result;
			}
		});
	}

	@Override
	public Review getReview(String itemId) throws DataAccessException {
		Review review = (Review) this.getHibernateTemplate().get(Review.class,itemId);
		System.out.println(review);
		return review;                           
	}
}
