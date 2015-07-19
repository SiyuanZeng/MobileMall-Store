package com.mindtree.mcse.mobilemall.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mindtree.mcse.mobilemall.dao.ProductDao;
import com.mindtree.mcse.mobilemall.domain.Product;

@SuppressWarnings({"rawtypes","unchecked"})
public class ProductDaoHibernateImpl extends HibernateDaoSupport implements ProductDao {

	@Override
	public List searchProductList(final String keyword) throws DataAccessException {

		if (keyword == null || keyword == "" ) {
			return new ArrayList();
		}

		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) throws HibernateException {
				String queryString = "select distinct product from Product product where product.name like ? OR product.categoryId like ? order by product.productId";
//				StringBuffer sb = new StringBuffer(100);
//				sb.append("select distinct product ");
//				sb.append("from Product product ");
//				sb.append("where ");
//				sb.append("product.name like ");
//				sb.append("'%").append(keyword).append("%' ");
//				sb.append("OR ");
//
//				sb.append("product.categoryId like ");
//				sb.append("'%").append(keyword).append("%' ");
//
//				sb.append("order by product.productId");
				Query query = session.createQuery(queryString);
				query.setString(0, "%"+keyword+"%");
				query.setString(1, "%"+keyword+"%");
				return query.list();
			}
		});
	}

	@Override
	public List getProductListByCategory(final String categoryId) throws DataAccessException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
			
				Query query = session.createQuery("SELECT prod FROM Product prod WHERE prod.categoryId = :categoryId ");
				query.setParameter("categoryId", categoryId);
				List<Product> resultList = query.list();
				System.out.println(resultList);
				return resultList;
				
			}
		});
	}

	@Override
	public Product getProduct(String productId) throws DataAccessException {
		Product prod = (Product) this.getHibernateTemplate().get(Product.class,productId);
		System.out.println(prod);
		return prod;
	}

}
