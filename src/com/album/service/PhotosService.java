package com.album.service;

import com.album.domain.Photos;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.album.dao.AlbumsDAO;
import com.album.dao.PhotosDAO;
import com.don.util.ResizeImage;
import com.don.util.TransData;

public class PhotosService {

	// 封裝新增物件

	public boolean add(Collection<Part> parts, String[] photo_desc, String al_no) throws UnsupportedEncodingException {
		List<Photos> list = new ArrayList<Photos>();

		int num = 0;
		for (Part p : parts) {
			String content = p.getHeader("Content-Disposition");
			if (content.startsWith("form-data; name=\"image\"")) {
				Photos photos = new Photos();
				photos.setAl_no(al_no);
				photos.setPhoto_desc(photo_desc[num++]);
				byte[] b = null;
				byte[] sb = null;

				try {
					b = TransData.transBlob(p);
					if (b.length > 150000) {
						b = ResizeImage.resizeImageAsJPG(b, 800);
					}
					sb = ResizeImage.resizeImageAsJPG(b, 200);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				photos.setPhoto(b);
				photos.setSphoto(sb);
				list.add(photos);
			}
		}
		PhotosDAO dao = new PhotosDAO();
		boolean result = dao.executeInsert(list);
		return result;

	}	
	public boolean update(String photo_no, String photo_desc){
		PhotosDAO dao = new PhotosDAO();
		Photos photos = new Photos();
		photos.setPhoto_no(photo_no);
		photos.setPhoto_desc(photo_desc);
		boolean result = dao.updateByVO(photos);
		return result;
	}
	public boolean delete(String[] photo_no){
		PhotosDAO dao = new PhotosDAO();
		boolean result = dao.executeDelete(photo_no);
		return result;
	}
	public int getPageNum(String al_no,int pageSize){
		PhotosDAO dao = new PhotosDAO();
		String sql = "select count(*) from photos where al_no="+al_no;
		int num = dao.countBySQL(sql);
		return (num-1)/pageSize+1;
	}
	public List<Photos> pageAndRank(int page, int pageSize, String al_no){
		PhotosDAO dao = new PhotosDAO();
		String order = "ul_date desc";
		String where = "al_no="+al_no;
		AlbumsDAO albumDAO = new AlbumsDAO();
		albumDAO.updateAlbumViews(al_no);
		List<Photos> photos = dao.pageAndRank(page, pageSize, order, where);
		return photos;
	}



	

}
