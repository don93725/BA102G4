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
import com.place_pic.model.Place_PicDAO;
import com.students.model.StudentsDAO;
import com.sun.xml.internal.messaging.saaj.util.Base64;

/**
 * Servlet implementation class OutputPic
 */
@WebServlet("/PublishPic")
public class OutputPic2 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/JPEG");
		String p_pic_no = req.getParameter("p_pic_no");
		ServletOutputStream out = res.getOutputStream();
		byte[] bytes = null;
		try {
			if (p_pic_no != null) {
				bytes = new Place_PicDAO().getPlacePic(p_pic_no);
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
