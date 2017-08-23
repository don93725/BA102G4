package com.queans.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.queans.model.QueansService;
@WebServlet("/g1/PhotoOutputQ")
public class PhotoOutputQ extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String que_no = req.getParameter("que_no");
		byte[] bytes = null;
		try {
			if(que_no!=null){
				QueansService queansService = new QueansService();
				bytes = queansService.getPic(que_no);
			}
			
			
			
			if (bytes!=null) {
					out.write(bytes);
				
				
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/back_end/queans/images/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/back_end/queans/images/nopic.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void init() throws ServletException {

	}

	public void destroy() {

	}

}
