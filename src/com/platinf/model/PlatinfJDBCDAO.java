package com.platinf.model;

import java.util.*;
import java.sql.*;

public class PlatinfJDBCDAO implements PlatinfDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = 
		"INSERT INTO platinf (pin_no,com_address,cp_no,cs_email,pr_policy,upd_date,pin_photo) VALUES (platinf_seq.NEXTVAL, ?, ?, ?, ?, default, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pin_no,com_address,cp_no,cs_email,pr_policy,to_char(upd_date,'yyyy-mm-dd')upd_date,pin_photo FROM platinf order by pin_no";
	private static final String GET_ONE_STMT = 
		"SELECT pin_no,com_address,cp_no,cs_email,pr_policy,to_char(upd_date,'yyyy-mm-dd')upd_date,pin_photo FROM platinf where pin_no = ?";
	private static final String DELETE = 
		"DELETE FROM platinf where pin_no = ?";
	private static final String UPDATE = 
		"UPDATE platinf set com_address=?,cp_no=?,cs_email=?,pr_policy=?,upd_date=sysdate,pin_photo where pin_no = ?";

	@Override
	public void insert(PlatinfVO platinfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, platinfVO.getCom_address());
			pstmt.setString(2, platinfVO.getCp_no());
			pstmt.setString(3, platinfVO.getCs_email());
			pstmt.setString(4, platinfVO.getPr_policy());
			pstmt.setBytes(5, platinfVO.getPin_photo());
			

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
	public void update(PlatinfVO platinfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, platinfVO.getCom_address());
			pstmt.setString(2, platinfVO.getCp_no());
			pstmt.setString(3, platinfVO.getCs_email());
			pstmt.setString(4, platinfVO.getPr_policy());
			pstmt.setBytes(5, platinfVO.getPin_photo());
			pstmt.setString(6, platinfVO.getPin_no());

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
	public void delete(String pin_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pin_no);

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
	public PlatinfVO findByPrimaryKey(String pin_no) {

		PlatinfVO platinfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pin_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				platinfVO = new PlatinfVO();
				platinfVO.setPin_no(rs.getString("pin_no"));
				platinfVO.setCom_address(rs.getString("com_address"));
				platinfVO.setCp_no(rs.getString("cp_no"));
				platinfVO.setCs_email(rs.getString("cs_email"));
				platinfVO.setPr_policy(rs.getString("pr_policy"));
				platinfVO.setUpd_date(rs.getDate("upd_date"));
				platinfVO.setPin_photo(rs.getBytes("pin_photo"));
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
		return platinfVO;
	}

	@Override
	public List<PlatinfVO> getAll() {
		List<PlatinfVO> list = new ArrayList<PlatinfVO>();
		PlatinfVO platinfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// platinfVO 也稱為 Domain objects
				platinfVO = new PlatinfVO();
				platinfVO.setPin_no(rs.getString("pin_no"));
				platinfVO.setCom_address(rs.getString("com_address"));
				platinfVO.setCp_no(rs.getString("cp_no"));
				platinfVO.setCs_email(rs.getString("cs_email"));
				platinfVO.setPr_policy(rs.getString("pr_policy"));
				platinfVO.setUpd_date(rs.getDate("upd_date"));
				platinfVO.setPin_photo(rs.getBytes("pin_photo"));
				list.add(platinfVO); // Store the row in the list
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

	public static void main(String[] args) {

		PlatinfJDBCDAO dao = new PlatinfJDBCDAO();

		// 新增
		PlatinfVO platinfVO1 = new PlatinfVO();
		platinfVO1.setCom_address("XX");
		platinfVO1.setCp_no("YY");
		platinfVO1.setCs_email(new String("PPP"));
		platinfVO1.setPr_policy(new String("WWW"));
		platinfVO1.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		platinfVO1.setPin_photo(null);
		dao.insert(platinfVO1);

		// 修改
		PlatinfVO platinfVO2 = new PlatinfVO();
		platinfVO2.setPin_no("1");
		platinfVO2.setCom_address("XX");
		platinfVO2.setCp_no("YY");
		platinfVO2.setCs_email(new String("PPP"));
		platinfVO2.setPr_policy(new String("WWW"));
		platinfVO2.setUpd_date(java.sql.Date.valueOf("2012-01-01"));
		platinfVO2.setPin_photo(null);
		
		dao.update(platinfVO2);

		// 刪除
		dao.delete("5");

		// 查詢
		PlatinfVO platinfVO3 = dao.findByPrimaryKey("1");
		System.out.print(platinfVO3.getPin_no() + ",");
		System.out.print(platinfVO3.getCom_address() + ",");
		System.out.print(platinfVO3.getCp_no() + ",");
		System.out.print(platinfVO3.getCs_email() + ",");
		System.out.print(platinfVO3.getPr_policy() + ",");
		System.out.println(platinfVO3.getUpd_date()+ ",");
		System.out.println(platinfVO3.getPin_photo());
		System.out.println("---------------------");

		// 查詢
		List<PlatinfVO> list = dao.getAll();
		for (PlatinfVO aPlatinf : list) {
			System.out.print(aPlatinf.getPin_no() + ",");
			System.out.print(aPlatinf.getCom_address() + ",");
			System.out.print(aPlatinf.getCp_no() + ",");
			System.out.print(aPlatinf.getCs_email() + ",");
			System.out.print(aPlatinf.getPr_policy() + ",");
			System.out.println(aPlatinf.getUpd_date()+ ",");
			System.out.println(aPlatinf.getPin_photo());
			System.out.println();
		}
	}

	@Override
	public void updateTwo(PlatinfVO platinfVO) {
		// TODO Auto-generated method stub
		
	}
}

