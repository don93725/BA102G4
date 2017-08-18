package com.board.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.board.domain.Likes_record;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;

public class Likes_recordDAO extends BasicDAO {
	// 建置查詢

	public List<Likes_record> getVOBySQL(String sql, Object[] param) {
		List list = new SQLHelper().executeQuery(sql, param);
		List<Likes_record> tempList = new ArrayList<Likes_record>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Likes_record likes_record = new Likes_record();
			if (obj[0] != null) {
				likes_record.setBd_msg_no((String) obj[0]);
			}
			if (obj[1] != null) {
				likes_record.setMem_no((String) obj[1]);
			}
			if (obj[2] != null) {
				likes_record.setIf_click((String) obj[2]);
			}
			tempList.add(likes_record);
		}
		return tempList;
	}
	// 建置查詢單筆

	// 建置新增

	public boolean executeInsert(Likes_record likes_record, Connection conn) {
		SQLHelper helper = new SQLHelper();
		String sql = "insert into likes_record values(?,?,1)";
		Object[] param = { likes_record.getBd_msg_no(), likes_record.getMem_no() };
		String res = helper.executeUpdate(sql, param, null, conn);
		if(res!=null){
			return true;
		}else{
			return false;
		}
	}

	// 建置分頁(彈性排序可設條件)

	public List<Likes_record> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String sql = "select bd_msg_no,mem_no,if_click from (select bd_msg_no,mem_no,if_click, rownum rn from (select * from likes_record";
		if (where != null) {
			sql = sql + " where " + where;
		}
		sql = sql + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Likes_record> list = getVOBySQL(sql, null);
		return list;
	}
	//刪除
	public boolean executeDelete(String bd_msg_no,Connection con) {
		String sql = "delete from likes_record where bd_msg_no="+bd_msg_no;
		String res = new SQLHelper().executeUpdate(sql, null,null,con);
		if(res!=null){
			return true;
		}else{
			return false;			
		}
	}
	// 建置分頁(彈性排序不設條件)

	public List<Likes_record> pageAndRank(int page, int pageSize, String order) {
		List<Likes_record> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Likes_record> pageAndRankByPk(int page, int pageSize) {
		List<Likes_record> list = pageAndRank(page, pageSize, "");
		return list;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String sql = "select " + col + " from likes_record where =?";
		List<Object[]> list = new SQLHelper().executeQuery(sql, param);
		Object[] colData = list.get(0);
		return colData;

	}
}
