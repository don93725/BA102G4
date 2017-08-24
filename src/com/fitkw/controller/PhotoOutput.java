package com.fitkw.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.fitkw.model.FitkwService;
@WebServlet("/g1/PhotoOutput")
public class PhotoOutput extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String fik_no = req.getParameter("fik_no");
		byte[] bytes = null;
		try {
			if(fik_no!=null){
				FitkwService fitkwService = new FitkwService();
				bytes = fitkwService.getPic(fik_no);
			}
			
			
			
			if (bytes!=null) {
					out.write(bytes);
				
				
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/back_end/fitkw/images/nopic.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/back_end/fitkw/images/nopic.jpg");
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
