package com.folgym.model;

import java.util.*;
import java.sql.*;



public class FolgymJDBCDAO implements FolgymDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = "INSERT INTO folgym (fg_acc,fgp_acc,fg_date) VALUES (?, ?, ?)";
	private static final String GET_Folgym_ByFgp_acc_STMT = "SELECT fg_acc,fgp_acc,to_char(fg_date,'yyyy-mm-dd') fg_date FROM folgym where fgp_acc = ? order by fg_acc";
	private static final String DELETE = "DELETE FROM folgym where fg_acc = ? and where fgp_acc= ?";
	


	@Override
	public void insert(FolgymVO folgymVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folgymVO.getFg_acc());
			pstmt.setString(2, folgymVO.getFgp_acc());
			pstmt.setDate(3, folgymVO.getFg_date());

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
	public void delete(String fg_acc, String fgp_acc) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fg_acc);
			pstmt.setString(2, fgp_acc);
			

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
	public Set<FolgymVO> getFolgymByFgp_acc(String fgp_acc) {
		Set<FolgymVO> set = new LinkedHashSet<FolgymVO>();
		FolgymVO folgymVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Folgym_ByFgp_acc_STMT);
			pstmt.setString(1, fgp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folgymVO = new FolgymVO();
				folgymVO.setFg_acc(rs.getString("fg_acc"));
				folgymVO.setFgp_acc(rs.getString("fgp_acc"));
				folgymVO.setFg_date(rs.getDate("fg_date"));
				set.add(folgymVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	public static void main(String[] args) {

		FolgymJDBCDAO dao = new FolgymJDBCDAO();

		 //新增
		FolgymVO folgymVO1 = new FolgymVO();
		folgymVO1.setFg_acc("xx");
		folgymVO1.setFgp_acc("yy");
		folgymVO1.setFg_date(java.sql.Date.valueOf("2012-01-01"));
		dao.insert(folgymVO1);


		//刪除
		dao.delete("3","5");

		
		
		// 查詢某學生的教練
		Set<FolgymVO> set = dao.getFolgymByFgp_acc("1");
		for (FolgymVO aFolgym : set) {
			System.out.print(aFolgym.getFg_acc() + ",");
			System.out.print(aFolgym.getFgp_acc() + ",");
			System.out.print(aFolgym.getFg_date());
			
		}
	}
}
