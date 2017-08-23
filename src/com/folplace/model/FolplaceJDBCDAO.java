package com.folplace.model;

import java.util.*;
import java.sql.*;



public class FolplaceJDBCDAO implements FolplaceDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CIA";
	String passwd = "12345";

	private static final String INSERT_STMT = "INSERT INTO folplace (fp_no,fpp_acc,fp_date) VALUES (?, ?, ?)";
	private static final String GET_Folplace_ByFpp_acc_STMT = "SELECT fp_no,fpp_acc,to_char(fp_date,'yyyy-mm-dd') fp_date FROM folplace where fpp_acc = ? order by fp_no";
	private static final String DELETE = "DELETE FROM folplace where fp_no = ? and fpp_acc=?";
	


	@Override
	public void insert(FolplaceVO folplaceVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, folplaceVO.getFp_no());
			pstmt.setString(2, folplaceVO.getFpp_acc());
			pstmt.setDate(3, folplaceVO.getFp_date());

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
	public void delete(String fp_no, String fpp_acc) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fp_no);
			pstmt.setString(2, fpp_acc);
		

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
	public Set<FolplaceVO> getFolplaceByFpp_acc(String fpp_acc) {
		Set<FolplaceVO> set = new LinkedHashSet<FolplaceVO>();
		FolplaceVO folplaceVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Folplace_ByFpp_acc_STMT);
			pstmt.setString(1, fpp_acc);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				folplaceVO = new FolplaceVO();
				folplaceVO.setFp_no(rs.getString("fp_no"));
				folplaceVO.setFpp_acc(rs.getString("fpp_acc"));
				folplaceVO.setFp_date(rs.getDate("fp_date"));
				set.add(folplaceVO); // Store the row in the vector
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

		FolplaceJDBCDAO dao = new FolplaceJDBCDAO();

		 //新增
		FolplaceVO folplaceVO1 = new FolplaceVO();
		folplaceVO1.setFp_no("xx");
		folplaceVO1.setFpp_acc("yy");
		folplaceVO1.setFp_date(java.sql.Date.valueOf("2012-01-01"));
		dao.insert(folplaceVO1);


		//刪除
		dao.delete("3","5");

		
		
		// 查詢某學生的教練
		Set<FolplaceVO> set = dao.getFolplaceByFpp_acc("1");
		for (FolplaceVO aFolplace : set) {
			System.out.print(aFolplace.getFp_no() + ",");
			System.out.print(aFolplace.getFpp_acc() + ",");
			System.out.print(aFolplace.getFp_date());
			
		}
	}
}

