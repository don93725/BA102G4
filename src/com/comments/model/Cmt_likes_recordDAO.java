package com.comments.model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;

public class Cmt_likes_recordDAO extends BasicDAO {
	// 建置查詢

	public List<Cmt_likes_record> getVOBySQL(String sql, Object[] param) {
		List list = new SQLHelper().executeQuery(sql, param);
		List<Cmt_likes_record> tempList = new ArrayList<Cmt_likes_record>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Cmt_likes_record cmt_likes_record = new Cmt_likes_record();
			if (obj[0] != null) {
				cmt_likes_record.setCmt_rcd_no((String) obj[0]);
			}
			if (obj[1] != null) {
				cmt_likes_record.setMem_no((String) obj[1]);
			}
			if (obj[2] != null) {
				cmt_likes_record.setCmt_type((String) obj[2]);
			}
			if (obj[3] != null) {
				cmt_likes_record.setCmt_pk((String) obj[3]);
			}
			if (obj[4] != null) {
				cmt_likes_record.setIf_click((String) obj[4]);
			}
			tempList.add(cmt_likes_record);
		}
		return tempList;
	}
	// 建置查詢單筆

	public Cmt_likes_record getVOByPK(String cmt_rcd_no) {
		String sql = "Select * from cmt_likes_record where cmt_rcd_no=?";
		Object[] param = { cmt_rcd_no };
		List<Cmt_likes_record> list = getVOBySQL(sql, param);
		Cmt_likes_record cmt_likes_record = list.get(0);
		return cmt_likes_record;
	}
	// 建置查詢全部

	public List<Cmt_likes_record> getAll() {
		String sql = "select * from cmt_likes_record";
		List<Cmt_likes_record> list = getVOBySQL(sql, null);
		return list;
	}
	// 算數量

	public int countAll() {
		String sql = "select * from cmt_likes_record";
		return countBySQL(sql);
	}
	// 建置修改

	public boolean updateByVO(Cmt_likes_record cmt_likes_record) {
		String sql = "update cmt_likes_record set mem_no=?,cmt_type=?,cmt_pk=?,if_click=? where cmt_rcd_no=?";
		Object[] param = { cmt_likes_record.getCmt_rcd_no(), cmt_likes_record.getMem_no(),
				cmt_likes_record.getCmt_type(), cmt_likes_record.getCmt_pk(), cmt_likes_record.getIf_click() };
		boolean updateResult = new SQLHelper().executeUpdate(sql, param);
		return updateResult;
	}
	// 建置新增

	public boolean executeInsert(Cmt_likes_record cmt_likes_record, Connection con) {
		SQLHelper helper = new SQLHelper();
		String sql = "insert into cmt_likes_record values(cmt_likes_record_pk_seq.nextval,?,?,?,1)";
		Object[] param = { cmt_likes_record.getMem_no(), cmt_likes_record.getCmt_type(), cmt_likes_record.getCmt_pk() };
		String res = helper.executeUpdate(sql, param, null, con);
		if(res!=null){
			return true;
		}else{
			return false;
		}
	}
	// 建置刪除

	public boolean executeDelete(Cmt_likes_record cmt_likes_record,Connection conn) {
		SQLHelper helper = new SQLHelper();
		String sql = "delete from cmt_likes_record where cmt_type="+cmt_likes_record.getCmt_type()+
				" and cmt_pk="+cmt_likes_record.getCmt_pk()+" and mem_no="+cmt_likes_record.getMem_no();
		String deleteResult = helper.executeUpdate(sql, null, null, conn);
		if( deleteResult != null){
			return true;
		}else{
			return false;
		}
	}
	// 建置分頁(彈性排序可設條件)

	public List<Cmt_likes_record> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String sql = "select cmt_rcd_no,mem_no,cmt_type,cmt_pk,if_click from (select cmt_rcd_no,mem_no,cmt_type,cmt_pk,if_click, rownum rn from (select * from cmt_likes_record";
		if (where != null) {
			sql = sql + " where " + where;
		}
		sql = sql + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Cmt_likes_record> list = getVOBySQL(sql, null);
		return list;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Cmt_likes_record> pageAndRank(int page, int pageSize, String order) {
		List<Cmt_likes_record> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Cmt_likes_record> pageAndRankByPk(int page, int pageSize) {
		List<Cmt_likes_record> list = pageAndRank(page, pageSize, " cmt_rcd_no");
		return list;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String sql = "select " + col + " from cmt_likes_record where cmt_rcd_no=?";
		List<Object[]> list = new SQLHelper().executeQuery(sql, param);
		Object[] colData = list.get(0);
		return colData;
	}
}
