package com.friends.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.don.util.BasicDAO;
import com.don.util.SQLHelper;

//首句

public class FriendsDAO extends BasicDAO {
	// 建置查詢
	public boolean checkFriendShip(String mem_no, String fd_no){
		String sql = "select * from friends where mem_no="+mem_no+" and fd_no="+fd_no;
		List friends = getVOBySQL(sql, null);
		if(friends.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	public List<Friends> getVOBySQL(String sql, Object[] param) {
		List list = new SQLHelper().executeQuery(sql, param);
		List<Friends> tempList = new ArrayList<Friends>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Friends friends = new Friends();
			if (obj[0] != null) {
				friends.setMem_no((String) obj[0]);
			}
			if (obj[1] != null) {
				friends.setFd_no((String) obj[1]);
			}
			if (obj[2] != null) {
				friends.setFd_date((Date) obj[2]);
			}
			if (obj[3] != null) {
				friends.setMem_nickname(String.valueOf(obj[3]));
			}
			if (obj[4] != null) {
				friends.setFd_nickname(String.valueOf(obj[4]));
			}
			tempList.add(friends);
		}
		return tempList;
	}

	// 算數量

	public int countAll(String mem_no, String fd_no) {
		String sql = "select * from friends mem_no=" + mem_no + " or fd_no=" + fd_no;
		return countBySQL(sql);
	}
	// 建置新增

	public boolean executeInsert(Friends friends) {
		String sql = "insert into friends values(?,?,?)";
		Object[] param = { friends.getMem_no(), friends.getFd_no(), friends.getFd_date() };
		boolean insertResult = new SQLHelper().executeUpdate(sql, param);
		return insertResult;
	}
	// 建置刪除

	public boolean executeDelete(String mem_no, String fd_no) {
		String sql = "delete from friends where mem_no=" + mem_no + " and fd_no=" + fd_no;
		boolean insertResult = new SQLHelper().executeUpdate(sql, null);
		return insertResult;
	}
	// 建置分頁(彈性排序可設條件)

	public List<Friends> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String sql = "select mem_no,fd_no,fd_date from (select mem_no,fd_no,fd_date, rownum rn from (select * from friends";
		if (where != null) {
			sql = sql + " where " + where;
		}
		sql = sql + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Friends> list = getVOBySQL(sql, null);
		return list;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Friends> pageAndRank(int page, int pageSize, String order) {
		List<Friends> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Friends> pageAndRankByPk(int page, int pageSize) {
		List<Friends> list = pageAndRank(page, pageSize, "");
		return list;
	}

}
