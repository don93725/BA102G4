package com.adapply.conctroller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Course.model.CourseService;
import com.adapply.model.AD_ApplyService;
import com.adapply.model.AD_ApplyVO;
import com.authority.model.AuthorityService;
import com.manager.model.ManagerVO;
import com.members.model.MembersVO;

@WebServlet("/adapply/AD_ApplyCtrl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class AD_ApplyCtrl extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		
		String action = req.getParameter("action");
		

		
		if("addAD".equals(action)){
			
			HttpSession session = req.getSession();
			MembersVO membersVO = (MembersVO)session.getAttribute("user");
			
			System.out.println("暱稱: "+membersVO.getMem_nickname());
			System.out.println("身分: "+membersVO.getMem_rank());

			String rank = membersVO.getMem_rank();
//			if("1".equals(rank)){
//				
//			}else if("2".equals(rank)){
//				
//			}else if("3".equals(rank)){
//				
//			}
//			MembersService membersSvc = new MembersService();
			
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front_end/adapply/addAD.jsp");
			failureView.forward(req, res);
			return;
		}
		
		
		if("insert".equals(action)){
			Collection<Part> parts = req.getParts();
			
			
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			MembersVO membersVO = (MembersVO)session.getAttribute("user");
			
			
			
			try {

				String mem_no = membersVO.getMem_no();
				
				String ad_url = null;
				try {
					ad_url = req.getParameter("ad_url").trim();
					System.out.println(ad_url);
				} catch (Exception e) {
					errorMsgs.add("請選擇廣告類型");
				}
				String ad_name = null;
				try {
					ad_name = req.getParameter("ad_name").trim();
				} catch (Exception e) {
					ad_name = "";
					errorMsgs.add("請輸入廣告名稱");
				}
				java.sql.Date ad_ondate = null;
				try {
					ad_ondate = java.sql.Date.valueOf(req.getParameter("ad_ondate").trim());
				} catch (Exception e) {
					ad_ondate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				java.sql.Date ad_offdate = null;
				try {
					ad_offdate = java.sql.Date.valueOf(req.getParameter("ad_ondate").trim());
				} catch (Exception e) {
					ad_offdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				String ad_ctx = null;
				try {
					ad_ctx = req.getParameter("ad_ctx").trim();
				} catch (Exception e) {
					ad_ctx = "";
					errorMsgs.add("請輸入廣告敘述");
							
				}
				byte[] ad_pt = null;
				
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
						String name = part.getName();
						String filename = getFileNameFromPart(part);
						//這裡只有IE能檢察出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
						String ContentType = part.getContentType();
						long size = part.getSize();
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];
						in.read(buf);
						in.close();
						ad_pt=buf;
					}
				}
				
				AD_ApplyVO ad_ApplyVO = new AD_ApplyVO();
				ad_ApplyVO.setMem_no(mem_no);
				ad_ApplyVO.setAd_name(ad_name);
				ad_ApplyVO.setAd_ondate(ad_ondate);
				ad_ApplyVO.setAd_offdate(ad_offdate);
				ad_ApplyVO.setAd_ctx(ad_ctx);
				ad_ApplyVO.setAd_pt(ad_pt);
				ad_ApplyVO.setAd_url(ad_url);

				//待修正
				///////////////////////////////
//				String ad_url = null;
				java.sql.Date pay_date = ad_ondate;
				ad_ApplyVO.setPay_date(pay_date);
				/////////////////////////////////

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ad_ApplyVO", ad_ApplyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/adapply/addAD.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				AD_ApplyService adSvc = new AD_ApplyService();
				ad_ApplyVO = adSvc.addAD(mem_no, pay_date, ad_name, ad_url, ad_ondate, ad_offdate, ad_ctx, ad_pt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/adapply/setCheckAD.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				String url = "/front_end/adapply/addAD.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
			
		
		}
			
		
		if("OK_AD".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String ad_no = req.getParameter("ad_no");
				
				AD_ApplyService ADSvc = new AD_ApplyService();
				ADSvc.updateADStat(new Integer(1), ad_no);
				String url = "/back_end/adapply/setCheckAD.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				String url = "/back_end/adapply/setCheckAD.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
			
		}
		
		if("NO_AD".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String ad_no = req.getParameter("ad_no");
				AD_ApplyService ADSvc = new AD_ApplyService();
				ADSvc.updateADStat(1, ad_no);
				String url = "/back_end/adapply/setCheckAD.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				String url = "/back_end/adapply/setCheckAD.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
			
		
		}
		
	
	}
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
