package com.album.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.album.domain.Albums;
import com.album.service.AlbumsService;
import com.members.model.MembersVO;

/**
 * Servlet implementation class AlbumsShowCtrl
 */
@WebServlet("/album/AlbumsShowCtrl")
public class AlbumsShowCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String mem_no = req.getParameter("mem_no");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		if (mem_no == null) {
			// 沒選擇相簿
			String referer = (String) req.getSession().getAttribute("referer");
			if(referer!=null){
				res.sendRedirect(referer);				
			}else{
				res.sendRedirect(req.getContextPath()+"/index.jsp");
			}	
			return;
		}
		int thisPage = (req.getParameter("thisPage") == null) ? 1 : Integer.valueOf(req.getParameter("thisPage"));
		int pageSize = 12;
		if (user == null || !mem_no.equals(user.getMem_no())) {
			// 非本人
			AlbumsService albumsService = new AlbumsService();
			int allPageCount = albumsService.getPrivateNum(mem_no, pageSize);
			if (thisPage > allPageCount) {
				thisPage = allPageCount;
			}
			List<Albums> albums = albumsService.getPrivateVO(thisPage, pageSize, mem_no);
			Map<String,Integer> photosNum = albumsService.getPhotosNum(mem_no);
			req.setAttribute("albums", albums);
			req.setAttribute("thisPage", thisPage);
			req.setAttribute("allPageCount", allPageCount);
			req.setAttribute("photosNum", photosNum);			
			req.setAttribute("queryStr", "album/AlbumsShowCtrl?mem_no=" + mem_no);
			req.getRequestDispatcher("/front_end/album/Album.jsp").forward(req, res);
			return;
		}
		if (mem_no.equals(user.getMem_no())) {
			// 本人
			AlbumsService albumsService = new AlbumsService();
			int allPageCount = albumsService.getPublicNum(mem_no, pageSize);
			if (thisPage > allPageCount) {
				thisPage = allPageCount;
			}
			Map<String,Integer> photosNum = albumsService.getPhotosNum(mem_no);
			List<Albums> albums = albumsService.getPublicVO(thisPage, pageSize, mem_no);
			req.setAttribute("albums", albums);
			req.setAttribute("thisPage", thisPage);
			req.setAttribute("allPageCount", allPageCount);
			req.setAttribute("photosNum", photosNum);
			req.setAttribute("queryStr", "album/AlbumsShowCtrl?mem_no=" + mem_no);
			req.getRequestDispatcher("/front_end/album/Album.jsp").forward(req, res);
			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
