package com.function.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.members.model.MembersVO;


public class FunctionJDBCDAO implements FunctionDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "jdbc";
	String userid = "JDBC";
	String passwd = "123";

	private static final String INSERT_FUN = "INSERT INTO FUNCTION VALUES(?,?)";
	
	private static final String GET_ALL_FUN = "SELECT F_NO, FNAME FROM FUNCTION order by F_NO";
	private static final String GET_ONE_FUN = "SELECT F_NO, FNAME FROM FUNCTION WHERE F_NO=?";

	private static final String DELETE_FUN = "DELETE FUNCTION where F_NO = ?";

	private static final String UPDATE_FUN = "UPDATE FUNCTION set F_NO=?, FNAME=? where F_NO=?";
	
	@Override
	public void insert(FunctionVO functionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_FUN);
			pstmt.setString(1, functionVO.getF_no());
			pstmt.setString(2, functionVO.getFname());

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
	public void update(FunctionVO functionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_FUN);
			
			pstmt.setString(1, functionVO.getF_no());
			pstmt.setString(2, functionVO.getFname());
			pstmt.setString(3, functionVO.getF_no());
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
	public void delete(String f_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_FUN);

			pstmt.setString(1, f_no);

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
	public FunctionVO findByPrimaryKey(String f_no) {

		FunctionVO functionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FUN);

			pstmt.setString(1, f_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				functionVO = new FunctionVO();
				functionVO.setF_no(rs.getString("f_no"));
				functionVO.setFname(rs.getString("fname"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return functionVO;
	}

	@Override
	public List<FunctionVO> getAll() {
		List<FunctionVO> list = new ArrayList<FunctionVO>();
		FunctionVO functionVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_FUN);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				functionVO = new FunctionVO();
				functionVO.setF_no(rs.getString("f_no"));
				functionVO.setFname(rs.getString("fname"));
				list.add(functionVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
		FunctionJDBCDAO dao = new FunctionJDBCDAO();

		// 新增
//		FunctionVO functionVO1 = new FunctionVO();
//		functionVO1.setF_no("TA");
//		functionVO1.setFname("測試管理權限01");	
//		dao.insert(functionVO1);		
		
		// 修改
//		FunctionVO functionVO2 = new FunctionVO();
//		functionVO2.setF_no("TS");
//		functionVO2.setFname("EEE_Test");
//		dao.update(functionVO2);

		// 刪除
		dao.delete("TS");
		
		// 查詢
		FunctionVO functionVO3 = dao.findByPrimaryKey("M");
		System.out.print(functionVO3.getF_no() + ",");
		System.out.println(functionVO3.getFname());
		System.out.println("---------------------");

		// 查詢
		List<FunctionVO> list = dao.getAll();
		for (FunctionVO aFunction : list) {
			System.out.print(aFunction.getF_no() + ",");
			System.out.print(aFunction.getFname());
			System.out.println();
		}
	}

}
