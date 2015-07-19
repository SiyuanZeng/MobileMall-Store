package com.mindtree.mcse.mobilemall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 */
public class SignonInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String url = request.getServletPath();
		String query = request.getQueryString();
		if (userSession == null) {
			if(url.startsWith("/admin")){
				ModelAndView modelAndView = new ModelAndView("redirect:/admin/signon.do");
				if (query != null) {
					modelAndView.addObject("signonForwardAction", url+"?"+query);
				}
				else {
					modelAndView.addObject("signonForwardAction", url);
				}
				throw new ModelAndViewDefiningException(modelAndView);
			} else {
				
				ModelAndView modelAndView = new ModelAndView("SignonForm");
				if (query != null) {
					modelAndView.addObject("signonForwardAction", url+"?"+query);
				}
				else {
					modelAndView.addObject("signonForwardAction", url);
				}
				throw new ModelAndViewDefiningException(modelAndView);
			}
		}
		else {
			if(userSession.getAccount().isAdmin() && url.startsWith("/shop")){
				ModelAndView modelAndView = new ModelAndView("SignonForm");
				if (query != null) {
					modelAndView.addObject("signonForwardAction", url+"?"+query);
				}
				else {
					modelAndView.addObject("signonForwardAction", url);
				}
				throw new ModelAndViewDefiningException(modelAndView);
			}
			else if(!userSession.getAccount().isAdmin() && url.startsWith("/admin")){
				ModelAndView modelAndView = new ModelAndView("redirect:/admin/signon.do");
				if (query != null) {
					modelAndView.addObject("signonForwardAction", url+"?"+query);
				}
				else {
					modelAndView.addObject("signonForwardAction", url);
				}
				throw new ModelAndViewDefiningException(modelAndView);
			} else {
				return true;
			}
		}
	}

}
