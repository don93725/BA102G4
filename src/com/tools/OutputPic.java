package com.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coaches.model.CoachesDAO;
import com.gyms.model.GymsDAO;
import com.students.model.StudentsDAO;
import com.sun.xml.internal.messaging.saaj.util.Base64;

/**
 * Servlet implementation class OutputPic
 */
@WebServlet("/XiangZhiPic")
public class OutputPic extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/JPEG");
		String mem_no = req.getParameter("mem_no");
		String mem_rank = req.getParameter("mem_rank");
		ServletOutputStream out = res.getOutputStream();
		byte[] bytes = null;
		try {
			if (mem_no != null) {
				if("0".equals(mem_rank) ) {
					bytes = new StudentsDAO().getPicByte(mem_no);
				}
				else if("1".equals(mem_rank) ) {
					bytes = new CoachesDAO().getPicByte(mem_no);
				}
				else if("2".equals(mem_rank) ) {
					bytes = new GymsDAO().getPicByte(mem_no);
				}
			}
			if (bytes != null) {
				out.write(bytes);
			} else {
				InputStream in = getServletContext().getResourceAsStream("/style/images/noPic.png");
				bytes = new byte[in.available()];
				in.read(bytes);
				out.write(bytes);
				in.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			InputStream in = getServletContext().getResourceAsStream("/style/images/noPic.png");
			bytes = new byte[in.available()];
			in.read(bytes);
			out.write(bytes);
			in.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
