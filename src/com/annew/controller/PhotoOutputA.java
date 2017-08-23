package com.annew.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.annew.model.AnnewService;
@WebServlet("/g1/PhotoOutputA")
public class PhotoOutputA extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String ann_no = req.getParameter("ann_no");
		byte[] bytes = null;
		try {
			if(ann_no!=null){
				AnnewService annewService = new AnnewService();
				bytes = annewService.getPic(ann_no);
			}
			
			
			
			if (bytes!=null) {
					out.write(bytes);
				
				
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/back_end/annew/images/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/back_end/annew/images/nopic.jpg");
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
