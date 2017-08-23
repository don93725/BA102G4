package com.annew.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.annew.model.AnnewService;
import com.annew.model.AnnewVO;

@WebServlet("/AnnewShowCtrl")
public class AnnewShowCtrl extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String ann_no = req.getParameter("ann_no");
		String ann_tilte = req.getParameter("ann_tilte");
		String ann_date = req.getParameter("ann_date");
		String queryString = req.getQueryString();
		if(queryString==null){
			AnnewService annewService = new AnnewService();
			List<AnnewVO> annewList = annewService.getAll();
			req.setAttribute("annewList", annewList);
			req.getRequestDispatcher("/front_end/annew/annewList.jsp").forward(req, res);		
			return;
		}
		if(ann_no!=null){
			AnnewService annewService = new AnnewService();
			AnnewVO annewVo = annewVo = annewService.getOneAnnew(ann_no);
			req.setAttribute("annewVo", annewVo);
			req.getRequestDispatcher("/front_end/annew/annewDetail.jsp").forward(req, res);		
			return;
		}
		if(ann_tilte!=null){
			AnnewService annewService = new AnnewService();
			List<AnnewVO> annewList = annewService.getPartByTitle(ann_tilte);
			req.setAttribute("annewList", annewList);
			req.getRequestDispatcher("/front_end/annew/annewList.jsp").forward(req, res);		
			return;
		}
		if(ann_date!=null){
			AnnewService annewService = new AnnewService();
			List<AnnewVO> annewList = annewService.getPartByDate(ann_date);
			req.setAttribute("annewList", annewList);
			req.getRequestDispatcher("/front_end/annew/annewList.jsp").forward(req, res);		
			return;
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
