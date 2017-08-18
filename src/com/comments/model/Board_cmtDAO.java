package com.comments.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;
import com.members.model.MembersVO;

public class Board_cmtDAO extends BasicDAO implements DAOInterface<Board_cmt> {
	// 建置查詢

	public List<Board_cmt> getVOBySQL(String sql, Object[] param) {
		List list = new SQLHelper().executeQuery(sql, param);
		List<Board_cmt> tempList = new ArrayList<Board_cmt>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Board_cmt board_cmt = new Board_cmt();
			if (obj[0] != null) {
				board_cmt.setBd_cmt_no((String) obj[0]);
			}
			if (obj[1] != null) {
				MembersVO members = new MembersVO();
				members.setMem_no(String.valueOf( obj[1]));
				members.setMem_nickname(String.valueOf( obj[7]));
				members.setMem_rank(String.valueOf( obj[8]));
				board_cmt.setMem_no(members);
			}
			if (obj[2] != null) {
				board_cmt.setCmt_type((String) obj[2]);
			}
			if (obj[3] != null) {
				board_cmt.setOrg_no((String) obj[3]);
			}
			if (obj[4] != null) {
				board_cmt.setBd_cmt_ctx((String) obj[4]);
			}
			if (obj[5] != null) {
				board_cmt.setCmt_likes(Integer.parseInt(obj[5].toString()));
			}
			if (obj[6] != null) {
				board_cmt.setBd_cmt_time((Date) obj[6]);
			}
			if (obj[9] != null) {
				
				board_cmt.setIfClick(true);
			}
			tempList.add(board_cmt);
		}
		return tempList;
	}
	// 建置查詢單筆

	public Board_cmt getVOByPK(String bd_cmt_no) {
		String sql = "Select * from board_cmt where bd_cmt_no=?";
		Object[] param = { bd_cmt_no };
		List<Board_cmt> list = getVOBySQL(sql, param);
		Board_cmt board_cmt = list.get(0);
		return board_cmt;
	}
	// 建置查詢全部

	public List<Board_cmt> getAll() {
		String sql = "select * from board_cmt";
		List<Board_cmt> list = getVOBySQL(sql, null);
		return list;
	}
	// 算數量

	public int countAll() {
		String sql = "select * from board_cmt";
		return countBySQL(sql);
	}
	// 建置修改

	public boolean updateByVO(Board_cmt board_cmt) {
		String sql = "update board_cmt set bd_cmt_ctx=? where bd_cmt_no=?";
		Object[] param = { board_cmt.getBd_cmt_ctx(), board_cmt.getBd_cmt_no() };
		boolean updateResult = new SQLHelper().executeUpdate(sql, param);
		return updateResult;
	}
	// 建置新增

	public boolean executeInsert(Board_cmt board_cmt) {
		String sql = "insert into board_cmt values(board_cmt_pk_seq.nextval,?,?,?,?,default,default)";
		Object[] param = { board_cmt.getMem_no().getMem_no(), board_cmt.getCmt_type(), board_cmt.getOrg_no(),
				board_cmt.getBd_cmt_ctx() };
		boolean insertResult = new SQLHelper().executeUpdate(sql, param);
		return insertResult;
	}
	// 建置刪除

	public boolean executeDelete(String bd_cmt_no) {
		String sql = "delete from board_cmt where bd_cmt_no=?";
		Object[] param = { bd_cmt_no };
		boolean deleteResult = new SQLHelper().executeUpdate(sql, param);
		return deleteResult;
	}
	// 建置分頁(彈性排序可設條件)

	public List<Board_cmt> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String sql = "select bd_cmt_no,mem_no,cmt_type,org_no,bd_cmt_ctx,cmt_likes,bd_cmt_time from (select bd_cmt_no,mem_no,cmt_type,org_no,bd_cmt_ctx,cmt_likes,bd_cmt_time, rownum rn from (select * from board_cmt";
		if (where != null) {
			sql = sql + " where " + where;
		}
		sql = sql + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Board_cmt> list = getVOBySQL(sql, null);
		return list;
	}

	public List<Board_cmt> pageAndRank(String cmt_type, String org_no) {
		String sql = "select * from (select bd_cmt_no,mem_no,cmt_type,org_no,bd_cmt_ctx,cmt_likes,bd_cmt_time,mem_nickname,mem_rank,if_click from (select bd_cmt_no,a.mem_no,a.cmt_type,org_no,bd_cmt_ctx,cmt_likes,bd_cmt_time,mem_nickname,mem_rank,if_click from board_cmt a join members b on a.mem_no=b.mem_no left outer join cmt_likes_record c on a.bd_cmt_no=c.cmt_pk and a.cmt_type = c.cmt_type and a.mem_no = c.mem_no)";

		sql = sql + " where cmt_type="+cmt_type+" and org_no="+org_no;

		sql = sql + " order by cmt_likes desc, bd_cmt_time)";
		List<Board_cmt> list = getVOBySQL(sql, null);
		return list;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Board_cmt> pageAndRank(int page, int pageSize, String order) {
		List<Board_cmt> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Board_cmt> pageAndRankByPk(int page, int pageSize) {
		List<Board_cmt> list = pageAndRank(page, pageSize, "bd_cmt_no");
		return list;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String sql = "select " + col + " from board_cmt where bd_cmt_no=?";
		List<Object[]> list = new SQLHelper().executeQuery(sql, param);
		Object[] colData = list.get(0);
		return colData;
		// Service層實作

	}

	public boolean setBd_likes(String bd_cmt_no, Cmt_likes_record cmt_likes_record) {
		boolean result = true;
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		try {
			con.setAutoCommit(false);
			Cmt_likes_recordDAO cmt_likes_recordDAO = new Cmt_likes_recordDAO();
			result = cmt_likes_recordDAO.executeInsert(cmt_likes_record, con);
			String res =null;
			if(result){			
				String sql = "update board_cmt set cmt_likes= (select cmt_likes+1 from board_cmt where bd_cmt_no=" + bd_cmt_no
						+ ") where bd_cmt_no=" + bd_cmt_no;
				res = helper.executeUpdate(sql, null,null, con);
			}
			if(res!=null){
				con.commit();
				return true;
			}else{
				con.rollback();
				return false;			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			helper.close(con);
		}
		return result;
		
		
	}
	public boolean negativeBd_likes(String bd_cmt_no, Cmt_likes_record cmt_likes_record) {
		boolean result = true;
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		try {
			con.setAutoCommit(false);
			Cmt_likes_recordDAO cmt_likes_recordDAO = new Cmt_likes_recordDAO();
			result = cmt_likes_recordDAO.executeDelete(cmt_likes_record, con);
			String res =null;
			if(result){			
				String sql = "update board_cmt set cmt_likes= (select cmt_likes-1 from board_cmt where bd_cmt_no=" + bd_cmt_no
						+ ") where bd_cmt_no=" + bd_cmt_no;
				res = helper.executeUpdate(sql, null,null, con);
			}
			if(res!=null){
				con.commit();
				return true;
			}else{
				con.rollback();
				return false;			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			helper.close(con);
		}
		return result;
		
		
	}

}
