package com.forum.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;
import com.forum.domain.*;

public class ForumsDAO extends BasicDAO implements DAOInterface<Forums> {
	// 建置查詢

	public List<Forums> getVOBySQL(String SQL, Object[] param) {
		List list = new SQLHelper().executeQuery(SQL, param);
		List<Forums> tempList = new ArrayList<Forums>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Forums forums = new Forums();
			if (obj[0] != null) {
				forums.setForum_no((String) obj[0]);
			}
			if (obj[1] != null) {
				forums.setMem_no((String) obj[1]);
			}
			if (obj[2] != null) {
				forums.setForum_name((String) obj[2]);
			}
			if (obj[3] != null) {
				forums.setForum_desc((String) obj[3]);
			}
			if (obj[4] != null) {
				forums.setForum_note((String) obj[4]);
			}
			if (obj[5] != null) {
				forums.setForum_stat(Integer.parseInt(String.valueOf(obj[5])));
			}
			if (obj[6] != null) {
				forums.setForum_views(Integer.parseInt(obj[6].toString()));
			}
			if(obj[7]!=null){
				forums.setForum_mviews(Integer.parseInt(obj[7].toString()));
				}
			if(obj[8]!=null){
				forums.setForum_date((Date)obj[8]);
				}
			tempList.add(forums);
		}
		return tempList;
	}
	// 建置查詢單筆

	public Forums getVOByPK(String forum_no) {
		String SQL = "Select * from forums where forum_no=?";
		Object[] param = { forum_no };
		List<Forums> list = getVOBySQL(SQL, param);
		Forums forums = list.get(0);
		return forums;
	}
	// 建置查詢全部

	public List<Forums> getAll() {
		String SQL = "select * from forums where forum_stat=1";
		List<Forums> list = getVOBySQL(SQL, null);
		return list;
	}
	public List<Forums> getAllOfficialForum() {
		String SQL = "select * from forums where forum_stat=3";
		List<Forums> list = getVOBySQL(SQL, null);
		return list;
	}
	// 算數量

	public int countAll() {
		String SQL = "select * from forums";
		return countBySQL(SQL);
	}
	// 計算文章數量
	public Map<String,Integer> countArticle(String forum_stat){
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		try {
			String SQL = "select forum_no,count(*) from articles group by forum_no";
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				map.put(rs.getString(1), rs.getInt(2));						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			helper.close(con, pstmt,rs);
		}
		return map;
	}
	// 建置修改

	public boolean updateByVO(Forums forums) {
		String SQL = "update forums set forum_name=?,forum_desc=?,forum_note=? where forum_no=?";
		Object[] param = { forums.getForum_name(), forums.getForum_desc(), forums.getForum_note(),forums.getForum_no()  };
		boolean updateResult = new SQLHelper().executeUpdate(SQL, param);
		return updateResult;
	}
	// 建置修改
	
	public boolean updateByVO(Forums forums,String[] art_type_name) {
		SQLHelper helper =	new SQLHelper();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		boolean result = false;	
		
		con=helper.getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "update forums set forum_desc=? where forum_no=?";
			pstmt = con.prepareStatement(sql);
			Object[] param = { forums.getForum_desc(),forums.getForum_no()  };
			String tip = helper.executeUpdate(sql, param, null, con);
			if(tip!=null){						
				Art_typesDAO art_typesDAO = new Art_typesDAO();
				result = art_typesDAO.updateByVO(art_type_name,forums.getForum_no(),con);
				if(result){
					con.commit();
				}
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
			helper.close(con,pstmt);
		}
		return result;
	}
	// 建置新增

	public boolean executeInsert(Forums forums) {
		SQLHelper helper =	new SQLHelper();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		boolean result = false;		
		try {
			con = helper.getConnection();
			con.setAutoCommit(false);			
			String SQL = "insert into forums values(forums_pk_seq.nextval,?,?,?,?,default,default,default,default)";
			String[] keys = {"forum_no"};
			pstmt = con.prepareStatement(SQL,keys);
			Object[] param = { forums.getMem_no(), forums.getForum_name(), forums.getForum_desc(),
					forums.getForum_note() };
			for(int i =0 ; i<param.length; i++){
				pstmt.setObject(i+1, param[i]);				
			}
			pstmt.executeUpdate();
			rs =pstmt.getGeneratedKeys();
			rs.next();
			String forum_no = rs.getString(1);
			Art_typesDAO art_typesDAO = new Art_typesDAO();
			Art_types art_types = new Art_types();
			art_types.setArt_type_name("其他");
			art_types.setForum_no(forum_no);
			result = art_typesDAO.executeInsert(art_types,con);	
			if(result){
				con.commit();
			}
			
			
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			helper.close(con, pstmt);
		}
		return result;
	}
	//新增
	public boolean executeInsert(Forums forums,String[] art_type_name) {
		SQLHelper helper =	new SQLHelper();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		boolean result = false;		
		try {
			con = helper.getConnection();
			con.setAutoCommit(false);			
			String SQL = "insert into forums values(forums_pk_seq.nextval,?,?,?,?,default,default,default,default)";
			String[] keys = {"forum_no"};
			pstmt = con.prepareStatement(SQL,keys);
			Object[] param = { forums.getMem_no(), forums.getForum_name(), forums.getForum_desc(),
					forums.getForum_note() };
			for(int i =0 ; i<param.length; i++){
				pstmt.setObject(i+1, param[i]);				
			}
			pstmt.executeUpdate();
			rs =pstmt.getGeneratedKeys();
			rs.next();
			String forum_no = rs.getString(1);
			List<Art_types> list = new ArrayList<Art_types>();
			for(String type_name:art_type_name ){
				if(type_name.length()!=0){
					Art_types art_types = new Art_types();
					art_types.setArt_type_name(type_name);
					art_types.setForum_no(forum_no);
					list.add(art_types);								
				}
			}
			if(list.size()==0){
				Art_types art_types = new Art_types();
				art_types.setArt_type_name("其他");
				art_types.setForum_no(forum_no);
				list.add(art_types);				
			}
			Art_typesDAO art_typesDAO = new Art_typesDAO();
			result = art_typesDAO.executeInsert(list,con);			
			
			
			
		} catch (SQLException e) {
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
	// 建置刪除

	public boolean executeDelete(String forum_no) {
		String SQL = "delete from forums where forum_no=?";
		Object[] param = { forum_no };
		boolean deleteResult = new SQLHelper().executeUpdate(SQL, param);
		return deleteResult;
	}
	// 建置分頁(彈性排序可設條件)

	public List<Forums> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String SQL = "select forum_no,mem_no,forum_name,forum_desc,forum_note,forum_stat,forum_views,forum_mviews,forum_date from (select forum_no,mem_no,forum_name,forum_desc,forum_note,forum_stat,forum_views,forum_mviews,forum_date, rownum rn from (select * from forums";
		if (where != null) {
			SQL = SQL + " where " + where;
		}
		SQL = SQL + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Forums> list = getVOBySQL(SQL, null);
		return list;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Forums> pageAndRank(int page, int pageSize, String order) {
		List<Forums> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Forums> pageAndRankByPk(int page, int pageSize) {
		List<Forums> list = pageAndRank(page, pageSize, "forum_no");
		return list;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String SQL = "select " + col + " from forums where forum_no=?";
		List<Object[]> list = new SQLHelper().executeQuery(SQL, param);
		Object[] colData = list.get(0);	
		
		return colData;
	}
	
	
	
}
