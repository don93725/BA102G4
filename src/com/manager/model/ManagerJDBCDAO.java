package com.manager.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.members.model.MembersVO;

public class ManagerJDBCDAO implements ManagerDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jdbc";
//	 String userid = "JDBC";
	String passwd = "123";

	private static final String INSERT_MEM = "INSERT INTO MEMBERS VALUES(mem_no_seq.NEXTVAL,?,?,?,DEFAULT)";
	private static final String INSERT_MGR = "INSERT INTO MANAGER VALUES('MGR' || lpad(MANAGER_PK_SEQ.NEXTVAL,3,'0'),?,?,?,?,?,?,?,?)";
//	private static final String GET_MEM_NO_SEQ = "members_pk_seq.nextval from dual";
	
	private static final String GET_ALL_MGR = "SELECT TO_NUMBER(MGR_NO,'9999') MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC FROM MANAGER order by MGR_NO";
	private static final String GET_ONE_MGR = "SELECT MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC FROM MANAGER WHERE MGR_NO=?";

	private static final String GET_MGR_GOB = "SELECT TO_NUMBER(MGR_NO,'9999')    MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC FROM MANAGER WHERE MGR_JOB=? order by MGR_NO";	
	private static final String GET_MGR_STATUS = "SELECT TO_NUMBER(MGR_NO,'9999') MGR_NO,MEM_NO,MGR_ID,MGR_PWD,MGR_JOB,MGR_NAME,MGR_EMAIL,MGR_STATUS,MGR_PIC FROM MANAGER WHERE MGR_STATUS=? order by MGR_NO";	
	
	private static final String DELETE_MGR = "DELETE FROM MANAGER where MGR_NO = ?";
	private static final String DELETE_MEM = "DELETE FROM MEMBERS WHERE MEM_NO = ( SELECT MEM_NO FROM MANAGER WHERE MGR_NO = ? )";

	private static final String UPDATE_MGR = "UPDATE MANAGER set  MGR_PWD=?, MGR_JOB=? ,MGR_NAME=? ,MGR_EMAIL=?,MGR_STATUS=?,MGR_PIC=? where MGR_NO = ?";
	private static final String UPDATE_MEM = "UPDATE MEMBERS SET  MEM_NICKNAME=?   where MEM_NO=(SELECT MEM_NO FROM MANAGER WHERE MGR_NO =?)";
	
	private static final String LOGIN_MGR = "Select * from manager where mgr_id = ? and mgr_pwd = ?";
	
	@Override
	public void insert(ManagerVO managerVO,MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			con.setAutoCommit(false);
			//先新增MEM
			String[] col = {"MEM_NO"}; 
			pstmt = con.prepareStatement(INSERT_MEM,col);
			pstmt.setString(1, managerVO.getMgr_id());
			pstmt.setString(2,"3");
			pstmt.setString(3, membersVO.getMem_nickname());
			pstmt.executeUpdate();
			
			//抓MEM_NO自增主鍵值
			String mem_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				mem_no=rs.getString(1);
				System.out.println("自增主鍵值= " + mem_no +"(剛新增成功的部門編號)");
			} else {
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
			
			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);

			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(ManagerVO managerVO,MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);
			//先改MEM
			pstmt = con.prepareStatement(UPDATE_MEM);
			
			pstmt.setString(1,membersVO.getMem_nickname());
			pstmt.setString(2, managerVO.getMgr_no());
			pstmt.executeUpdate();
			//再改MGR
			pstmt = con.prepareStatement(UPDATE_MGR);
			
			pstmt.setString(1, managerVO.getMgr_pwd());
			pstmt.setInt(2, managerVO.getMgr_job());
			pstmt.setString(3, managerVO.getMgr_name());
			pstmt.setString(4, managerVO.getMgr_email());
			pstmt.setInt(5, managerVO.getMgr_status());
			pstmt.setBytes(6, managerVO.getMgr_pic());
			pstmt.setString(7, managerVO.getMgr_no());
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<ManagerVO> getAll() {
		List<ManagerVO> list = new ArrayList<ManagerVO>();
		ManagerVO managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(managerVO); // Store the row in the list
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

	@Override
	public Set<ManagerVO> getMgrByJob(Integer mgr_job) {
		Set<ManagerVO> set = new LinkedHashSet<ManagerVO>();
		ManagerVO managerVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				set.add(managerVO); // Store the row in the vector
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
	public Set<ManagerVO> getMgrByStatus(Integer mgr_status) {
		Set<ManagerVO> set = new LinkedHashSet<ManagerVO>();
		ManagerVO managerVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				set.add(managerVO); // Store the row in the vector
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

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	@Override
	public boolean login(String mgr_account, String mgr_password) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(LOGIN_MGR);
			pstmt.setString(1, mgr_account);
			pstmt.setString(2, mgr_password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {	
				return true;
			}else {
				return false;
			}

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
	
	}

	public static void main(String[] args) throws IOException {

		ManagerJDBCDAO dao = new ManagerJDBCDAO();
		
		// 新增
		MembersVO membersVO1 = new MembersVO();
		membersVO1.setMem_nickname("jdbcTest");
		
		ManagerVO managerVO1 = new ManagerVO();
		managerVO1.setMgr_id("jdbcTest");
		managerVO1.setMgr_pwd("123");
		managerVO1.setMgr_job(new Integer(1));
		managerVO1.setMgr_name("Lucy");
		managerVO1.setMgr_email("dimcjo6u4+4@gmail.com");
		managerVO1.setMgr_status(new Integer(1));
		byte[] pic = null;
		managerVO1.setMgr_pic(pic);
		
		dao.insert(managerVO1,membersVO1);
		
		// 修改
//		ManagerVO managerVO2 = new ManagerVO();
//		MembersVO membersVO2 = new MembersVO();
//		
//		membersVO2.setMem_nickname("Sam_Rock");
//		managerVO2.setMgr_no("2");
//		managerVO2.setMgr_pwd("321");
//		managerVO2.setMgr_job(new Integer(1));
//		managerVO2.setMgr_name("Sam");
//		managerVO2.setMgr_email("dimcjo6u4+3@gmail.com");
//		managerVO2.setMgr_status(new Integer(1));
//		byte[] pic2 = getPictureByteArray("pic/FC_Bayern.png");
//		managerVO2.setMgr_pic(pic2);
//		
//		dao.update(managerVO2,membersVO2);
//
//		//刪除
////		dao.delete("4");

//		// 查詢
//		ManagerVO managerVO3 = dao.findByPrimaryKey("1");
//		System.out.print(managerVO3.getMgr_no() + ",");
//		System.out.print(managerVO3.getMem_no() + ",");
//		System.out.print(managerVO3.getMgr_id() + ",");
//		System.out.print(managerVO3.getMgr_pwd() + ",");
//		System.out.print(managerVO3.getMgr_job() + ",");
//		System.out.print(managerVO3.getMgr_name()+ ",");
//		System.out.print(managerVO3.getMgr_email() + ",");
//		System.out.print(managerVO3.getMgr_status() + ",");
//		System.out.println(managerVO3.getMgr_pic());
//		System.out.println("---------------------");
//
//		
//		
//		// 查詢
//		List<ManagerVO> list = dao.getAll();
//		for (ManagerVO aManager : list) {
//			System.out.print(aManager.getMgr_no() + ",");
//			System.out.print(aManager.getMem_no() + ",");
//			System.out.print(aManager.getMgr_id() + ",");
//			System.out.print(aManager.getMgr_pwd() + ",");
//			System.out.print(aManager.getMgr_job() + ",");
//			System.out.print(aManager.getMgr_name() + ",");
//			System.out.print(aManager.getMgr_email() + ",");
//			System.out.print(aManager.getMgr_status() + ",");
//			System.out.print(aManager.getMgr_pic());
//			System.out.println();
//		}
//		System.out.println("----------------------------");
//		// 查詢某員工的階級
//		Set<ManagerVO> set = dao.getMgrByJob(1);
//		for(ManagerVO aJob : set){
//			System.out.print(aJob.getMgr_no() + ",");
//			System.out.print(aJob.getMem_no() + ",");
//			System.out.print(aJob.getMgr_id() + ",");
//			System.out.print(aJob.getMgr_pwd() + ",");
//			System.out.print(aJob.getMgr_job() + ",");
//			System.out.print(aJob.getMgr_name() + ",");
//			System.out.print(aJob.getMgr_email() + ",");
//			System.out.print(aJob.getMgr_status() + ",");
//			System.out.print(aJob.getMgr_pic());
//			System.out.println();
//		}
//		System.out.println("----------------------------");
//		// 查詢某員工的狀態
//		Set<ManagerVO> set1 = dao.getMgrByStatus(1);
//		for (ManagerVO aStatus : set1) {
//			System.out.print(aStatus.getMgr_no() + ",");
//			System.out.print(aStatus.getMem_no() + ",");
//			System.out.print(aStatus.getMgr_id() + ",");
//			System.out.print(aStatus.getMgr_pwd() + ",");
//			System.out.print(aStatus.getMgr_job() + ",");
//			System.out.print(aStatus.getMgr_name() + ",");
//			System.out.print(aStatus.getMgr_email() + ",");
//			System.out.print(aStatus.getMgr_status() + ",");
//			System.out.print(aStatus.getMgr_pic());
//			System.out.println();
//		}
		
//		boolean managerVO4 = dao.login("AAA", "123");
//		System.out.println(managerVO4);

		

	}

	@Override
	public ManagerVO loginTest(String mgr_account, String mgr_password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagerVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public boolean loginMgrSta(String mgr_account) {
//		// TODO Auto-generated method stub
//		return false;
//	}


	
	

	



}
