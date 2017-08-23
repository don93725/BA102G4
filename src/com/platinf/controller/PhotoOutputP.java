package com.platinf.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.platinf.model.PlatinfService;
@WebServlet("/g1/PhotoOutputP")
public class PhotoOutputP extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String pin_no = req.getParameter("pin_no");
		byte[] bytes = null;
		try {
			if(pin_no!=null){
				PlatinfService platinfService = new PlatinfService();
				bytes = platinfService.getPic(pin_no);
			}
			
			
			
			if (bytes!=null) {
					out.write(bytes);
				
				
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/back_end/platinf/images/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/back_end/platinf/images/nopic.jpg");
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
