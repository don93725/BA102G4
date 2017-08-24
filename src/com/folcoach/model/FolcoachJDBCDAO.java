package com.folcoach.model;

import java.util.*;
import java.sql.*;


public class FolcoachJDBCDAO implements FolcoachDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = "INSERT INTO folcoach (fc_acc,fcp_acc,fc_date) VALUES (?, ?, ?)";
	private static final String GET_Folcoach_ByFcp_acc_STMT = "SELECT fc_acc,fcp_acc,to_char(fc_date,'yyyy-mm-dd') fc_date FROM folcoach where fcp_acc = ? order by fc_acc";
	private static final String DELETE = "DELETE FROM folcoach where fc_acc = ? and where fcp_acc= ?";
	


	@Override
	public void insert(FolcoachVO folcoachVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folcoachVO.getFc_acc());
			pstmt.setString(2, folcoachVO.getFcp_acc());
			pstmt.setDate(3, folcoachVO.getFc_date());

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
	public void delete(String fc_acc, String fcp_acc) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fc_acc);
			pstmt.setString(2, fcp_acc);
		

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
	public Set<FolcoachVO> getFolcoachByFcp_acc(String fcp_acc) {
		Set<FolcoachVO> set = new LinkedHashSet<FolcoachVO>();
		FolcoachVO folcoachVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Folcoach_ByFcp_acc_STMT);
			pstmt.setString(1, fcp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folcoachVO = new FolcoachVO();
				folcoachVO.setFc_acc(rs.getString("fc_acc"));
				folcoachVO.setFcp_acc(rs.getString("fcp_acc"));
				folcoachVO.setFc_date(rs.getDate("fc_date"));
				set.add(folcoachVO); // Store the row in the vector
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

		FolcoachJDBCDAO dao = new FolcoachJDBCDAO();

		 //新增
		FolcoachVO folcoachVO1 = new FolcoachVO();
		folcoachVO1.setFc_acc("xx");
		folcoachVO1.setFcp_acc("yy");
		folcoachVO1.setFc_date(java.sql.Date.valueOf("2012-01-01"));
		dao.insert(folcoachVO1);


		//刪除
		dao.delete("3","5");

		
		
		// 查詢某學生的教練
		Set<FolcoachVO> set = dao.getFolcoachByFcp_acc("1");
		for (FolcoachVO aFolcoach : set) {
			System.out.print(aFolcoach.getFc_acc() + ",");
			System.out.print(aFolcoach.getFcp_acc() + ",");
			System.out.print(aFolcoach.getFc_date());
			
		}
	}
}
