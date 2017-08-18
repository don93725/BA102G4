package com.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;
import com.forum.domain.Art_types;

public class Art_typesDAO extends BasicDAO implements DAOInterface<Art_types> {
	// 建置查詢

	public List<Art_types> getVOBySQL(String SQL, Object[] param) {
		List list = new SQLHelper().executeQuery(SQL, param);
		List<Art_types> tempList = new ArrayList<Art_types>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Art_types art_types = new Art_types();
			if (obj[0] != null) {
				art_types.setArt_type_no((String) obj[0]);
			}
			if (obj[1] != null) {
				art_types.setForum_no((String) obj[1]);
			}
			if (obj[2] != null) {
				art_types.setArt_type_name((String) obj[2]);
			}
			if (obj[3] != null) {
				art_types.setArt_type_stat(Integer.parseInt(String.valueOf(obj[3])));
			}

			tempList.add(art_types);
		}
		return tempList;
	}
	// 建置查詢單筆

	public Art_types getVOByPK(String art_type_no) {
		String SQL = "Select * from art_types where art_type_no=?";
		Object[] param = { art_type_no };
		List<Art_types> list = getVOBySQL(SQL, param);
		Art_types art_types = list.get(0);
		return art_types;
	}
	// 建置查詢全部

	public List<Art_types> getAll() {
		String SQL = "select * from art_types";
		List<Art_types> list = getVOBySQL(SQL, null);
		return list;
	}
	// 算數量

	public int countAll() {
		String SQL = "select * from art_types";
		return countBySQL(SQL);
	}
	// 建置修改

	public boolean updateByVO(Art_types art_types) {
		String sql = "update art_types set forum_no=?,art_type_name=? where art_type_no=?";
		Object[] param = { art_types.getArt_type_no(), art_types.getForum_no(), art_types.getArt_type_name() };
		boolean updateResult = new SQLHelper().executeUpdate(sql, param);
		return updateResult;
	}
	// 建置修改2

	public boolean updateByVO(String[] art_type_name, String forum_no, Connection conn) {
		SQLHelper helper = new SQLHelper();
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			String sql2 = "update art_types set art_type_stat=1 where forum_no=? and art_type_name in (";
			String sql = "select art_type_name from art_types where forum_no=? and art_type_name not in (";
			for (int i = 0; i < art_type_name.length; i++) {
				String type_name = art_type_name[i];
				if(type_name.length()!=0){
					sql = sql + "'" + type_name + "',";					
				}
			}
			sql = sql.substring(0, sql.length() - 1) + ")";

			sql2 = sql2 + sql + ")";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, forum_no);
			pstmt.setString(2, forum_no);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(sql.replace("not in (", "in ("));
			pstmt.setString(1, forum_no);
			rs = pstmt.executeQuery();
			List<String> temp = new ArrayList<String>();
			while (rs.next()) {
				temp.add(rs.getString(1));
			}
			rs.close();
			String sql4 = "select art_type_name from art_types where forum_no=?";
			pstmt.setString(1, forum_no);
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				temp.add(rs.getString(1));
			}
			rs.close();
			String sql3 = "insert into art_types values(art_types_seq.nextval,?,?,default)";
			for (int i = 0; i < art_type_name.length; i++) {
				String type_name = art_type_name[i];
				pstmt = con.prepareStatement(sql3);
				if (!temp.contains(type_name)&&type_name.length()!=0) {
					pstmt.setObject(1, forum_no);
					pstmt.setObject(2, type_name);
					pstmt.executeUpdate();

				}

			}

			pstmt.executeBatch();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return result;
	}
	// 建置新增

	public boolean executeInsert(Art_types art_types) {
		String SQL = "insert into art_types values(?,?,?,default)";
		Object[] param = { art_types.getArt_type_no(), art_types.getForum_no(), art_types.getArt_type_name() };
		boolean insertResult = new SQLHelper().executeUpdate(SQL, param);
		return insertResult;
	}

	// 建置新增2
	public boolean executeInsert(Art_types art_types, Connection conn) {
		SQLHelper helper = new SQLHelper();
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			String SQL = "insert into art_types values(art_types_seq.nextval,?,?,default)";
			pstmt = con.prepareStatement(SQL);
			Object[] param = { art_types.getForum_no(), art_types.getArt_type_name() };
			pstmt.setObject(1, param[0]);
			pstmt.setObject(2, param[1]);
			pstmt.executeUpdate();
			con.commit();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return result;
	}

	// 建置新增2
	public boolean executeInsert(List<Art_types> art_types, Connection conn) {
		SQLHelper helper = new SQLHelper();
		Connection con = conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			String SQL = "insert into art_types values(art_types_seq.nextval,?,?,default)";
			pstmt = con.prepareStatement(SQL);
			for (Art_types art_type : art_types) {
				pstmt.setObject(1, art_type.getForum_no());
				pstmt.setObject(2, art_type.getArt_type_name());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
			con.commit();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				helper.close(con, pstmt);
			}
			e.printStackTrace();
		}

		return result;
	}
	// 建置刪除

	public boolean executeDelete(String art_type_no) {
		String SQL = "delete from art_types where art_type_no=?";
		Object[] param = { art_type_no };
		boolean deleteResult = new SQLHelper().executeUpdate(SQL, param);
		return deleteResult;
	}
	// 建置分頁(彈性排序可設條件)

	public List<Art_types> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String SQL = "select art_type_no,forum_no,art_type_name from (select art_type_no,forum_no,art_type_name, rownum rn from art_types";
		if (where != null) {
			SQL = SQL + " where " + where;
		}
		SQL = SQL + " order by " + order + ") where rn between " + firstPage + " and " + lastPage;
		List<Art_types> list = getVOBySQL(SQL, null);
		return list;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Art_types> pageAndRank(int page, int pageSize, String order) {
		List<Art_types> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Art_types> pageAndRankByPk(int page, int pageSize) {
		List<Art_types> list = pageAndRank(page, pageSize, "art_type_no");
		return list;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String SQL = "select " + col + " from art_types where art_type_no=?";
		List<Object[]> list = new SQLHelper().executeQuery(SQL, param);
		Object[] colData = list.get(0);
		return colData;
	}

}
