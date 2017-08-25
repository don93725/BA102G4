package com.fitkw.model;

import java.util.*;
import java.sql.*;

public class FitkwJDBCDAO implements FitkwDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = 
			"INSERT INTO fitkw (fik_no,upd_date,fik_type,fik_title,fik_ctx,fik_photo) VALUES (fitkw_seq.NEXTVAL, default, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT fik_no,to_char(upd_date,'yyyy-mm-dd')upd_date,fik_type,fik_title,fik_ctx,fik_photo FROM fitkw order by fik_no";
		private static final String GET_ONE_STMT = 
			"SELECT fik_no,to_char(upd_date,'yyyy-mm-dd')upd_date,fik_type,fik_title,fik_ctx,fik_photo FROM fitkw where fik_no =?";
		private static final String FIND_BY_TITLE_STMT = 
			"SELECT fik_no,to_char(upd_date,'yyyy-mm-dd')upd_date,fik_type,fik_title,fik_ctx,fik_photo FROM fitkw where ";
		private static final String FIND_BY_TYPE_STMT = 
			"SELECT fik_no,to_char(upd_date,'yyyy-mm-dd')upd_date,fik_type,fik_title,fik_ctx,fik_photo FROM fitkw where fik_type=?";
		private static final String FIND_BY_DATE_STMT = 
			"SELECT fik_no,to_char(upd_date,'yyyy-mm-dd')upd_date,fik_type,fik_title,fik_ctx,fik_photo FROM fitkw where to_char(upd_date,'yyyy-mm-dd')=?";
		private static final String DELETE = 
			"DELETE FROM fitkw where fik_no = ?";
		private static final String UPDATE = 
			"UPDATE fitkw set upd_date=sysdate,fik_type=?,fik_title=?,fik_ctx=?,fik_photo=? where fik_no = ?";
	@Override
	public void insert(FitkwVO fitkwVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, fitkwVO.getFik_type());
			pstmt.setString(2, fitkwVO.getFik_title());
			pstmt.setString(3, fitkwVO.getFik_ctx());
			pstmt.setBytes(4, fitkwVO.getFik_photo());
			

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
	public void update(FitkwVO fitkwVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, fitkwVO.getFik_type());
			pstmt.setString(2, fitkwVO.getFik_title());
			pstmt.setString(3, fitkwVO.getFik_ctx());
			pstmt.setBytes(4, fitkwVO.getFik_photo());
			pstmt.setString(5, fitkwVO.getFik_no());

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
	public void delete(String fik_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fik_no);

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
	public FitkwVO findByPrimaryKey(String fik_no) {

		FitkwVO fitkwVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fik_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				fitkwVO = new FitkwVO();
				fitkwVO.setFik_no(rs.getString("fik_no"));
				fitkwVO.setUpd_date(rs.getDate("upd_date"));
				fitkwVO.setFik_type(rs.getString("fik_type"));
				fitkwVO.setFik_title(rs.getString("fik_title"));
				fitkwVO.setFik_ctx(rs.getString("fik_ctx"));
				fitkwVO.setFik_photo(rs.getBytes("fik_photo"));
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
		return fitkwVO;
	}

    public List<FitkwVO> getPartByTitle(String title) {
		
		FitkwVO fitkwVO = null;
		Connection con = null;
		List<FitkwVO> list = new ArrayList<FitkwVO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = FIND_BY_TITLE_STMT + " fik_title like '%"+title+"%'";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				fitkwVO = new FitkwVO();
				fitkwVO.setFik_no(rs.getString("fik_no"));
				fitkwVO.setUpd_date(rs.getDate("upd_date"));
				fitkwVO.setFik_type(rs.getString("fik_type"));
				fitkwVO.setFik_title(rs.getString("fik_title"));
				fitkwVO.setFik_ctx(rs.getString("fik_ctx"));
				fitkwVO.setFik_photo(rs.getBytes("fik_photo"));
				list.add(fitkwVO);
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
	public List<FitkwVO> getAll() {
		List<FitkwVO> list = new ArrayList<FitkwVO>();
		FitkwVO fitkwVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// fitkwVO 也稱為 Domain objects
				fitkwVO = new FitkwVO();
				fitkwVO.setFik_no(rs.getString("fik_no"));
				fitkwVO.setUpd_date(rs.getDate("upd_date"));
				fitkwVO.setFik_type(rs.getString("fik_type"));
				fitkwVO.setFik_title(rs.getString("fik_title"));
				fitkwVO.setFik_ctx(rs.getString("fik_ctx"));
				fitkwVO.setFik_photo(rs.getBytes("fik_photo"));
				list.add(fitkwVO); // Store the row in the list
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
	public List<FitkwVO> getPartByType(String fik_type) {
		List<FitkwVO> list = new ArrayList<FitkwVO>();
		FitkwVO fitkwVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_TYPE_STMT);
			pstmt.setString(1, fik_type);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// annewsVO 也稱為 Domain objects
				fitkwVO = new FitkwVO();
				fitkwVO.setFik_no(rs.getString("fik_no"));
				fitkwVO.setUpd_date(rs.getDate("upd_date"));
				fitkwVO.setFik_type(rs.getString("fik_type"));
				fitkwVO.setFik_title(rs.getString("fik_title"));
				fitkwVO.setFik_ctx(rs.getString("fik_ctx"));
				fitkwVO.setFik_photo(rs.getBytes("fik_photo"));
				list.add(fitkwVO); // Store the row in the list
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
	public List<FitkwVO> getPartByDate(String upd_date) {
		List<FitkwVO> list = new ArrayList<FitkwVO>();
		FitkwVO fitkwVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			System.out.println(upd_date);
//			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_DATE_STMT);
			pstmt.setString(1, upd_date);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// fitkwVO 也稱為 Domain objects
				fitkwVO = new FitkwVO();
				fitkwVO.setFik_no(rs.getString("fik_no"));
				fitkwVO.setUpd_date(rs.getDate("upd_date"));
				fitkwVO.setFik_type(rs.getString("fik_type"));
				fitkwVO.setFik_title(rs.getString("fik_title"));
				fitkwVO.setFik_ctx(rs.getString("fik_ctx"));
				fitkwVO.setFik_photo(rs.getBytes("fik_photo"));
				list.add(fitkwVO); // Store the row in the list
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

		FitkwJDBCDAO dao = new FitkwJDBCDAO();

		// 新增
		FitkwVO fitkwVO1 = new FitkwVO();
		fitkwVO1.setFik_type("XX");
		fitkwVO1.setFik_title("YY");
		fitkwVO1.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		fitkwVO1.setFik_ctx(new String("PPP"));
		fitkwVO1.setFik_photo(null);
		dao.insert(fitkwVO1);

		// 修改
		FitkwVO fitkwVO2 = new FitkwVO();
		fitkwVO2.setFik_no("1");
		fitkwVO2.setFik_type("XX");
		fitkwVO2.setFik_title("YY");
		fitkwVO2.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		fitkwVO2.setFik_ctx(new String("PPP"));
		fitkwVO2.setFik_photo(null);
		dao.update(fitkwVO2);

		// 刪除
		dao.delete("5");

		// 查詢
		FitkwVO fitkwVO3 = dao.findByPrimaryKey("1");
		System.out.print(fitkwVO3.getFik_no() + ",");
		System.out.print(fitkwVO3.getFik_type() + ",");
		System.out.print(fitkwVO3.getFik_title() + ",");
		System.out.print(fitkwVO3.getUpd_date() + ",");
		System.out.print(fitkwVO3.getFik_ctx() + ",");
		System.out.println(fitkwVO3.getFik_photo());
		System.out.println("---------------------");

		// 查詢
		List<FitkwVO> list = dao.getAll();
		for (FitkwVO aFitkw : list) {
			System.out.print(aFitkw.getFik_no() + ",");
			System.out.print(aFitkw.getFik_type() + ",");
			System.out.print(aFitkw.getFik_title() + ",");
			System.out.print(aFitkw.getUpd_date() + ",");
			System.out.print(aFitkw.getFik_ctx() + ",");
			System.out.print(aFitkw.getFik_photo());
			System.out.println();
		}
	}

	@Override
	public void updateTwo(FitkwVO fitkwVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FitkwVO> getFrontAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

