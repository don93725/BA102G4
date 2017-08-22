package com.coa_gym_apply.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coaches.model.CoachesService;
import com.gyms.model.GymsService;
import com.membersreport.model.MembersReportService;


@WebServlet("/CoaGymApplyCtrl")
public class CoaGymApplyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		
		String action = req.getParameter("action");
		
		
		
		
		if("CoaApply_ok".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String coa_acc = req.getParameter("coa_acc");
				String coa_no = req.getParameter("coa_no");
				CoachesService coaSvc = new CoachesService();
				coaSvc.updateCoaApply(1, coa_acc);
				String url = "/back_end/coa_gym_apply/coa_apply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa_gym_apply/coa_apply.jsp");
				failureView.forward(req, res);
			
			}
			
			
		}
		if("CoaApply_no".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String coa_acc = req.getParameter("coa_acc");
				String coa_no = req.getParameter("coa_no");
				CoachesService coaSvc = new CoachesService();
				coaSvc.updateCoaApply(2, coa_acc);
				String url = "/back_end/coa_gym_apply/coa_apply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa_gym_apply/coa_apply.jsp");
				failureView.forward(req, res);
			
			}
		}
		
		if("GymApply_ok".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String gym_acc = req.getParameter("gym_acc");
				String gym_no = req.getParameter("gym_no");
				GymsService gymSvc = new GymsService();
				gymSvc.updateGymApply(1, gym_acc);
				String url = "/back_end/coa_gym_apply/gym_apply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa_gym_apply/gym_apply.jsp");
				failureView.forward(req, res);
			
			}
			
			
		}
		if("GymApply_no".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String gym_acc = req.getParameter("gym_acc");
				String gym_no = req.getParameter("gym_no");
				GymsService gymSvc = new GymsService();
				gymSvc.updateGymApply(2, gym_acc);
				String url = "/back_end/coa_gym_apply/gym_apply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/coa_gym_apply/gym_apply.jsp");
				failureView.forward(req, res);
			
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
