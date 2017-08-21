package com.adapply.conctroller;


import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/adapply/DBGifReader")
public class DBGifReader extends HttpServlet {

	private static final String GET_ONE_AD = 
			"select * from ad_apply where ad_no = ?";
	
	
	Connection con = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String ad_no = req.getParameter("ad_no");
		try {
			
			PreparedStatement pstmt = null;
			
			pstmt = con.prepareStatement(GET_ONE_AD);
			
			pstmt.setString(1, ad_no);
			
			ResultSet rs= pstmt.executeQuery();
		
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ad_pt"));
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
			System.out.println(e);
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
