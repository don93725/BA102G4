package com.annew.model;

import java.util.*;
import java.sql.*;

public class AnnewJDBCDAO implements AnnewDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = 
		"INSERT INTO annew (ann_no,ann_date,upd_date,ann_title,ann_ctx,ann_photo,att_no) VALUES (annew_seq.NEXTVAL, default, null, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew order by ann_no";
	private static final String GET_ONE_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew where ann_no = ?";
	private static final String FIND_BY_TITLE_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew where ";
	private static final String FIND_BY_DATE_STMT = 
		"SELECT ann_no,to_char(ann_date,'yyyy-mm-dd')ann_date,to_char(upd_date,'yyyy-mm-dd')upd_date,ann_title,ann_ctx,ann_photo,att_no FROM annew where to_char(ann_date,'yyyy-mm-dd')= ?";
	private static final String DELETE = 
		"DELETE FROM annew where ann_no = ?";
	private static final String UPDATE = 
		"UPDATE annew set upd_date=sysdate,ann_title=?,ann_ctx=?,ann_photo=?,att_no=? where ann_no = ?";

	@Override
	public void insert(AnnewVO annewVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setString(1, annewVO.getAnn_title());
			pstmt.setString(2, annewVO.getAnn_ctx());
			pstmt.setBytes(3, annewVO.getAnn_photo());
			pstmt.setBytes(4, annewVO.getAtt_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(AnnewVO annewVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			
			pstmt.setString(1, annewVO.getAnn_title());
			pstmt.setString(2, annewVO.getAnn_ctx());
			pstmt.setBytes(3, annewVO.getAnn_photo());
			pstmt.setBytes(4, annewVO.getAtt_no());
			pstmt.setString(5, annewVO.getAnn_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String ann_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ann_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public AnnewVO findByPrimaryKey(String ann_no) {

		AnnewVO annewVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ann_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return annewVO;
	}

	public List<AnnewVO> getPartByTitle(String title) {

		AnnewVO annewVO = null;
		Connection con = null;
		List<AnnewVO> list = new ArrayList<AnnewVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			//Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = FIND_BY_TITLE_STMT + " ann_title like '%"+ title +"%'";			
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
				list.add(annewVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<AnnewVO> getAll() {
		List<AnnewVO> list = new ArrayList<AnnewVO>();
		AnnewVO annewVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// annewVO 也稱為 Domain objects
				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
				list.add(annewVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<AnnewVO> getPartByDate(String ann_date) {
		List<AnnewVO> list = new ArrayList<AnnewVO>();
		AnnewVO annewVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println(ann_date);
			//Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_DATE_STMT);
			pstmt.setString(1, ann_date);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// fitkwVO 也稱為 Domain objects
				annewVO = new AnnewVO();
				annewVO.setAnn_no(rs.getString("ann_no"));
				annewVO.setAnn_date(rs.getDate("ann_date"));
				annewVO.setUpd_date(rs.getDate("upd_date"));
				annewVO.setAnn_title(rs.getString("ann_title"));
				annewVO.setAnn_ctx(rs.getString("ann_ctx"));
				annewVO.setAnn_photo(rs.getBytes("ann_photo"));
				annewVO.setAtt_no(rs.getBytes("att_no"));
				list.add(annewVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		AnnewJDBCDAO dao = new AnnewJDBCDAO();

		// 新增
		AnnewVO annewVO1 = new AnnewVO();
		
		annewVO1.setAnn_title("YY");
		annewVO1.setAnn_date(java.sql.Date.valueOf("2012-01-01"));
		annewVO1.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		annewVO1.setAnn_ctx(new String("PPP"));
		annewVO1.setAnn_photo(null);
		annewVO1.setAtt_no(null);
		dao.insert(annewVO1);

		// 修改
		AnnewVO annewVO2 = new AnnewVO();
		annewVO2.setAnn_no("1");
		annewVO2.setAnn_title("YY");
		annewVO2.setAnn_date(java.sql.Date.valueOf("2012-01-01"));
		annewVO2.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		annewVO2.setAnn_ctx(new String("PPP"));
		annewVO2.setAnn_photo(null);
		annewVO2.setAtt_no(null);
		dao.update(annewVO2);

		// 刪除
		dao.delete("5");

		// 查詢
		AnnewVO annewVO3 = dao.findByPrimaryKey("1");
		System.out.print(annewVO3.getAnn_no() + ",");
		System.out.print(annewVO3.getAnn_title() + ",");
		System.out.print(annewVO3.getAnn_date() + ",");
		System.out.print(annewVO3.getUpd_date() + ",");
		System.out.print(annewVO3.getAnn_ctx() + ",");
		System.out.println(annewVO3.getAnn_photo()+ ",");
		System.out.println(annewVO3.getAtt_no());
		System.out.println("---------------------");

		// 查詢
		List<AnnewVO> list = dao.getAll();
		for (AnnewVO aAnnew : list) {
			System.out.print(aAnnew.getAnn_no() + ",");
			System.out.print(aAnnew.getAnn_title() + ",");
			System.out.print(aAnnew.getAnn_date() + ",");
			System.out.print(aAnnew.getUpd_date() + ",");
			System.out.print(aAnnew.getAnn_ctx() + ",");
			System.out.print(aAnnew.getAnn_photo()+ ",");
			System.out.print(aAnnew.getAtt_no());
			System.out.println();
		}
	}

	@Override
	public void updateTwo(AnnewVO annewVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AnnewVO> getFrontAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
