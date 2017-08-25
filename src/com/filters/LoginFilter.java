package com.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
	private FilterConfig config;
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		MembersVO user = (MembersVO)session.getAttribute("user");
		if (user == null) {
			String location = req.getRequestURI();
			String queryString = req.getQueryString();
			if(queryString != null) {
				location = location + "?" + queryString;
			}
			System.out.println("location="+location);
//			session.setAttribute("location", req.getRequestURI());
//			res.sendRedirect(req.getContextPath() + "/login.html");
			session.setAttribute("location", location);
			session.setAttribute("referer", req.getHeader("Referer"));
			res.sendRedirect(req.getContextPath() + "/front_end/login/login.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) {
		this.config = config;
		
	}

}
