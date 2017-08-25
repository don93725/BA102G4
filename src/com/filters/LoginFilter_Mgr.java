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

import com.manager.model.ManagerVO;
import com.members.model.MembersVO;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter_Mgr")
public class LoginFilter_Mgr implements Filter {
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
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		
		ManagerVO userMgr = (ManagerVO)session.getAttribute("userMgr");
		if (userMgr == null) {
			String location = req.getRequestURI();
			String queryString = req.getQueryString();
			if(queryString != null) {
				location = location + "?" + queryString;
			}
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
