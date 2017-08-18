package com.board.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

import com.album.domain.Photos;
import com.board.dao.Message_boardDAO;
import com.board.domain.Message_board;
import com.don.util.ResizeImage;
import com.don.util.TransData;
import com.friends.model.FriendsDAO;
import com.members.model.MembersVO;

public class Message_boardService {
	// 封裝新增物件

	public boolean add(String mem_no, String bd_type, String bd_msg_ctx, String bd_prvt, Collection<Part> parts) {
		Message_board message_board = new Message_board();
		MembersVO members = new MembersVO();
		members.setMem_no(mem_no);
		message_board.setMem_no(members);
		message_board.setBd_type(bd_type);
		message_board.setBd_msg_ctx(bd_msg_ctx);
		message_board.setBd_prvt(bd_prvt);
		List<Photos> photos = new ArrayList<Photos>();
		try {
			for (Part part : parts) {
				Photos photo = new Photos();
				
				byte[] b = null;
				byte[] sb = null;
				String header = part.getHeader("Content-Disposition");
				if (header.startsWith("form-data; name=\"image\";")) {
					b = TransData.transBlob(part);
					if (b.length > 150000) {
						b = ResizeImage.resizeImageAsJPG(b, 800);
					}
					sb = ResizeImage.resizeImageAsJPG(b, 200);
					photo.setPhoto(b);
					photo.setSphoto(b);
					photos.add(photo);
				}
				if (header.startsWith("form-data; name=\"film\";")) {
					b = TransData.transBlob(part);
					message_board.setBd_film(b);
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		Message_boardDAO dao = new Message_boardDAO();
		boolean result = dao.executeInsert(message_board, photos);
		return result;
	}
	public boolean addRefBoard(String mem_no, String bd_type, String bd_msg_ctx, String bd_prvt, String bd_ref_ctx) {
		Message_board message_board = new Message_board();
		MembersVO members = new MembersVO();
		members.setMem_no(mem_no);
		message_board.setMem_no(members);
		message_board.setBd_type(bd_type);
		message_board.setBd_msg_ctx(bd_msg_ctx);
		message_board.setBd_prvt(bd_prvt);
		message_board.setBd_ref_ctx(bd_ref_ctx);		
		Message_boardDAO dao = new Message_boardDAO();
		boolean result = dao.executeInsert(message_board);
		return result;
	}
	public boolean executeDelete(String bd_msg_no){
		Message_boardDAO dao = new Message_boardDAO();
		boolean result = dao.executeDelete(bd_msg_no);
		return result;
	}
	public boolean setBd_prvt(String bd_msg_no, String bd_prvt){
		Message_boardDAO dao = new Message_boardDAO();
		boolean result = dao.setPrvt(bd_msg_no,bd_prvt);
		return result;
	}
	public boolean setBd_likes(String bd_msg_no, String mem_no){
		Message_boardDAO dao = new Message_boardDAO();
		boolean result = dao.setBd_likes(bd_msg_no, mem_no);
		return result;
	}
	public boolean updateByVO(String bd_msg_no,String mem_no ,String delStat, String bd_msg_ctx,  Collection<Part> parts, String[] delPhoto_no) {
		Message_board message_board = new Message_board();
		message_board.setBd_msg_no(bd_msg_no);
		MembersVO members = new MembersVO();
		members.setMem_no(mem_no);
		message_board.setMem_no(members);
		message_board.setBd_msg_ctx(bd_msg_ctx);
		List<Photos> photos = new ArrayList<Photos>();		
		try {
			for (Part part : parts) {
				
				byte[] b = null;
				byte[] sb = null;
				String header = part.getHeader("Content-Disposition");
				if (header.startsWith("form-data; name=\"image\";")) {
					Photos photo = new Photos();
					b = TransData.transBlob(part);
					if (b.length > 150000) {
						b = ResizeImage.resizeImageAsJPG(b, 800);
					}
					sb = ResizeImage.resizeImageAsJPG(b, 200);
					photo.setPhoto(b);
					photo.setSphoto(b);
					photos.add(photo);
				}
				if (header.startsWith("form-data; name=\"film\";")) {
					b = TransData.transBlob(part);
					message_board.setBd_film(b);
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		Message_boardDAO dao = new Message_boardDAO();
		if("5".equals(delStat)){
			return dao.updateByVO(message_board);
		}
		return dao.updateByVO(message_board, photos, delPhoto_no,delStat);
	}
		

	public List<Message_board> getPageAndRank(int thisPage, int pageSize, String mem_no, String condition) {
		Message_boardDAO dao = new Message_boardDAO();
		String where = "mem_no=" + mem_no + " and (" + condition + ")";
		String order = "bd_msg_time desc";

		List<Message_board> message_board = dao.pageAndRank(thisPage, pageSize, order, where);
		return message_board;
	}
	public int getBoardNum(String mem_no, String condition, int pageSize) {
		Message_boardDAO dao = new Message_boardDAO();
		String sql = "select count(*) from message_board where mem_no=" + mem_no + " and (" + condition + ")";
		int num = dao.countBySQL(sql);

		return (num - 1) / pageSize + 1;
	}
	//用來看好友動態
	public List<Message_board> getFriendsPageAndRank(int thisPage, int pageSize, String user_no) {
		Message_boardDAO dao = new Message_boardDAO();
		String where = "(mem_no=" + user_no + " and (bd_prvt=0 or bd_prvt=1 or bd_prvt=2)) or ((mem_no in (select fd_no from friends where mem_no=" + user_no + " ) or mem_no in (select mem_no from friends where fd_no=" + user_no + " ) )and (bd_prvt=0 or bd_prvt=1))";
		String order = "bd_msg_time desc";
		
		List<Message_board> message_board = dao.pageAndRank(thisPage, pageSize, order, where);
		return message_board;
	}
	public int getFriendsBoardNum(String user_no, int pageSize) {
		Message_boardDAO dao = new Message_boardDAO();
		
		String sql = "select count(*) from message_board where (mem_no=" + user_no + " and (bd_prvt=0 or bd_prvt=1 or bd_prvt=2)) or ((mem_no in (select fd_no from friends where mem_no=" + user_no + " ) or mem_no in (select mem_no from friends where fd_no=" + user_no + " ) )and (bd_prvt=0 or bd_prvt=1))";
		int num = dao.countBySQL(sql);
		System.out.println("num="+num);
		return (num - 1) / pageSize + 1;
	}
	
	public List<Message_board> getSingleBoard(int thisPage, int pageSize, String bd_msg_no, String condition ) {
		Message_boardDAO dao = new Message_boardDAO();
		String where = "bd_msg_no=" + bd_msg_no + " and (" + condition + ")";
		String order = "bd_msg_time desc";
		
		List<Message_board> message_board = dao.pageAndRank(thisPage, pageSize, order, where);
		return message_board;
	}
}
