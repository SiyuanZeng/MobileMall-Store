package com.mindtree.mcse.mobilemall.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.mindtree.mcse.mobilemall.domain.Product;
import com.mindtree.mcse.mobilemall.service.ItemService;
import com.mindtree.mcse.mobilemall.service.ProductService;

@SuppressWarnings({"rawtypes","unchecked"})
public class ViewProductController implements Controller {

	private ItemService itemService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setItemService(ItemService is){
		this.itemService = is;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		String productId = request.getParameter("productId");
		if (productId != null) {
			PagedListHolder itemList = new PagedListHolder(this.itemService.getItemListByProduct(productId));
			itemList.setPageSize(4);
			Product product = this.productService.getProduct(productId);
			request.getSession().setAttribute("ViewProductAction_itemList", itemList);
			request.getSession().setAttribute("ViewProductAction_product", product);
			model.put("itemList", itemList);
			model.put("product", product);
		}
		else {
			PagedListHolder itemList = (PagedListHolder) request.getSession().getAttribute("ViewProductAction_itemList");
			Product product = (Product) request.getSession().getAttribute("ViewProductAction_product");
			String page = request.getParameter("page");
			if ("next".equals(page)) {
				itemList.nextPage();
			}
			else if ("previous".equals(page)) {
				itemList.previousPage();
			}
			model.put("itemList", itemList);
			model.put("product", product);
		}
		return new ModelAndView("Product", model);
	}

}
