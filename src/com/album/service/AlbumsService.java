package com.album.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.album.dao.AlbumsDAO;
import com.album.dao.PhotosDAO;
import com.album.domain.Albums;
import com.don.inteface.DAOInterface;
import com.don.inteface.ServiceIntface;
import com.forum.domain.Article_comments;
import com.members.model.MembersVO;

public class AlbumsService {
	// 封裝新增物件

	public boolean add(String mem_no, String al_name, String al_prvt) {
		Albums albums = new Albums();
		MembersVO members = new MembersVO();
		members.setMem_no(mem_no);
		albums.setMem_no(members);
		albums.setAl_name(al_name);
		albums.setAl_prvt(al_prvt);
		DAOInterface dao = new AlbumsDAO();
		boolean result = dao.executeInsert(albums);
		return result;
	}

	public boolean update(String al_no, String al_name, String al_prvt) {
		Albums albums = new Albums();
		albums.setAl_no(al_no);
		albums.setAl_name(al_name);
		albums.setAl_prvt(al_prvt);
		DAOInterface dao = new AlbumsDAO();
		boolean result = dao.updateByVO(albums);
		return result;
	}

	public boolean delete(String[] al_no) {
		AlbumsDAO dao = new AlbumsDAO();
		boolean result = dao.executeDelete(al_no);
		return result;
	}

	public List<Albums> getPublicVO(int page, int pageSize, String mem_no) {
		AlbumsDAO albumsDAO = new AlbumsDAO();
		String where = "mem_no=" + mem_no;
		String order = "al_board,al_date desc";
		Object[] param = { mem_no };
		List<Albums> albums = albumsDAO.pageAndRank(page, pageSize, order, where);
		return albums;
	}
	public Albums getVO(String al_no) {
		AlbumsDAO albumsDAO = new AlbumsDAO();		
		Albums albums = albumsDAO.getVOByPK(al_no);
		return albums;
	}

	public List<Albums> getPrivateVO(int page, int pageSize, String mem_no) {
		AlbumsDAO albumsDAO = new AlbumsDAO();
		String where = "mem_no=" + mem_no + " and al_prvt=0 ";
		String order = "al_board,al_date desc";
		Object[] param = { mem_no };
		List<Albums> albums = albumsDAO.pageAndRank(page, pageSize, order, where);
		return albums;
	}

	public int getPublicNum(String mem_no, int pageSize) {
		AlbumsDAO albumsDAO = new AlbumsDAO();
		String sql = "select count(*) from albums where mem_no=" + mem_no;
		int num = albumsDAO.countBySQL(sql);
		return (num - 1) / pageSize + 1;
	}

	public int getPrivateNum(String mem_no, int pageSize) {
		AlbumsDAO albumsDAO = new AlbumsDAO();
		String sql = "select count(*) from albums where mem_no=" + mem_no + " and al_prvt=0";
		int num = albumsDAO.countBySQL(sql);
		return (num - 1) / pageSize + 1;
	}
	public int getAlbumNum(String mem_no) {
		AlbumsDAO albumsDAO = new AlbumsDAO();
		String sql = "select count(*) from albums where mem_no=" + mem_no + " and al_prvt=0 or al_prvt=1";
		int num = albumsDAO.countBySQL(sql);
		return num;
	}
	public int getAlbumNumForOther(String mem_no) {
		AlbumsDAO albumsDAO = new AlbumsDAO();
		String sql = "select count(*) from albums where mem_no=" + mem_no + " and al_prvt=0";
		int num = albumsDAO.countBySQL(sql);
		return num;
	}

	// 確認資格
	public boolean checkStatus(String al_no) {
		AlbumsDAO albumsDAO = new AlbumsDAO();
		Object[] param = { al_no };
		String al_prvt = String.valueOf(albumsDAO.getCol("al_prvt", param)[0]);
		if ("0".equals(al_prvt)) {
			return true;
		}
		return false;
	}

	public Map<String, Integer> getPhotosNum(String mem_no) {
		PhotosDAO getPhotosNum = new PhotosDAO();
		return getPhotosNum.getPhotosNum(mem_no);

	}

}
