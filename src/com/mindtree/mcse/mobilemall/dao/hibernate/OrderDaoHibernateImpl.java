/*
 * MyPetStore Project.
 */
package com.mindtree.mcse.mobilemall.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindtree.mcse.mobilemall.dao.OrderDao;
import com.mindtree.mcse.mobilemall.domain.Order;
import com.mindtree.mcse.mobilemall.domain.Product;
import com.mindtree.mcse.mobilemall.domain.Sequence;

public class OrderDaoHibernateImpl extends HibernateDaoSupport implements OrderDao {
	 
	private static final String ORDER_SEQUENCE_NAME = "ordernum";

	private int getSeqNum(String name) {
		int seqNum = -1;
		Sequence seq = this.getHibernateTemplate().get(Sequence.class, name);
		seqNum = seq.getSeqnum();
		seq.increment();
		this.getHibernateTemplate().update(seq);
		return seqNum;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Order> getOrdersByUsername(String username) throws DataAccessException {
				
		Query query = this.getSession().createSQLQuery("CALL GetOrdersByUsername(:username)")
						.addEntity(Order.class)
						.setParameter("username", username);
		 List<Order> orders = query.list();
		 System.out.println(orders);
		 return orders;
	}

	@Override
	public Order getOrder(int orderId) throws DataAccessException {
		Order order = this.getHibernateTemplate().get(Order.class, orderId);
		System.out.println(order);
		return order;
	}

	@Override
	public void insertOrder(Order order) throws DataAccessException {
		order.setOrderId(this.getSeqNum(ORDER_SEQUENCE_NAME));
		this.getHibernateTemplate().save(order);

	}

	@Override
	public List<Order> getAllOrders() throws DataAccessException {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("FROM Order ");
				List<Order> resultList = query.list();
				System.out.println(resultList);
				return resultList;
			}
		});		
	}
}
