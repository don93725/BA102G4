package com.fitkw.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitkw.model.FitkwService;
import com.fitkw.model.FitkwVO;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FitkwService fitkwService = new FitkwService();
		FitkwVO FitkwVO = fitkwService.getOneFitkw("2");
		//FitkwVO FitkwVO= fitkwService.addFitkw("kk", "yy");		
		//fitkwService.deleteFitkw("1");
		System.out.println(FitkwVO.getFik_no());
		System.out.println(FitkwVO.getFik_ctx());
		System.out.println(FitkwVO.getFik_title());
		System.out.println(FitkwVO.getFik_type());
		System.out.println(FitkwVO.getFik_photo());
		System.out.println(FitkwVO.getUpd_date());
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
