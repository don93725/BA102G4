package com.manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.members.model.MembersVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Manager;

public class ManagerDAO implements ManagerDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_MEM = "INSERT INTO MEMBERS VALUES(mem_no_seq.NEXTVAL,?,?,?,DEFAULT)";
	private static final String INSERT_MGR = "INSERT INTO MANAGER VALUES('MGR' || lpad(MANAGER_PK_SEQ.NEXTVAL,3,'0'),?,?,?,?,?,?,?,?,?)";
//	private static final String GET_MEM_NO_SEQ = "mem_no_seq.NEXTVAL from dual";
	
	private static final String GET_ALL_MGR = "SELECT  MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC,MGR_INT FROM MANAGER order by MGR_NO";
	private static final String GET_ONE_MGR = "SELECT MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC,MGR_INT FROM MANAGER WHERE MGR_NO=?";
	private static final String GET_ONE_MGR2 = "SELECT MGR_NO,a.MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC,mem_nickname,MGR_INT FROM MANAGER a join ( select mem_no,mem_nickname from members) b  on a.mem_no=b.mem_no WHERE MGR_NO=?";

	
	private static final String GET_MGR_GOB = "SELECT  MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC,MGR_INT FROM MANAGER WHERE MGR_JOB=? order by MGR_NO";	
	private static final String GET_MGR_STATUS = "SELECT MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC FROM MANAGER WHERE MGR_STATUS=? order by MGR_NO";	
	
	private static final String DELETE_MGR = "DELETE FROM MANAGER where MGR_NO = ?";
	private static final String DELETE_MEM = "DELETE FROM MEMBERS WHERE MEM_NO = ( SELECT MEM_NO FROM MANAGER WHERE MGR_NO = ? )";

	private static final String UPDATE_MGR = "UPDATE MANAGER set MGR_PWD=?, MGR_JOB=? ,MGR_NAME=? ,MGR_EMAIL=?,MGR_STATUS=?,MGR_PIC=?,MGR_INT=? where MGR_NO = ?";
	private static final String UPDATE_MGR2 = "UPDATE MANAGER set  MGR_PWD=?, MGR_JOB=? ,MGR_NAME=? ,MGR_EMAIL=?,MGR_STATUS=?,MGR_INT=? where MGR_NO = ?";
	private static final String UPDATE_MEM = "UPDATE MEMBERS SET MEM_NICKNAME=?   where MEM_NO=(SELECT MEM_NO FROM MANAGER WHERE MGR_NO =?)";
	
	private static final String LOGIN_MGR = "Select * from manager where mgr_id = ? and mgr_pwd = ?";
	private static final String LOGIN_MGR_TEST = "Select * from manager where mgr_id = ? and mgr_pwd = ? ";
//	private static final String LOGIN_MGR_STATUS = "Select * from manager where mgr_id = ? ";
	
	@Override
	public void insert(ManagerVO managerVO, MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			//先新增MEM
			String[] col = {"MEM_NO"}; 
			pstmt = con.prepareStatement(INSERT_MEM,col);
			pstmt.setString(1, managerVO.getMgr_id());
			pstmt.setString(2,"3");
			pstmt.setString(3, membersVO.getMem_nickname());
			pstmt.executeUpdate();
			
			//抓MEM_NO
			String mem_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				mem_no=rs.getString(1);
				System.out.println("自增主鍵值= " + mem_no +"(剛新增成功的部門編號)");
			}else{
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			//再新增MGR
			
			pstmt = con.prepareStatement(INSERT_MGR);
			pstmt.setString(1,mem_no);
			pstmt.setString(2, managerVO.getMgr_id());
			pstmt.setString(3, managerVO.getMgr_pwd());
			pstmt.setInt(4, managerVO.getMgr_job());
			pstmt.setString(5, managerVO.getMgr_name());
			pstmt.setString(6, managerVO.getMgr_email());
			pstmt.setInt(7, managerVO.getMgr_status());
			pstmt.setBytes(8, managerVO.getMgr_pic());
			pstmt.setString(9, managerVO.getMgr_int());
			
			
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);

			
			// Handle any driver errors
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
	public void update(ManagerVO managerVO,MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			con.setAutoCommit(false);
			//先改MEM
			pstmt = con.prepareStatement(UPDATE_MEM);
			pstmt.setString(1,membersVO.getMem_nickname());
			pstmt.setString(2, managerVO.getMgr_no());
			pstmt.executeUpdate();
			//再改MGR
			pstmt = con.prepareStatement(UPDATE_MGR);
			if(managerVO.getMgr_pic()==null)
				pstmt = con.prepareStatement(UPDATE_MGR2);
			pstmt.setString(1, managerVO.getMgr_pwd());
			pstmt.setInt(2, managerVO.getMgr_job());
			pstmt.setString(3, managerVO.getMgr_name());
			pstmt.setString(4, managerVO.getMgr_email());
			pstmt.setInt(5, managerVO.getMgr_status());
			
			if(managerVO.getMgr_pic()!=null){
			
				pstmt.setBytes(6, managerVO.getMgr_pic());
				pstmt.setString(7, managerVO.getMgr_int());
				pstmt.setString(8, managerVO.getMgr_no());
				
			}else{
				
				pstmt.setString(6, managerVO.getMgr_int());
				pstmt.setString(7, managerVO.getMgr_no());
			}
			
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_MGR);
			pstmt.setString(1, mgr_no);
			pstmt.executeUpdate();
			// 再刪除mem_no
			pstmt = con.prepareStatement(DELETE_MEM);
			pstmt.setString(1, mgr_no);
			pstmt.executeUpdate();
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			
			// Handle any driver errors
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public ManagerVO findByPrimaryKey(String mgr_no) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MGR);

			pstmt.setString(1, mgr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setMgr_no(rs.getString("mgr_no"));
				managerVO.setMem_no(rs.getString("mem_no"));
				managerVO.setMgr_id(rs.getString("mgr_id"));
				managerVO.setMgr_pwd(rs.getString("mgr_pwd"));
				managerVO.setMgr_job(rs.getInt("mgr_job"));
				managerVO.setMgr_name(rs.getString("mgr_name"));
				managerVO.setMgr_email(rs.getString("mgr_email"));
				managerVO.setMgr_status(rs.getInt("mgr_status"));
				managerVO.setMgr_pic(rs.getBytes("mgr_pic"));
				managerVO.setMgr_int(rs.getString("mgr_int"));

			}

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
	public List<ManagerVO> getAll() {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MGR);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setMgr_no(rs.getString("mgr_no"));
				managerVO.setMem_no(rs.getString("mem_no"));
				managerVO.setMgr_id(rs.getString("mgr_id"));
				managerVO.setMgr_pwd(rs.getString("mgr_pwd"));
				managerVO.setMgr_job(rs.getInt("mgr_job"));
				managerVO.setMgr_name(rs.getString("mgr_name"));
				managerVO.setMgr_email(rs.getString("mgr_email"));
				managerVO.setMgr_status(rs.getInt("mgr_status"));
				managerVO.setMgr_pic(rs.getBytes("mgr_pic"));
				managerVO.setMgr_int(rs.getString("mgr_int"));
				list.add(managerVO); // Store the row in the list
			}
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

	@Override
	public Set<ManagerVO> getMgrByJob(Integer mgr_job) {
		Set<ManagerVO> set = new LinkedHashSet<ManagerVO>();
		ManagerVO managerVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MGR_GOB);
			pstmt.setInt(1, mgr_job);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setMgr_no(rs.getString("mgr_no"));
				managerVO.setMem_no(rs.getString("mem_no"));
				managerVO.setMgr_id(rs.getString("mgr_id"));
				managerVO.setMgr_pwd(rs.getString("mgr_pwd"));
				managerVO.setMgr_job(rs.getInt("mgr_job"));
				managerVO.setMgr_name(rs.getString("mgr_name"));
				managerVO.setMgr_email(rs.getString("mgr_email"));
				managerVO.setMgr_status(rs.getInt("mgr_status"));
				managerVO.setMgr_pic(rs.getBytes("mgr_pic"));
				managerVO.setMgr_int(rs.getString("mgr_int"));
				set.add(managerVO); // Store the row in the vector
			}
	
			// Handle any driver errors
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
	public Set<ManagerVO> getMgrByStatus(Integer mgr_status) {
		Set<ManagerVO> set = new LinkedHashSet<ManagerVO>();
		ManagerVO managerVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MGR_STATUS);
			pstmt.setInt(1, mgr_status);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setMgr_no(rs.getString("mgr_no"));
				managerVO.setMem_no(rs.getString("mem_no"));
				managerVO.setMgr_id(rs.getString("mgr_id"));
				managerVO.setMgr_pwd(rs.getString("mgr_pwd"));
				managerVO.setMgr_job(rs.getInt("mgr_job"));
				managerVO.setMgr_name(rs.getString("mgr_name"));
				managerVO.setMgr_email(rs.getString("mgr_email"));
				managerVO.setMgr_status(rs.getInt("mgr_status"));
				managerVO.setMgr_pic(rs.getBytes("mgr_pic"));
				managerVO.setMgr_int(rs.getString("mgr_int"));
				set.add(managerVO); // Store the row in the vector
			}
	
			// Handle any driver errors
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
	public boolean login(String mgr_account, String mgr_password) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOGIN_MGR);
			pstmt.setString(1, mgr_account);
			pstmt.setString(2, mgr_password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {	
				return true;
			}else {
				return false;
			}

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
	
	}

	@Override
	public ManagerVO loginTest(String mgr_account, String mgr_password) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOGIN_MGR_TEST);
			pstmt.setString(1, mgr_account);
			pstmt.setString(2, mgr_password);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// membersVo 也稱為 Domain objects
				managerVO = new ManagerVO();
				managerVO.setMgr_no(rs.getString("mgr_no"));
				managerVO.setMem_no(rs.getString("mem_no"));
				managerVO.setMgr_id(rs.getString("mgr_id"));
				managerVO.setMgr_pwd(rs.getString("mgr_pwd"));
				managerVO.setMgr_job(rs.getInt("mgr_job"));
				managerVO.setMgr_name(rs.getString("mgr_name"));
				managerVO.setMgr_email(rs.getString("mgr_email"));
				managerVO.setMgr_status(rs.getInt("mgr_status"));
				managerVO.setMgr_pic(rs.getBytes("mgr_pic"));
				managerVO.setMgr_int(rs.getString("mgr_int"));

			}

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
		return managerVO;
	}

	@Override
	public List<ManagerVO> getAll(Map<String, String[]> map) {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from manager "
		          + jdbcUtil_CompositeQuery_Manager.get_WhereCondition(map)
		          + "order by mgr_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setMgr_no(rs.getString("mgr_no"));
				managerVO.setMem_no(rs.getString("mem_no"));
				managerVO.setMgr_id(rs.getString("mgr_id"));
				managerVO.setMgr_pwd(rs.getString("mgr_pwd"));
				managerVO.setMgr_job(rs.getInt("mgr_job"));
				managerVO.setMgr_name(rs.getString("mgr_name"));
				managerVO.setMgr_email(rs.getString("mgr_email"));
				managerVO.setMgr_status(rs.getInt("mgr_status"));
				managerVO.setMgr_pic(rs.getBytes("mgr_pic"));
				managerVO.setMgr_int(rs.getString("mgr_int"));
				list.add(managerVO); // Store the row in the List
			}
	
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
		return list;
	}

//	@Override
//	public boolean loginMgrSta(String mgr_account) {
//		// TODO Auto-generated method stub
//		return false;
//	}


}
