package com.membersreport.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



public class MembersReportJDBCDAO implements MembersReportDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jdbc";
//	String userid = "JDBC";
	String passwd = "123";
	
	private static final String INSERT_MR =
			"INSERT INTO MEMBERS_REPORT VALUES(MEMBERS_REPORT_PK_SEQ.NEXTVAL,?,?,?,SYSDATE,DEFAULT,?,?)";
	private static final String GET_ALL =
			"SELECT TO_NUMBER(MR_NO,'99999') MR_NO,MR_DEF,MR_PRO,MR_CTX,MR_TIME,MR_STAT,REF_CTX,MR_PT FROM MEMBERS_REPORT order by MR_NO"; 
	private static final String GET_ONE_MR = 
			"SELECT MR_NO,MR_DEF,MR_PRO,MR_CTX,MR_TIME,MR_STAT,REF_CTX,MR_PT FROM MEMBERS_REPORT where Mr_NO = ?";
	private static final String GET_STAT_MR = 
			"SELECT TO_NUMBER(MR_NO,'99999') MR_NO,MR_DEF,MR_PRO,MR_CTX,MR_TIME,MR_STAT,REF_CTX,MR_PT FROM MEMBERS_REPORT where Mr_STAT = ?";
	
	private static final String DELETE = 
			"DELETE FROM MEMBERS_REPORT where MR_NO = ?";
	
	private static final String UPDATE = 
			"UPDATE MEMBERS_REPORT set mr_stat=1 where Mr_NO = ?";

	
	private static final String UPDATE_MEM_MR_NUM=
			"UPDATE MEMBERS SET MR_NUM=MR_NUM+1 WHERE MEM_NO=(select mr_def from members_report where mr_no=?)";
	@Override
	public void insert(MembersReportVO membersReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_MR);

			pstmt.setString(1, membersReportVO.getMr_def());
			pstmt.setString(2, membersReportVO.getMr_pro());
			pstmt.setString(3, membersReportVO.getMr_ctx());
			pstmt.setString(4, membersReportVO.getRef_ctx());
			pstmt.setBytes(5, membersReportVO.getMr_pt());

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
	public void update(MembersReportVO membersReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, membersReportVO.getMr_no());
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
	public void updateMRNum(MembersReportVO membersReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_MEM_MR_NUM);

			pstmt.setString(1, membersReportVO.getMr_no());
			pstmt.executeUpdate();

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
	public void delete(String mr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mr_no);

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
	public MembersReportVO findByPrimaryKey(String mr_no) {

		MembersReportVO membersReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MR);

			pstmt.setString(1, mr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				membersReportVO = new MembersReportVO();
				membersReportVO.setMr_no(rs.getString("mr_no"));
				membersReportVO.setMr_def(rs.getString("mr_def"));
				membersReportVO.setMr_pro(rs.getString("mr_pro"));
				membersReportVO.setMr_ctx(rs.getString("mr_ctx"));
				membersReportVO.setMr_time(rs.getDate("mr_time"));
				membersReportVO.setMr_stat(rs.getInt("mr_stat"));
				membersReportVO.setRef_ctx(rs.getString("ref_ctx"));
				membersReportVO.setMr_pt(rs.getBytes("mr_pt"));
				
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
		return membersReportVO;
	}

	@Override
	public List<MembersReportVO> getAll() {
		List<MembersReportVO> list = new ArrayList<MembersReportVO>();
		MembersReportVO membersReportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				membersReportVO = new MembersReportVO();
				membersReportVO.setMr_no(rs.getString("mr_no"));
				membersReportVO.setMr_def(rs.getString("mr_def"));
				membersReportVO.setMr_pro(rs.getString("mr_pro"));
				membersReportVO.setMr_ctx(rs.getString("mr_ctx"));
				membersReportVO.setMr_time(rs.getDate("mr_time"));
				membersReportVO.setMr_stat(rs.getInt("mr_stat"));
				membersReportVO.setRef_ctx(rs.getString("ref_ctx"));
				membersReportVO.setMr_pt(rs.getBytes("mr_pt"));
				list.add(membersReportVO); // Store the row in the list
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
	@Override
	public Set<MembersReportVO> getStat(Integer mr_stat) {
		Set<MembersReportVO> set = new LinkedHashSet<MembersReportVO>();
		MembersReportVO membersReportVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STAT_MR);
			pstmt.setInt(1, mr_stat);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				membersReportVO = new MembersReportVO();
				membersReportVO.setMr_no(rs.getString("mr_no"));
				membersReportVO.setMr_def(rs.getString("mr_def"));
				membersReportVO.setMr_pro(rs.getString("mr_pro"));
				membersReportVO.setMr_ctx(rs.getString("mr_ctx"));
				membersReportVO.setMr_time(rs.getDate("mr_time"));
				membersReportVO.setMr_stat(rs.getInt("mr_stat"));
				membersReportVO.setRef_ctx(rs.getString("ref_ctx"));
				membersReportVO.setMr_pt(rs.getBytes("mr_pt"));
				set.add(membersReportVO); // Store the row in the vector
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
		MembersReportJDBCDAO dao = new MembersReportJDBCDAO();

		// 新增
//		MembersReportVO membersReportVO1 = new MembersReportVO();
//		membersReportVO1.setMr_def("4");
//		membersReportVO1.setMr_pro("5");
//		membersReportVO1.setMr_ctx("謾罵");
//		membersReportVO1.setMr_pt(null);
//		dao.insert(membersReportVO1);
		// 修改
//		MembersReportVO membersReportVO2 = new MembersReportVO();
//		membersReportVO2.setMr_no("1");
//		dao.update(membersReportVO2);
//		
//		// 刪除
//		dao.delete("2");
//				
		// 查詢 one
		MembersReportVO membersReportVO3 = dao.findByPrimaryKey("1");
		System.out.print(membersReportVO3.getMr_no() + ",");
		System.out.print(membersReportVO3.getMr_def() + ",");
		System.out.print(membersReportVO3.getMr_pro() + ",");
		System.out.print(membersReportVO3.getMr_ctx() + ",");
		System.out.print(membersReportVO3.getMr_time() + ",");
		System.out.print(membersReportVO3.getMr_stat() + ",");
		System.out.println(membersReportVO3.getMr_pt());
		System.out.println("---------------------");

		// 查詢
		List<MembersReportVO> list = dao.getAll();
		for (MembersReportVO aMembersReport : list) {
			System.out.print(aMembersReport.getMr_no() + ",");
			System.out.print(aMembersReport.getMr_def() + ",");
			System.out.print(aMembersReport.getMr_pro() + ",");
			System.out.print(aMembersReport.getMr_ctx() + ",");
			System.out.print(aMembersReport.getMr_time() + ",");
			System.out.print(aMembersReport.getMr_stat() + ",");
			System.out.print(aMembersReport.getMr_pt());
			System.out.println();
		}
		System.out.println("---------------------");

		Set<MembersReportVO> set = dao.getStat(new Integer(1));
		for (MembersReportVO aSet : set) {
			System.out.print(aSet.getMr_no() + ",");
			System.out.print(aSet.getMr_def() + ",");
			System.out.print(aSet.getMr_pro() + ",");
			System.out.print(aSet.getMr_ctx() + ",");
			System.out.print(aSet.getMr_time() + ",");
			System.out.print(aSet.getMr_stat() + ",");
			System.out.print(aSet.getMr_pt());
			System.out.println();
		}
		
		
	}


	

	

}
