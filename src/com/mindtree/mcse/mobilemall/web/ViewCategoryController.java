package com.mindtree.mcse.mobilemall.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.mindtree.mcse.mobilemall.domain.Category;
import com.mindtree.mcse.mobilemall.service.CategoryService;
import com.mindtree.mcse.mobilemall.service.ProductService;

@SuppressWarnings({"rawtypes","unchecked"})
public class ViewCategoryController implements Controller {

	private ProductService productService;
	private CategoryService categoryService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		String categoryId = request.getParameter("categoryId");
		if (categoryId != null) {
				
			Category category = this.categoryService.getCategory(categoryId);

			PagedListHolder productList = new PagedListHolder(this.productService.getProductListByCategory(categoryId));
			productList.setPageSize(4);
			request.getSession().setAttribute("ViewProductAction_category", category);
			request.getSession().setAttribute("ViewProductAction_productList", productList);
			model.put("category", category);
			model.put("productList", productList);
		}
		else {
			Category category = (Category) request.getSession().getAttribute("ViewProductAction_category");
			PagedListHolder productList = (PagedListHolder) request.getSession().getAttribute("ViewProductAction_productList");
			if (category == null || productList == null) {
				throw new IllegalStateException("Cannot find pre-loaded category and product list");
			}
			String page = request.getParameter("page");
			if ("next".equals(page)) {
				productList.nextPage();
			}
			else if ("previous".equals(page)) {
				productList.previousPage();
			}
			model.put("category", category);
			model.put("productList", productList);
		}
		return new ModelAndView("Category", model);
	}

}
