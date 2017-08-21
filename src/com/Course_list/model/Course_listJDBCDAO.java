package com.Course_list.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Course.model.CourseJDBCDAO;
import com.Course.model.CourseVO;
import com.Course_time.model.Course_timeVO;
import com.place.model.PlaceVO;


public class Course_listJDBCDAO implements Course_listDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "jdbc";
//	 String userid = "JDBC";
	String passwd = "123";
	private static final String INSERT_STMT = "INSERT INTO course_list (ct_no,stu_acc,cl_date,crs_time,stu_pay_sta,stu_pay_date,report_sta,report_ct,feedback,evaluation_cao,evaluation_crs,n_sta,reason) VALUES (?, ?, ?, ?, default, ?, default, ?, ?, ?, ?, default, ?,)";
	private static final String GET_ALL_STMT = "SELECT * FROM course_list ";
	private static final String GET_ONE_STMT = "SELECT * FROM course_list  where ct_no = ? AND stu_acc = ?";
	private static final String DELETE = "DELETE FROM course_list  where ct_no = ? AND stu_acc = ?";
	private static final String UPDATE = "UPDATE course_list  set crs_no=?, p_no=?, crs_date=?, deadline=?, crs_time=?, price=?, limit=?, class_num=?, status=? where ct_no = ?";
	private static final String COUNT = "SELECT COUNT(*) from course_list where ct_no = ?";
	// 77777
	private static final String GET_REPORT_STA = "SELECT * FROM COURSE_LIST WHERE REPORT_STA=?";
	private static final String GET_COACHES = "SELECT * FROM COACHES WHERE COA_ACC = (SELECT C_ACC FROM COURSE WHERE CRS_NO=(SELECT CRS_NO FROM COURSE_TIME WHERE CT_NO=(SELECT CT_NO FROM COURSE_LIST WHERE CT_NO=?)) )";
	private static final String GET_COURSE = "SELECT * FROM COURSE WHERE CRS_NO=(SELECT CRS_NO FROM COURSE_TIME WHERE CT_NO=(SELECT CT_NO FROM COURSE_LIST WHERE CT_NO=?))";
	private static final String UPDATE_REP_STA = "UPDATE course_list  set REPORT_STA=2 where ct_no=?";
	private static final String UPDATE_MEM_CR_NUM="UPDATE MEMBERS SET MR_NUM=MR_NUM+1 WHERE MEM_acc=(select c_acc FROM COURSE WHERE CRS_NO=(SELECT CRS_NO FROM COURSE_TIME WHERE CT_NO=(SELECT CT_NO FROM COURSE_LIST WHERE CT_NO=?)))";

	
	@Override
	public void insert(Course_listVO course_listVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Course_listVO course_listVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRepSta(Course_listVO course_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_REP_STA);

			pstmt.setString(1, course_listVO.getCt_no());
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
	public void updateCRNum(Course_listVO course_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_MEM_CR_NUM);

			pstmt.setString(1, course_listVO.getCt_no());
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
	public void delete(String ct_no, String stu_acc) {
		
	}
	
	@Override
	public int count(String ct_no) {
		
		
		return 1 ;
		
	}

	@Override
	public Course_listVO findByPK(String ct_no, String stu_acc) {
		
		return null;
	}

	@Override
	public List<Course_listVO> getAll() {
		
		return null;
	}

	@Override
	public List<Course_listVO> getReportSta(Integer report_sta) {
		
		return null;
	}

	public static void main(String[] args) {

		Course_listJDBCDAO dao = new Course_listJDBCDAO();
		
		Course_listVO course_listVO = new Course_listVO();
		course_listVO.setCt_no("A000001");
		dao.updateRepSta(course_listVO);
		
//		Course_listVO course_listVO2 = new Course_listVO();
//		course_listVO2.setCt_no("B000002");
//		
//		dao.updateRepSta(course_listVO2);
//		dao.updateCRNum(course_listVO2);;
	}

	

}
