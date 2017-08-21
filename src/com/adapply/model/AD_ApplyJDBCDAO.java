package com.adapply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;






public class AD_ApplyJDBCDAO implements AD_ApplyDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jdbc";
//	String userid = "JDBC";
	String passwd = "123";
	private static final String INSERT =
			"INSERT INTO AD_APPLY VALUES(AD_APPLY_PK_SEQ.NEXTVAL,?,0,?,?,?,?,?,?,?)";
	private static final String GET_ALL =
			"SELECT TO_NUMBER(AD_NO,'9999') AD_NO,MEM_NO,ARV_STAT,PAY_DATE,AD_NAME,AD_URL,AD_ONDATE,AD_OFFDATE,AD_CTX,AD_PT FROM AD_APPLY order by AD_NO"; 
	private static final String GET_ONE = 
			"SELECT AD_NO,MEM_NO,ARV_STAT,PAY_DATE,AD_NAME,AD_URL,AD_ONDATE,AD_OFFDATE,AD_CTX,AD_PT FROM AD_APPLY WHERE AD_NO=?"; 
	private static final String GET_STAT = 
			"SELECT TO_NUMBER(AD_NO,'9999') AD_NO,MEM_NO,ARV_STAT,PAY_DATE,AD_NAME,AD_URL,AD_ONDATE,AD_OFFDATE,AD_CTX,AD_PT FROM AD_APPLY WHERE ARV_STAT=?"; 
	private static final String DELETE = 
			"DELETE FROM AD_APPLY where AD_NO = ?";
	private static final String UPDATE = 
			"UPDATE AD_APPLY set AD_NAME=?,AD_URL=?,AD_ONDATE=?,AD_OFFDATE=?,AD_CTX=?,AD_PT=? where AD_NO = ?";
	private static final String UPDATE_STAT = 
			"UPDATE AD_APPLY set ARV_STAT=? where AD_NO = ?";
	@Override
	public void insert(AD_ApplyVO adApplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, adApplyVO.getMem_no());
			pstmt.setDate(2, adApplyVO.getPay_date());
			pstmt.setString(3, adApplyVO.getAd_name());
			pstmt.setString(4, adApplyVO.getAd_url());
			pstmt.setDate(5, adApplyVO.getAd_ondate());
			pstmt.setDate(6, adApplyVO.getAd_offdate());
			pstmt.setString(7, adApplyVO.getAd_ctx());
			pstmt.setBytes(8, adApplyVO.getAd_pt());
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
	public void update(AD_ApplyVO adApplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adApplyVO.getAd_name());
			pstmt.setString(2, adApplyVO.getAd_url());
			pstmt.setDate(3, adApplyVO.getAd_ondate());
			pstmt.setDate(4, adApplyVO.getAd_offdate());
			pstmt.setString(5, adApplyVO.getAd_ctx());
			pstmt.setBytes(6, adApplyVO.getAd_pt());
			pstmt.setString(7,adApplyVO.getAd_no());
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
	public void update_stat(AD_ApplyVO adApplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STAT);

			pstmt.setInt(1, adApplyVO.getArv_stat());
			pstmt.setString(2, adApplyVO.getAd_no());
			
			
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
	public void delete(String ad_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ad_no);

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
	public AD_ApplyVO findByPrimaryKey(String ad_no) {
		AD_ApplyVO ad_ApplyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, ad_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				ad_ApplyVO = new AD_ApplyVO();
				ad_ApplyVO.setAd_no(rs.getString("ad_no"));
				ad_ApplyVO.setMem_no(rs.getString("mem_no"));
				ad_ApplyVO.setArv_stat(rs.getInt("arv_stat"));
				ad_ApplyVO.setPay_date(rs.getDate("pay_date"));
				ad_ApplyVO.setAd_name(rs.getString("ad_name"));
				ad_ApplyVO.setAd_url(rs.getString("ad_url"));
				ad_ApplyVO.setAd_ondate(rs.getDate("ad_ondate"));
				ad_ApplyVO.setAd_offdate(rs.getDate("ad_offdate"));
				ad_ApplyVO.setAd_ctx(rs.getString("ad_ctx"));
				ad_ApplyVO.setAd_pt(rs.getBytes("ad_pt"));
				
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
		return ad_ApplyVO;
	}

	@Override
	public Set<AD_ApplyVO> getStat(Integer arv_stat) {
		Set<AD_ApplyVO> set = new LinkedHashSet<AD_ApplyVO>();
		AD_ApplyVO ad_ApplyVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STAT);
			pstmt.setInt(1, arv_stat);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				ad_ApplyVO = new AD_ApplyVO();
				ad_ApplyVO.setAd_no(rs.getString("ad_no"));
				ad_ApplyVO.setMem_no(rs.getString("mem_no"));
				ad_ApplyVO.setArv_stat(rs.getInt("arv_stat"));
				ad_ApplyVO.setPay_date(rs.getDate("pay_date"));
				ad_ApplyVO.setAd_name(rs.getString("ad_name"));
				ad_ApplyVO.setAd_url(rs.getString("ad_url"));
				ad_ApplyVO.setAd_ondate(rs.getDate("ad_ondate"));
				ad_ApplyVO.setAd_offdate(rs.getDate("ad_offdate"));
				ad_ApplyVO.setAd_ctx(rs.getString("ad_ctx"));
				ad_ApplyVO.setAd_pt(rs.getBytes("ad_pt"));
				set.add(ad_ApplyVO); // Store the row in the vector
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

	@Override
	public List<AD_ApplyVO> getAll() {
		List<AD_ApplyVO> list = new ArrayList<AD_ApplyVO>();
		AD_ApplyVO ad_ApplyVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 銋迂� Domain objects
				ad_ApplyVO = new AD_ApplyVO();
				ad_ApplyVO.setAd_no(rs.getString("ad_no"));
				ad_ApplyVO.setMem_no(rs.getString("mem_no"));
				ad_ApplyVO.setArv_stat(rs.getInt("arv_stat"));
				ad_ApplyVO.setPay_date(rs.getDate("pay_date"));
				ad_ApplyVO.setAd_name(rs.getString("ad_name"));
				ad_ApplyVO.setAd_url(rs.getString("ad_url"));
				ad_ApplyVO.setAd_ondate(rs.getDate("ad_ondate"));
				ad_ApplyVO.setAd_offdate(rs.getDate("ad_offdate"));
				ad_ApplyVO.setAd_ctx(rs.getString("ad_ctx"));
				ad_ApplyVO.setAd_pt(rs.getBytes("ad_pt"));
				list.add(ad_ApplyVO); // Store the row in the list
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
		AD_ApplyJDBCDAO dao = new AD_ApplyJDBCDAO();

		// 新增
//		AD_ApplyVO ad_ApplyVO1 = new AD_ApplyVO();
//		ad_ApplyVO1.setMem_no("8");
//		ad_ApplyVO1.setPay_date(null);
//		ad_ApplyVO1.setAd_name("萵苣");
//		ad_ApplyVO1.setAd_url("yahoo.com");
//		ad_ApplyVO1.setAd_ondate(null);
//		ad_ApplyVO1.setAd_offdate(null);
//		ad_ApplyVO1.setAd_ctx("萵苣休閒館");
//		ad_ApplyVO1.setAd_pt(null);
//		dao.insert(ad_ApplyVO1);
		
//		// 刪除	
//		dao.delete("7");
		// 修改
		AD_ApplyVO ad_ApplyVO2 = new AD_ApplyVO();
		ad_ApplyVO2.setAd_name("苣萵");
		ad_ApplyVO2.setAd_url("google.com");
		ad_ApplyVO2.setAd_ondate(null);
		ad_ApplyVO2.setAd_offdate(null);
		ad_ApplyVO2.setAd_ctx("苣萵苣萵館");
		ad_ApplyVO2.setAd_pt(null);
		ad_ApplyVO2.setAd_no("8");
		dao.update(ad_ApplyVO2);
		// 修改
		AD_ApplyVO ad_ApplyVO4 = new AD_ApplyVO();
		ad_ApplyVO4.setArv_stat(1);;
		ad_ApplyVO4.setAd_no("1");;
		dao.update_stat(ad_ApplyVO4);
		
		// 查詢
		AD_ApplyVO ad_ApplyVO3 = dao.findByPrimaryKey("2");
		System.out.print(ad_ApplyVO3.getAd_no() + ",");
		System.out.print(ad_ApplyVO3.getMem_no()+ ",");
		System.out.print(ad_ApplyVO3.getArv_stat() + ",");
		System.out.print(ad_ApplyVO3.getPay_date()+",");
		System.out.print(ad_ApplyVO3.getAd_name()+",");
		System.out.print(ad_ApplyVO3.getAd_url()+",");
		System.out.print(ad_ApplyVO3.getAd_ondate()+",");
		System.out.print(ad_ApplyVO3.getAd_offdate()+",");
		System.out.print(ad_ApplyVO3.getAd_ctx()+",");
		System.out.println(ad_ApplyVO3.getAd_pt());
		System.out.println("---------------------");
		
		// 查詢stat
		Set<AD_ApplyVO> set = dao.getStat(new Integer(1));
		for (AD_ApplyVO aStat : set) {
			System.out.print(aStat.getAd_no() + ",");
			System.out.print(aStat.getMem_no()+ ",");
			System.out.print(aStat.getArv_stat() + ",");
			System.out.print(aStat.getPay_date()+",");
			System.out.print(aStat.getAd_name()+",");
			System.out.print(aStat.getAd_url()+",");
			System.out.print(aStat.getAd_ondate()+",");
			System.out.print(aStat.getAd_offdate()+",");
			System.out.print(aStat.getAd_ctx()+",");
			System.out.println(aStat.getAd_pt());
			System.out.println();
		}
		System.out.println("---------------------");
		// 查詢
		List<AD_ApplyVO> list = dao.getAll();
		for (AD_ApplyVO aAD : list) {
			System.out.print(aAD.getAd_no() + ",");
			System.out.print(aAD.getMem_no()+ ",");
			System.out.print(aAD.getArv_stat() + ",");
			System.out.print(aAD.getPay_date()+",");
			System.out.print(aAD.getAd_name()+",");
			System.out.print(aAD.getAd_url()+",");
			System.out.print(aAD.getAd_ondate()+",");
			System.out.print(aAD.getAd_offdate()+",");
			System.out.print(aAD.getAd_ctx()+",");
			System.out.println(aAD.getAd_pt());
			System.out.println();
		}
	}

	

}
