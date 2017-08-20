package com.friends.model;

import java.util.Date;
import java.util.List;

import com.don.util.BasicDAO;

public class FriendsService {
	// 封裝新增物件

	public boolean add(String mem_no, String user_no) {
		Friends friends = new Friends();
		friends.setMem_no(mem_no);
		friends.setFd_no(user_no);
		FriendsDAO dao = new FriendsDAO();
		boolean result = dao.executeInsert(friends);
		return result;
	}
	public boolean delete(String mem_no, String user_no) {
		
		FriendsDAO dao = new FriendsDAO();
		boolean result = dao.executeDelete(mem_no, user_no);
		return result;
	}

	// 建置查詢
	public boolean checkFriendShip(String mem_no, String fd_no) {
		FriendsDAO FriendsDAO = new FriendsDAO();
		return FriendsDAO.checkFriendShip(mem_no, fd_no);
	}
	public List<Friends> getFriendList(String user_no){
		FriendsDAO dao = new FriendsDAO();
		String sql = "select a.mem_no,fd_no,fd_date,b.mem_nickname,c.mem_nickname,b.mem_rank,c.mem_rank from (select * from friends where fd_no="+user_no+" or mem_no="+user_no+
				") a join members b on a.mem_no=b.mem_no join members c on a.fd_no = c.mem_no ";
		return dao.getVOBySQL(sql, null);
	}
	public List<Friends> getPageFriendList(int thisPage,int pageSize, String user_no){
		FriendsDAO dao = new FriendsDAO();

		return dao.pageFriendList(thisPage, pageSize, user_no);
	}
	public int getFriendNum(String user_no){
		String sql = "select count(*) from friends where fd_no="+user_no+" or mem_no="+user_no;
		FriendsDAO dao = new FriendsDAO();
		
		return dao.countBySQL(sql);
	}
	
}
