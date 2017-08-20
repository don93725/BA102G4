package com.comments.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.don.util.Validation;
import com.google.gson.Gson;
import com.members.model.MembersService;
import com.members.model.MembersVO;

/**
 * Servlet implementation class BoardCommentsCtrl
 */
@WebServlet("/all/CommentsCtrl")
public class CommentsCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		String mem_no = req.getParameter("mem_no");
		if(action==null){
			MembersService membersService = new MembersService();
			MembersVO personalCmts =  membersService.getPersonalComments(mem_no);
			req.setAttribute("personalCmts", personalCmts);
			req.getRequestDispatcher("/front_end/search/PersonComments.jsp").forward(req, res);
					return;
					
		}
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		String user_no = user.getMem_no();
		PrintWriter out = res.getWriter();
		HashMap<String,String> map = new HashMap<String,String>();
		if ("addCmtLikes".equals(action)) {
			// 刪除
			String bd_cmt_no = req.getParameter("bd_cmt_no");
			String cmt_type = req.getParameter("cmt_type");
			Board_cmtService dao = new Board_cmtService();
			boolean result = dao.addCmt_likes(bd_cmt_no,user_no,cmt_type);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}
		if ("negativeCmtLikes".equals(action)) {
			// 刪除
			String bd_cmt_no = req.getParameter("bd_cmt_no");
			String cmt_type = req.getParameter("cmt_type");
			Board_cmtService dao = new Board_cmtService();
			boolean result = dao.negativeCmt_likes(bd_cmt_no,user_no,cmt_type);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}
		if ("insert".equals(action)) {
			// 新增
			Board_cmtService dao = new Board_cmtService();
			String cmt_type = req.getParameter("cmt_type");
			String org_no = req.getParameter("org_no");
			String bd_cmt_ctx = req.getParameter("bd_cmt_ctx");
			boolean valid = Validation.checkLengthOne2Thirty(bd_cmt_ctx, "bd_cmt_ctx", map);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			boolean result = dao.add(mem_no, cmt_type, org_no, bd_cmt_ctx);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}
		if("updateReply".equals(action)){
			String bd_cmt_no = req.getParameter("bd_cmt_no");
			String bd_cmt_reply = req.getParameter("bd_cmt_reply");
			Board_cmtService dao = new Board_cmtService();
			boolean result = dao.updateReply(bd_cmt_no, bd_cmt_reply);
			if(!result){
				out.write("{\"fail\":\"fail\"}");				
			}
			return;
		}
		if("deleteReply".equals(action)){
			String bd_cmt_no = req.getParameter("bd_cmt_no");
			Board_cmtService dao = new Board_cmtService();
			boolean result = dao.updateReply(bd_cmt_no, null);
			if(!result){
				out.write("{\"fail\":\"fail\"}");				
			}
			return;
		}
		if (!mem_no.equals(user.getMem_no()) || action == null) {
			// 非會員想做其他操作
			String referer = (String) req.getSession().getAttribute("referer");
			req.getSession().removeAttribute("referer");
			if (referer != null) {
				res.sendRedirect(referer);
			} else {
				res.sendRedirect(req.getContextPath() + "/index.jsp");
			}
			return;
		}
		if ("update".equals(action)) {
			// 修改
			Board_cmtService dao = new Board_cmtService();
			String bd_cmt_no = req.getParameter("bd_cmt_no");
			String bd_cmt_ctx = req.getParameter("bd_cmt_ctx");
			boolean valid = Validation.checkLengthOne2Thirty(bd_cmt_ctx, "bd_cmt_ctx", map);
			System.out.println(bd_cmt_no+" "+bd_cmt_ctx);
			if(!valid){
				Gson gson = new Gson();
				out.write(gson.toJson(map));
				return;
			}
			boolean result = dao.update(bd_cmt_no, bd_cmt_ctx);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}
		if ("delete".equals(action)) {
			// 刪除
			String bd_cmt_no = req.getParameter("bd_cmt_no");
			Board_cmtService dao = new Board_cmtService();
			boolean result = dao.delete(bd_cmt_no);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			} 
			return;
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
