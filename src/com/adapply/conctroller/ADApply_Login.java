package com.adapply.conctroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/adapply/Login") 
public class ADApply_Login extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MembersVO members = new MembersVO();
		  
		members.setMem_no("7");
		members.setMem_acc("COA1");
		members.setMem_nickname("COA1_C1");
		members.setMem_rank("1");
		members.setMr_num(1);
		
//		members.setMem_no("9");
//		members.setMem_acc("GYM1");
//		members.setMem_nickname("GYM1_G1");
//		members.setMem_rank("2");
//		members.setMr_num(0);

		session.setAttribute("user",members);
		res.setContentType("text/html; charset=utf-8");
		System.out.println(((MembersVO)session.getAttribute("user")).getMem_nickname()+"登入了");
		res.sendRedirect(req.getContextPath()+"/front_end/adapply/AD_ApplyBtn.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
