package com.album.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.album.domain.Photos;
import com.album.service.PhotosService;
import com.don.util.ResizeImage;
import com.don.util.TransData;
import com.forum.dao.Article_commentsDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.members.model.MembersVO;

/**
 * Servlet implementation class PhotosActionCtrl
 */
@WebServlet("/album/PhotosActionCtrl")
@MultipartConfig(fileSizeThreshold = 100, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 100 * 1024 * 1024)
public class PhotosActionCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		String mem_no = req.getParameter("mem_no");
		String al_no = req.getParameter("al_no");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		HashMap<String,String> map = new HashMap<String,String>();
		if (action == null || !user.getMem_no().equals(mem_no)) {
			String referer = (String) req.getSession().getAttribute("referer");
			req.getSession().removeAttribute("referer");
			if(referer!=null){
				req.getRequestDispatcher(referer).forward(req, res);	
				
			}else{
				res.sendRedirect(req.getContextPath()+"/index.jsp");
			}	
			return;
		}


		if ("insert".equals(action)) {
			// 新增
			String[] names = req.getParameterValues("photo_desc");
			Collection<Part> parts = req.getParts();
			PhotosService photosService = new PhotosService();
			boolean result = photosService.add(parts, names, al_no);
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			}
			return;
		}
		if ("update".equals(action)) {	
			// 修改			
			String photo_desc = req.getParameter("photo_desc");
			String photo_no = req.getParameter("photo_no");
			PhotosService photosService = new PhotosService();
			System.out.println(photo_no+ photo_desc);
			boolean result = photosService.update(photo_no, photo_desc);		
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			}
			return;

		}
		if ("delete".equals(action)) {
			// 刪除
			String[] photo_no = req.getParameterValues("photo_no");
			PhotosService photosService = new PhotosService();
			boolean result = photosService.delete(photo_no);		
			if (!result) {
				out.write("{\"fail\":\"fail\"}");
			}
			return;
		}


//		Photos photos = gson.fromJson(jsonIn.toString(),Photos.class);
//		String al_no = req.getParameter("al_no");
//		PhotosService photosService = new PhotosService();
//		boolean result = photosService.add(photos, al_no);
		
//		byte[] bytes = TransData.transBlob(part);
//		System.out.println(bytes.length);
			

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
