package com.place.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.place.model.PlaceService;
import com.place.model.PlaceVO;

/**
 * Servlet implementation class PlaceInfoServlett
 */
@WebServlet("/PlaceInfoServlet")
public class PlaceInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("(P_info)action= " + action);
		PrintWriter out = res.getWriter();
		
		if("lookPlaceInfoByP".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				String p_no = req.getParameter("p_no");
				System.out.println("p_no= " + p_no);
				PlaceService placeSV = new PlaceService();
				PlaceVO placeVO = placeSV.getPlaceInfoByP_no(p_no);
				System.out.println("placeVO= "+placeVO);
				req.setAttribute("placeVO", placeVO);
				String url = "/front_end/place_info/place_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
