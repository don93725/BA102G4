package com.comments.model;

import java.util.Date;

import com.members.model.MembersVO;

public class Board_cmtService {
	// 封裝新增物件

	public boolean add( String mem_no, String cmt_type, String org_no, String bd_cmt_ctx) {
		Board_cmt board_cmt = new Board_cmt();
		MembersVO members = new MembersVO();
		members.setMem_no(mem_no);
		board_cmt.setMem_no(members);
		board_cmt.setCmt_type(cmt_type);
		board_cmt.setOrg_no(org_no);
		board_cmt.setBd_cmt_ctx(bd_cmt_ctx);
		Board_cmtDAO dao = new Board_cmtDAO();
		boolean result = dao.executeInsert(board_cmt);
		return result;
	}
	public boolean update(String bd_cmt_no,String bd_cmt_ctx) {
		Board_cmt board_cmt = new Board_cmt();
		board_cmt.setBd_cmt_no(bd_cmt_no);
		board_cmt.setBd_cmt_ctx(bd_cmt_ctx);
		Board_cmtDAO dao = new Board_cmtDAO();
		boolean result = dao.updateByVO(board_cmt);
		return result;
	}
	public boolean delete(String bd_cmt_no) {

		Board_cmtDAO dao = new Board_cmtDAO();
		boolean result = dao.executeDelete(bd_cmt_no);
		return result;
	}
	public boolean addCmt_likes(String bd_cmt_no,String user_no, String cmt_type) {
		Board_cmtDAO dao = new Board_cmtDAO();
		Cmt_likes_record cmt_likes_record = new Cmt_likes_record();
		cmt_likes_record.setCmt_pk(bd_cmt_no);
		cmt_likes_record.setMem_no(user_no);
		cmt_likes_record.setCmt_type(cmt_type);
		boolean result = dao.setBd_likes(bd_cmt_no,cmt_likes_record);
		return result;
	}
	public boolean negativeCmt_likes(String bd_cmt_no,String user_no, String cmt_type) {
		Board_cmtDAO dao = new Board_cmtDAO();
		Cmt_likes_record cmt_likes_record = new Cmt_likes_record();
		cmt_likes_record.setCmt_pk(bd_cmt_no);
		cmt_likes_record.setMem_no(user_no);
		cmt_likes_record.setCmt_type(cmt_type);
		boolean result = dao.negativeBd_likes(bd_cmt_no,cmt_likes_record);
		return result;
	}
	public boolean updateReply(String bd_cmt_no, String bd_cmt_reply ){
		Board_cmtDAO dao = new Board_cmtDAO();
		return dao.updateReply(bd_cmt_no, bd_cmt_reply);
	}
	public boolean deleteReply(String bd_cmt_no){
		Board_cmtDAO dao = new Board_cmtDAO();
		return dao.updateReply(bd_cmt_no, null);
	}
}
