package com.tools;


import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.manager.model.ManagerVO;
@WebServlet("/tools/DBGifReader")
public class DBGifReader extends HttpServlet {

	private static final String GET_ONE_AD = "select * from ad_apply where ad_no = ?";
	private static final String GET_ONE_MGR = "select * from manager where mgr_no = ?";
	
	
	
	Connection con = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		HttpSession session = req.getSession();
		ManagerVO userMgr=(ManagerVO)session.getAttribute("userMgr");
		
		String from = null;
		String getphoto = null;
		String wherePk = null;
		String ad_no = req.getParameter("ad_no");
		String mgr_no = req.getParameter("mgr_no");
		String pr_no = req.getParameter("pr_no");
		String gym_acc = req.getParameter("gym_acc");
		String coa_acc = req.getParameter("coa_acc");
		
//		if(userMgr.getMgr_no()!=null){
//			from = "manager";
//			wherePk = "mgr_no";
//			getphoto = "mgr_pic";
//			this.sqlTool(from, wherePk, userMgr.getMgr_no(), getphoto, req, res, out);
//		}
		if(mgr_no!=null){			
			from = "manager";
			wherePk = "mgr_no";
			getphoto = "mgr_pic";
			this.sqlTool(from, wherePk, mgr_no, getphoto, req, res, out);
		}
		if(ad_no!=null){
			from ="adapply";
			wherePk = "ad_no";
			getphoto = "ad_pt";
			this.sqlTool(from, wherePk, ad_no, getphoto, req, res, out);
		}
		if(pr_no!=null){
			from = "place_report";
			wherePk = "pr_no";
			getphoto = "pr_pt";
			this.sqlTool(from, wherePk, pr_no, getphoto, req, res, out);
		}
		if(gym_acc!=null){
			from = "gyms";
			wherePk = "gym_acc";
			getphoto = "gym_pic";
			this.sqlTool(from, wherePk, gym_acc, getphoto, req, res, out);
		}
		if(coa_acc!=null){
			from = "coaches";
			wherePk = "coa_acc";
			getphoto = "coa_pic";
			this.sqlTool(from, wherePk, coa_acc, getphoto, req, res, out);
		}
		
		
		
		
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
			con= ds.getConnection();
		
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void sqlTool(String from, String wherePk,String whereValue,String getphoto,HttpServletRequest req,HttpServletResponse res,ServletOutputStream out) {

		String sql = "select * from " + from + " where " + wherePk + " = ?";

		try {

			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, whereValue);
			ResultSet rs = pstmt.executeQuery();

			
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(getphoto));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			try {
				InputStream in = getServletContext().getResourceAsStream("/style/images/noPic.png");
				byte[] bytes = new byte[in.available()];
				in.read(bytes);
				out.write(bytes);
				in.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
			
		}

	}

}
