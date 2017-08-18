package com.friends.model;

import java.util.Date;
import java.util.List;

import com.don.util.BasicDAO;

public class FriendsService {
	// 封裝新增物件

	public boolean add(String mem_no, String fd_no, Date fd_date) {
		Friends friends = new Friends();
		friends.setMem_no(mem_no);
		friends.setFd_no(fd_no);
		friends.setFd_date(fd_date);
		FriendsDAO dao = new FriendsDAO();
		boolean result = dao.executeInsert(friends);
		return result;
	}

	// 建置查詢
	public boolean checkFriendShip(String mem_no, String fd_no) {
		FriendsDAO FriendsDAO = new FriendsDAO();
		return FriendsDAO.checkFriendShip(mem_no, fd_no);
	}
	public List<Friends> getFriendList(String user_no){
		FriendsDAO dao = new FriendsDAO();
		String sql = "select a.mem_no,fd_no,fd_date,b.mem_nickname,c.mem_nickname from (select * from friends where fd_no="+user_no+" or mem_no="+user_no+
				") a join members b on a.mem_no=b.mem_no join members c on a.fd_no = c.mem_no ";
		return dao.getVOBySQL(sql, null);
	}
}
