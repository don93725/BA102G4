package com.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.authority.model.AuthorityService;
import com.manager.model.ManagerVO;

@WebFilter("/AutCF")
public class AutCF implements Filter {
	private FilterConfig config;
   
	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		
		HttpSession session =req.getSession();
		ManagerVO userMgr = (ManagerVO)session.getAttribute("userMgr");
		AuthorityService autSvc = new AuthorityService();
		
//		String location = req.getRequestURI();
//		String queryString = req.getQueryString();
//		if(queryString != null) {
//			location = location + "?" + queryString;
//		}
		try {
			String autCheck = autSvc.getOneFun(userMgr.getMgr_no(),"CF").getF_no();
			System.out.println("autCheck: " + autCheck);
			chain.doFilter(req, res);
//			
//			String url =location;
//			req.setAttribute("location", location);
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			return;
		} catch (NullPointerException e) {
			System.out.println("您無此權限進入該頁面");
			res.sendRedirect(req.getContextPath()+"/back_end/no_authority.jsp");
			return;
		}
		
		// pass the request along the filter chain
//		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
