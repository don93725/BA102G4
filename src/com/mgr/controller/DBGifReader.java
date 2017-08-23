package com.mgr.controller;


import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/mgr/DBGifReader")
public class DBGifReader extends HttpServlet {

	private static final String GET_ONE_MGR = 
			"select mgr_pic from manager where mgr_no = ?";
	
	
	Connection con = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String mgr_no = req.getParameter("mgr_no");
		byte[] bytes = null;
		try {
			
			PreparedStatement pstmt = null;
			
			pstmt = con.prepareStatement(GET_ONE_MGR);
			
			pstmt.setString(1, mgr_no);
			
			ResultSet rs= pstmt.executeQuery();
		
			if (rs.next()) {
				bytes = rs.getBytes("mgr_pic");
				System.out.println(bytes);
				if(bytes!=null){
					out.write(bytes);					
				}else{
					InputStream in = getServletContext().getResourceAsStream("/style/images/noPic.png");
					bytes = new byte[in.available()];
					in.read(bytes);
					out.write(bytes);
					in.close();					
				}
			} else {
				InputStream in = getServletContext().getResourceAsStream("/style/images/noPic.png");
				bytes = new byte[in.available()];
				in.read(bytes);
				out.write(bytes);
				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/style/images/noPic.png");
			bytes = new byte[in.available()];
			in.read(bytes);
			out.write(bytes);
			in.close();
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

}
