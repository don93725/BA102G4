package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.function.model.FunctionVO;
import com.manager.model.ManagerVO;

public class AuthorityJDBCDAO implements AuthorityDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	// String userid = "jdbc";
	String userid = "JDBC";
	String passwd = "123";

	
	private static final String INSERT_AUT = "INSERT INTO AUTHORITY VALUES(?,?)";
	
	private static final String GET_MGR = "SELECT MGR_NO from manager where  mgr_id= ?";
	private static final String GET_ALL_AUT = "SELECT MGR_NO, F_NO FROM AUTHORITY order by MGR_NO";
	private static final String GET_ONE_AUT = "SELECT MGR_NO, F_NO FROM AUTHORITY WHERE MGR_NO=? AND F_NO=?";

	private static final String GET_MGR_FUNCTION = "SELECT  F_NO FROM AUTHORITY WHERE MGR_NO=? ";

	private static final String DELETE_AUT = "DELETE AUTHORITY where MGR_NO = ?";

	@Override
	public void insert(AuthorityVO authorityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_AUT);
			pstmt.setString(1, authorityVO.getMgr_no());
			pstmt.setString(2, authorityVO.getF_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String mgr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_AUT);

			pstmt.setString(1, mgr_no);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ManagerVO findByMgrNo(String mgr_id) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MGR);

			pstmt.setString(1, mgr_id);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setMgr_no(rs.getString("mgr_no"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return managerVO;
	}

	@Override
	public AuthorityVO findByPrimaryKey(String mgr_no, String f_no) {
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_AUT);

			pstmt.setString(1, mgr_no);
			pstmt.setString(2, f_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				authorityVO = new AuthorityVO();
				authorityVO.setMgr_no(rs.getString("mgr_no"));
				authorityVO.setF_no(rs.getString("f_no"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return authorityVO;
	}

	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_AUT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				authorityVO = new AuthorityVO();
				authorityVO.setMgr_no(rs.getString("mgr_no"));
				authorityVO.setF_no(rs.getString("f_no"));
				list.add(authorityVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();

//		// 新增
//		AuthorityVO authoriytVO1 = new AuthorityVO();
//		authoriytVO1.setMgr_no("2");
//		authoriytVO1.setF_no("M");	
//		dao.insert(authoriytVO1);
//		
//		// 刪除
//		dao.delete("2");
		// 查詢
		AuthorityVO authorityVO3 = dao.findByPrimaryKey("1", "M");
		System.out.print(authorityVO3.getMgr_no() + ",");
		System.out.println(authorityVO3.getF_no());
		System.out.println("---------------------");

		// 查詢
		List<AuthorityVO> list = dao.getAll();
		for (AuthorityVO aAuthority : list) {
			System.out.print(aAuthority.getMgr_no() + ",");
			System.out.print(aAuthority.getF_no());
			System.out.println();
		}

	}

	@Override
	public Set<AuthorityVO> getFun(String mgr_no) {
		Set<AuthorityVO> set = new LinkedHashSet<AuthorityVO>();
		AuthorityVO authorityVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MGR_FUNCTION);
			pstmt.setString(1, mgr_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				authorityVO = new AuthorityVO();
				authorityVO.setF_no(rs.getString("f_no"));
				
				set.add(authorityVO); // Store the row in the vector
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

	

}
