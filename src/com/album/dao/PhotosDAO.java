package com.album.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.album.domain.Photos;
import com.comments.model.Board_cmt;
import com.comments.model.Board_cmtDAO;
import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;

public class PhotosDAO extends BasicDAO implements DAOInterface<Photos> {
	// 建置查詢

	public List<Photos> getVOBySQL(String sql, Object[] param) {
		List list = new SQLHelper().executeQuery(sql, param);
		List<Photos> tempList = new ArrayList<Photos>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Photos photos = new Photos();
			if (obj[0] != null) {
				photos.setPhoto_no((String) obj[0]);
				Board_cmtDAO board_cmtDAO = new Board_cmtDAO();
				List<Board_cmt> comments = board_cmtDAO.pageAndRank("1",String.valueOf( obj[0]));
				photos.setComments(comments);
			}
			if (obj[1] != null) {
				photos.setAl_no((String) obj[1]);
			}
			if (obj[2] != null) {
				photos.setPhoto_desc((String) obj[2]);
			}
			if (obj[5] != null) {
				photos.setUl_Date((Date)obj[5]);
			}
			tempList.add(photos);
		}
		return tempList;
	}
	// 建置查詢單筆

	public Photos getVOByPK(String photo_no) {
		String sql = "Select * from photos where photo_no=?";
		Object[] param = { photo_no };
		List<Photos> list = getVOBySQL(sql, param);
		Photos photos = list.get(0);
		return photos;
	}
	// 建置查詢全部

	public List<Photos> getAll() {
		String sql = "select * from photos";
		List<Photos> list = getVOBySQL(sql, null);
		return list;
	}
	// 算數量

	public int countAll() {
		String sql = "select * from photos";
		return countBySQL(sql);
	}
	public Map<String,Integer> getPhotosNum(String mem_no){
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String,Integer> map = map = new HashMap<String,Integer>();
		try {
			String sql = "select a.al_no,count(*) from photos a join (select al_no from albums where mem_no="+mem_no+") b on a.al_no=b.al_no group by a.al_no";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				map.put(rs.getString(1), rs.getInt(2));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			helper.close(con, pstmt, rs);
		}
		return map;
		
	}
	// 建置修改

	public boolean updateByVO(Photos photos) {
		String sql = "update photos set photo_desc=? where photo_no=?";
		Object[] param = { photos.getPhoto_desc(), photos.getPhoto_no() };
		boolean updateResult = new SQLHelper().executeUpdate(sql, param);
		return updateResult;
	}
	// 建置新增

	public boolean executeInsert(Photos photos) {
		String sql = "insert into photos values(photos_pk_seq.nextval,?,?,?,?,default)";
		Object[] param = { photos.getAl_no(), photos.getPhoto_desc(), photos.getPhoto(), photos.getSphoto() };
		boolean insertResult = new SQLHelper().executeUpdate(sql, param);

		return insertResult;
	}
	// 建置新增

	public boolean executeInsert(List<Photos> photos) {
		String sql = "insert into photos values(photos_pk_seq.nextval,?,?,?,?,default)";
		boolean insertResult = true;
		for (Photos p : photos) {
			Object[] param = { p.getAl_no(), p.getPhoto_desc(), p.getPhoto(), p.getSphoto() };
			boolean res = new SQLHelper().executeUpdate(sql, param);
			if (!res) {
				insertResult = false;
			}
		}
		return insertResult;
	}
	// 建置動態相簿新增

	public List<String> executeInsert(List<Photos> photos,String mem_no,Connection conn) {
		List<String> photosKey;
		try {
			photosKey = new ArrayList<String>();
			String al_no = getCol(mem_no);
			String sql = "insert into photos values(photos_pk_seq.nextval,"+al_no+",null,?,?,default)";
			for (Photos p : photos) {
				Object[] param = {p.getPhoto(), p.getSphoto() };
				String key = new SQLHelper().executeUpdate(sql, param, "photo_no", conn);
				photosKey.add(key);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
                    + e.getMessage());	
		}
		return photosKey;
	}
	// 建置刪除

	public boolean executeDelete(String[] photo_no) {
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		boolean result = true;
		for (String s : photo_no) {
			String sql = "delete from photos where photo_no=?";
			Object[] param = { s };
			String res = helper.executeUpdate(sql, param, null, con);
			if (res == null) {
				result = false;
			}
		}
			try {
				if(result){
					con.commit();
				}else{
					con.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return result;
	}
	// 建置刪除
	
	public boolean executeDeleteForBoard(String[] photo_no ,Connection conn) {
		SQLHelper helper = new SQLHelper();
		Connection con = conn;
		boolean result = true;
		for (String s : photo_no) {
			String sql = "delete from photos where photo_no=?";
			Object[] param = { s };
			String res = helper.executeUpdate(sql, param, null, con);
			if (res == null) {
				result = false;
			}
		}
		try {
			if(result){
				con.commit();
			}else{
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	// 建置多重交易刪除

	public boolean executeDelete(String[] al_no, Connection conn) {
		SQLHelper helper = new SQLHelper();
		Connection con = conn;
		boolean result = true;

		for (String s : al_no) {
			String sql = "delete from photos where al_no=?";
			Object[] param = { s };
			String res = helper.executeUpdate(sql, param, null, conn);
			if (res == null) {
				result = false;
			}
		}

		return result;
	}
	// 建置分頁(彈性排序可設條件)

	public List<Photos> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String sql = "select photo_no,al_no,photo_desc,photo,sphoto,ul_date from (select photo_no,al_no,photo_desc,photo,sphoto,ul_date, rownum rn from (select * from photos";
		if (where != null) {
			sql = sql + " where " + where;
		}
		sql = sql + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Photos> list = getVOBySQL(sql, null);
		return list;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Photos> pageAndRank(int page, int pageSize, String order) {
		List<Photos> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Photos> pageAndRankByPk(int page, int pageSize) {
		List<Photos> list = pageAndRank(page, pageSize, "photo_no");
		return list;
	}
	// 取得圖片集合
	public byte[] getBigPic(String where) {
		String sql = "select photo from photos where " + where;
		byte[] b = new SQLHelper().getPic(sql, null);
		return b;
	}
	public byte[] getPic(String where) {
		String sql = "select sphoto from photos where " + where;
		byte[] b = new SQLHelper().getPic(sql, null);
		return b;
	}
	public byte[] getRandomPic(String al_no ,int num) {
		String sql = "select sphoto from (select sphoto,rownum rn from photos where al_no=" + al_no +") where rn = "+num;
		byte[] b = new SQLHelper().getPic(sql, null);
		return b;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String sql = "select " + col + " from photos where photo_no=?";
		List<Object[]> list = new SQLHelper().executeQuery(sql, param);
		Object[] colData = list.get(0);
		return colData;

	}
	// 建置取得動態相簿編號

	public String getCol(String mem_no) {
		String sql = "select al_no from albums where mem_no="+mem_no+" and al_board=1";
		List<Object[]> list = new SQLHelper().executeQuery(sql, null);
		String colData = String.valueOf(list.get(0)[0]);
		return colData;

	}

	@Override
	public boolean executeDelete(String no) {
		// TODO Auto-generated method stub
		return false;
	}
}