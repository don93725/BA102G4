package com.place_pic.controller;

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

import com.google.gson.Gson;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;
import com.place_pic.model.Place_PicService;

/**
 * Servlet implementation class place_picServlet
 */
@WebServlet("/Place_picServlet")
public class Place_picServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		Map<String,String> Msgs = new LinkedHashMap<String,String>();
		PrintWriter out = res.getWriter();
		System.out.println("action = " + action);
		if("showPlacePic".equals(action)){
			try {
				String ppp_no = req.getParameter("ppp_no");
				System.out.println("ppp_no= " + ppp_no);
				Place_PicService place_PicService = new Place_PicService();
				List picList = place_PicService.getAllPPic(ppp_no);	
				Gson gSon = new Gson();
				out.print(gSon.toJson(picList));
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
