package com.queans.model;

import java.util.*;
import java.sql.*;

public class QueansJDBCDAO implements QueansDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = 
		"INSERT INTO queans (que_no,upd_date,que_type,que_title,ans_ctx,que_photo) VALUES (queans_seq.NEXTVAL, default, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT que_no,to_char(upd_date,'yyyy-mm-dd')upd_date,que_type,que_title,ans_ctx,que_photo FROM queans order by que_no";
	private static final String GET_ONE_STMT = 
		"SELECT que_no,to_char(upd_date,'yyyy-mm-dd')upd_date,que_type,que_title,ans_ctx,que_photo FROM queans where que_no = ?";
	private static final String FIND_BY_TITLE_STMT = 
		"SELECT que_no,to_char(upd_date,'yyyy-mm-dd')upd_date,que_type,que_title,ans_ctx,que_photo FROM queans where ";
	private static final String FIND_BY_TYPE_STMT = 
		"SELECT que_no,to_char(upd_date,'yyyy-mm-dd')upd_date,que_type,que_title,ans_ctx,que_photo FROM queans where que_type= ?";
	private static final String FIND_BY_DATE_STMT = 
		"SELECT que_no,to_char(upd_date,'yyyy-mm-dd')upd_date,que_type,que_title,ans_ctx,que_photo FROM queans where to_char(upd_date,'yyyy-mm-dd')= ?";
	private static final String DELETE = 
		"DELETE FROM queans where que_no = ?";
	private static final String UPDATE = 
		"UPDATE queans set upd_date=sysdate,que_type=?,que_title=?,ans_ctx=?,que_photo=? where que_no = ?";

	@Override
	public void insert(QueansVO queansVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, queansVO.getQue_type());
			pstmt.setString(2, queansVO.getQue_title());
			pstmt.setString(3, queansVO.getAns_ctx());
			pstmt.setBytes(4, queansVO.getQue_photo());
			

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
	public void update(QueansVO queansVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, queansVO.getQue_type());
			pstmt.setString(2, queansVO.getQue_title());
			pstmt.setString(3, queansVO.getAns_ctx());
			pstmt.setBytes(4, queansVO.getQue_photo());
			pstmt.setString(5, queansVO.getQue_no());

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
	public void delete(String que_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, que_no);

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
	public QueansVO findByPrimaryKey(String que_no) {

		QueansVO queansVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, que_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				queansVO = new QueansVO();
				queansVO.setQue_no(rs.getString("que_no"));
				queansVO.setUpd_date(rs.getDate("upd_date"));
				queansVO.setQue_type(rs.getString("que_type"));
				queansVO.setQue_title(rs.getString("que_title"));
				queansVO.setAns_ctx(rs.getString("ans_ctx"));
				queansVO.setQue_photo(rs.getBytes("que_photo"));
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
		return queansVO;
	}
    public List<QueansVO> getPartByTitle(String title) {
		
		QueansVO queansVO = null;
		Connection con = null;
		List<QueansVO> list = new ArrayList<QueansVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			//Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = FIND_BY_TITLE_STMT + " que_title like '%"+title+"%'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				queansVO = new QueansVO();
				queansVO.setQue_no(rs.getString("que_no"));
				queansVO.setUpd_date(rs.getDate("upd_date"));
				queansVO.setQue_type(rs.getString("que_type"));
				queansVO.setQue_title(rs.getString("que_title"));
				queansVO.setAns_ctx(rs.getString("ans_ctx"));
				queansVO.setQue_photo(rs.getBytes("que_photo"));
				list.add(queansVO);
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public List<QueansVO> getAll() {
		List<QueansVO> list = new ArrayList<QueansVO>();
		QueansVO queansVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// queansVO 也稱為 Domain objects
				queansVO = new QueansVO();
				queansVO.setQue_no(rs.getString("que_no"));
				queansVO.setUpd_date(rs.getDate("upd_date"));
				queansVO.setQue_type(rs.getString("que_type"));
				queansVO.setQue_title(rs.getString("que_title"));
				queansVO.setAns_ctx(rs.getString("ans_ctx"));
				queansVO.setQue_photo(rs.getBytes("que_photo"));
				list.add(queansVO); // Store the row in the list
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
	public List<QueansVO> getPartByType(String que_type) {
		List<QueansVO> list = new ArrayList<QueansVO>();
		QueansVO queansVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			//Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_TYPE_STMT);
			pstmt.setString(1, que_type);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// queansVO 也稱為 Domain objects
				queansVO = new QueansVO();
				queansVO.setQue_no(rs.getString("que_no"));
				queansVO.setUpd_date(rs.getDate("upd_date"));
				queansVO.setQue_type(rs.getString("que_type"));
				queansVO.setQue_title(rs.getString("que_title"));
				queansVO.setAns_ctx(rs.getString("ans_ctx"));
				queansVO.setQue_photo(rs.getBytes("que_photo"));
				list.add(queansVO); // Store the row in the list
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
	
	@Override
	public List<QueansVO> getPartByDate(String upd_date) {
		List<QueansVO> list = new ArrayList<QueansVO>();
		QueansVO queansVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println(upd_date);
			//Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_DATE_STMT);
			pstmt.setString(1, upd_date);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// fitkwVO 也稱為 Domain objects
				queansVO = new QueansVO();
				queansVO.setQue_no(rs.getString("que_no"));
				queansVO.setUpd_date(rs.getDate("upd_date"));
				queansVO.setQue_type(rs.getString("que_type"));
				queansVO.setQue_title(rs.getString("que_title"));
				queansVO.setAns_ctx(rs.getString("ans_ctx"));
				queansVO.setQue_photo(rs.getBytes("que_photo"));
				list.add(queansVO); // Store the row in the list
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

		QueansJDBCDAO dao = new QueansJDBCDAO();

		// 新增
		QueansVO queansVO1 = new QueansVO();
		queansVO1.setQue_type("XX");
		queansVO1.setQue_title("YY");
		queansVO1.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		queansVO1.setAns_ctx(new String("PPP"));
		queansVO1.setQue_photo(null);
		dao.insert(queansVO1);

		// 修改
		QueansVO queansVO2 = new QueansVO();
		queansVO2.setQue_no("1");
		queansVO2.setQue_type("XX");
		queansVO2.setQue_title("YY");
		queansVO2.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		queansVO2.setAns_ctx(new String("PPP"));
		queansVO2.setQue_photo(null);
		dao.update(queansVO2);

		// 刪除
		dao.delete("5");

		// 查詢
		QueansVO queansVO3 = dao.findByPrimaryKey("1");
		System.out.print(queansVO3.getQue_no() + ",");
		System.out.print(queansVO3.getQue_type() + ",");
		System.out.print(queansVO3.getQue_title() + ",");
		System.out.print(queansVO3.getUpd_date() + ",");
		System.out.print(queansVO3.getAns_ctx() + ",");
		System.out.println(queansVO3.getQue_photo());
		System.out.println("---------------------");

		// 查詢
		List<QueansVO> list = dao.getAll();
		for (QueansVO aQueans : list) {
			System.out.print(aQueans.getQue_no() + ",");
			System.out.print(aQueans.getQue_type() + ",");
			System.out.print(aQueans.getQue_title() + ",");
			System.out.print(aQueans.getUpd_date() + ",");
			System.out.print(aQueans.getAns_ctx() + ",");
			System.out.print(aQueans.getQue_photo());
			System.out.println();
		}
	}

	@Override
	public void updateTwo(QueansVO queansVO) {
		// TODO Auto-generated method stub
		
	}
}

