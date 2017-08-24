package com.folcourse.model;

import java.util.*;
import java.sql.*;



public class FolcourseJDBCDAO implements FolcourseDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = "INSERT INTO folcourse (fcrs_no,fcrsp_acc,fcrs_date) VALUES (?, ?, ?)";
	private static final String GET_Folcourse_ByFcrsp_acc_STMT = "SELECT fcrs_no,fcrsp_acc,to_char(fcrs_date,'yyyy-mm-dd') fcrs_date FROM folcourse where fcrsp_acc = ? order by fcrs_no";
	private static final String DELETE = "DELETE FROM folcoach where fcrs_no = ? and where fcrsp_acc=?";
	


	@Override
	public void insert(FolcourseVO folcourseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folcourseVO.getFcrs_no());
			pstmt.setString(2, folcourseVO.getFcrsp_acc());
			pstmt.setDate(3, folcourseVO.getFcrs_date());

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
	public void delete(String fcrs_no, String fcrsp_acc) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fcrs_no);
			pstmt.setString(2, fcrsp_acc);

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
	public Set<FolcourseVO> getFolcourseByFcrsp_acc(String fcrsp_acc) {
		Set<FolcourseVO> set = new LinkedHashSet<FolcourseVO>();
		FolcourseVO folcourseVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Folcourse_ByFcrsp_acc_STMT);
			pstmt.setString(1, fcrsp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folcourseVO = new FolcourseVO();
				folcourseVO.setFcrs_no(rs.getString("fcrs_no"));
				folcourseVO.setFcrsp_acc(rs.getString("fcrsp_acc"));
				folcourseVO.setFcrs_date(rs.getDate("fcrs_date"));
				set.add(folcourseVO); // Store the row in the vector
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

		FolcourseJDBCDAO dao = new FolcourseJDBCDAO();

		 //新增
		FolcourseVO folcourseVO1 = new FolcourseVO();
		folcourseVO1.setFcrs_no("xx");
		folcourseVO1.setFcrsp_acc("yy");
		folcourseVO1.setFcrs_date(java.sql.Date.valueOf("2012-01-01"));
		dao.insert(folcourseVO1);


		//刪除
		dao.delete("3","5");

		
		
		// 查詢某學生的教練
		Set<FolcourseVO> set = dao.getFolcourseByFcrsp_acc("1");
		for (FolcourseVO aFolcourse : set) {
			System.out.print(aFolcourse.getFcrs_no() + ",");
			System.out.print(aFolcourse.getFcrsp_acc() + ",");
			System.out.print(aFolcourse.getFcrs_date());
			
		}
	}
}

