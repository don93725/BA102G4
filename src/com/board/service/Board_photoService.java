package com.board.service;

import com.board.dao.Board_photoDAO;
import com.board.domain.Board_photo;

public class Board_photoService {
	//封裝新增物件

	public boolean add(String bd_msg_no, String photo_no){
	Board_photo board_photo = new Board_photo();
	board_photo.setBd_msg_no(bd_msg_no);
	board_photo.setPhoto_no(photo_no);
	Board_photoDAO dao = new Board_photoDAO();
	boolean result = dao.executeInsert(board_photo);
	return result;
	}
}
