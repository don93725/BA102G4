package com.album.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.album.domain.Albums;
import com.album.domain.Photos;
import com.album.service.AlbumsService;
import com.album.service.PhotosService;
import com.members.model.MembersVO;

/**
 * Servlet implementation class PhotosShowCtrl
 */
@WebServlet("/album/PhotosShowCtrl")
public class PhotosShowCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String mem_no = req.getParameter("mem_no");
		String al_no = req.getParameter("al_no");
		MembersVO user = (MembersVO) req.getSession().getAttribute("user");
		if (mem_no == null || al_no == null) {
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
		int pageSize = 36;
		if (user == null || !mem_no.equals(user.getMem_no())) {
			// 會員看
			AlbumsService albumsService = new AlbumsService();
			if (albumsService.checkStatus(al_no)) {
				PhotosService photosService = new PhotosService();
				int allPageCount = photosService.getPageNum(al_no, pageSize);
				if (thisPage > allPageCount) {
					thisPage = allPageCount;
				}
				List<Photos> photos = photosService.pageAndRank(thisPage, pageSize, al_no);
				Albums album = albumsService.getVO(al_no);
				req.setAttribute("album", album);
				req.setAttribute("photos", photos);
				req.setAttribute("thisPage", thisPage);
				req.setAttribute("allPageCount", allPageCount);
				req.setAttribute("queryStr", "album/PhotosShowCtrl?mem_no=" + mem_no +"&al_no="+al_no);
				req.getRequestDispatcher("/front_end/album/Photos.jsp").forward(req, res);
				return;
			} else {
				// 不符合資格
				String referer = req.getHeader("Referer");
				if (user == null) {
					// 非會員請去登入畫面
					String requestURI = req.getRequestURI();
					req.getSession().setAttribute("referer",referer);
					req.getSession().setAttribute("requestURI", requestURI);
					req.getRequestDispatcher("/LoginCtrl").forward(req, res);
					return;
				}
				if(referer!=null){
					req.getRequestDispatcher(referer).forward(req, res);				
				}else{
					res.sendRedirect(req.getContextPath()+"/index.jsp");
				}	
				
				return;
			}
		}
		if (mem_no.equals(user.getMem_no())) {
			// 本人看
			PhotosService photosService = new PhotosService();
			int allPageCount = photosService.getPageNum(al_no, pageSize);
			if (thisPage > allPageCount) {
				thisPage = allPageCount;
			}
			List<Photos> photos = photosService.pageAndRank(thisPage, pageSize, al_no);
			AlbumsService albumsService = new AlbumsService();
			Albums album = albumsService.getVO(al_no);
			req.setAttribute("album", album);
			req.setAttribute("photos", photos);
			req.setAttribute("thisPage", thisPage);
			req.setAttribute("allPageCount", allPageCount);
			req.setAttribute("queryStr", "album/PhotosShowCtrl?mem_no=" + mem_no +"&al_no="+al_no);
			req.getRequestDispatcher("/front_end/album/Photos.jsp").forward(req, res);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
