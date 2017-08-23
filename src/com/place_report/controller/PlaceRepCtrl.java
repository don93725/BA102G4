package com.place_report.controller;

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

import com.authority.model.AuthorityService;
import com.manager.model.ManagerVO;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.message.model.MessageService;
import com.place.model.PlaceService;
import com.place_report.model.PlaceReportService;
import com.place_report.model.PlaceReportVO;


@WebServlet("/placerep/PlaceRepCtrl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PlaceRepCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		String action = req.getParameter("action");
		
		
		
		if("addPR".equals(action)){
//			List<String>errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session = req.getSession();
			MembersVO membersVO = (MembersVO)session.getAttribute("user");
			
			System.out.println("暱稱: "+membersVO.getMem_nickname());
			System.out.println("身分: "+membersVO.getMem_rank());

			
			String p_no = req.getParameter("p_no");
			System.out.println(p_no);
			PlaceReportVO placeReportVO = new PlaceReportVO();
			placeReportVO.setP_no(p_no);
			System.out.println(placeReportVO.getP_no());
			req.setAttribute("placeReportVO", placeReportVO);
			RequestDispatcher successView = req.getRequestDispatcher("/front_end/placerep/placeRep_ctx.jsp");
			successView.forward(req, res);
		}
		if("insert".equals(action)){
			Collection<Part> parts = req.getParts();

			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			MembersVO membersVO = (MembersVO)session.getAttribute("user");

			System.out.println("暱稱: "+membersVO.getMem_nickname());
			System.out.println("身分: "+membersVO.getMem_rank());

			String p_no = req.getParameter("p_no");
//			System.out.println("p_no: "+p_no);

			String mem_no = membersVO.getMem_no();
//			System.out.println("mem_no: "+mem_no);
			
			String pr_ctx = req.getParameter("pr_ctx");
//			System.out.println("pr_ctx: "+pr_ctx);
			String ref_ctx = null;
			
			byte[] pr_pt = null;
			
			for (Part part : parts) {
				if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
					System.out.println("3");
					String name = part.getName();
					String filename = getFileNameFromPart(part);
					//這裡只有IE能檢察出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
					String ContentType = part.getContentType();
					long size = part.getSize();
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];
					in.read(buf);
					in.close();
					System.out.println(buf);
					pr_pt=buf;
				}
			}
			
			try {
				PlaceReportVO placeReportVO = new PlaceReportVO();
				placeReportVO.setP_no(p_no);
				placeReportVO.setMem_no(mem_no);
				placeReportVO.setPr_ctx(pr_ctx);
				placeReportVO.setRef_ctx(ref_ctx);
				placeReportVO.setPr_pt(pr_pt);
				PlaceReportService placeRepSvc = new PlaceReportService();
				placeReportVO = placeRepSvc.addPR(p_no, mem_no, pr_ctx, ref_ctx, pr_pt);
				String url = "/back_end/placerep/CheckPlaceRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/placerep/placeRep_ctx.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		if("Report".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			MessageService messageSvc = new MessageService();
			
				try {
					String pr_no = req.getParameter("pr_no");
					String p_no = req.getParameter("p_no");					
					PlaceReportService PRSvc = new PlaceReportService();
					PRSvc.updatePR(pr_no);
					PRSvc.updatePRNum(p_no);
					
					PlaceService pSvc = new PlaceService();
					String G_acc =  pSvc.getOnePlace(p_no).getG_acc();
					MembersService memSvc = new MembersService();
					String G_no = memSvc.getMemAcc(G_acc).getMem_no();
					messageSvc.add(G_no, "0", "您的場地被檢舉,請近期改善...");
					
					String url = "/back_end/placerep/CheckPlaceRep.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				} catch (Exception e) {
					errorMsgs.add("審核失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/placerep/CheckPlaceRep.jsp");
					failureView.forward(req, res);
				
				}
			
		}
		
		if("NO_Report".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String pr_no = req.getParameter("pr_no");
//				String mr_def = req.getParameter("mr_def");
				PlaceReportService PRSvc = new PlaceReportService();
				PRSvc.updatePR(pr_no);
				String url = "/back_end/placerep/CheckPlaceRep.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("審核失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/placerep/CheckplaceRep.jsp");
				failureView.forward(req, res);
			
			}
		}
		
	}
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
//			System.out.println("header=" + header); // 測試用
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//			System.out.println("filename=" + filename); // 測試用
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}

}
