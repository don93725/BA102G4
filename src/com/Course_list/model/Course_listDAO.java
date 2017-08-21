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

import com.Course.model.CourseVO;
import com.Course_time.model.Course_timeVO;
import com.place.model.PlaceVO;


public class Course_listDAO implements Course_listDAO_interface{
	
	private static DataSource ds = null;
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

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REP_STA);
System.out.println("DAO~~updateRepSta~~~:  "+course_listVO.getCt_no());
			pstmt.setString(1, course_listVO.getCt_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM_CR_NUM);
System.out.println("DAO~~updateCRNum~~~:  "+course_listVO.getCt_no());

			pstmt.setString(1, course_listVO.getCt_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ct_no);
			pstmt.setString(2, stu_acc);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public int count(String ct_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT);
			pstmt.setString(1, ct_no);

			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			

			// Handle any driver errors
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
		
		return count;
		
	}

	@Override
	public Course_listVO findByPK(String ct_no, String stu_acc) {
		Course_listVO course_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ct_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_listVO = new Course_listVO();
				course_listVO.setCt_no(rs.getString("ct_no"));
				course_listVO.setStu_acc(rs.getString("stu_acc"));
				course_listVO.setCl_date(rs.getDate("cl_date"));
				course_listVO.setCrs_time(rs.getInt("crs_time"));
				course_listVO.setStu_pay_sta(rs.getInt("stu_pay_sta"));
				course_listVO.setStu_pay_date(rs.getDate("stu_pay_date"));
				course_listVO.setReport_sta(rs.getInt("report_sta"));
				course_listVO.setReport_ct(rs.getString("report_ct"));
				course_listVO.setFeedback(rs.getString("feedback"));
				course_listVO.setEvaluation_cao(rs.getString("evaluation_cao"));
				course_listVO.setEvaluation_crs(rs.getString("evaluation_crs"));
				course_listVO.setN_sta(rs.getInt("n_sta"));
				course_listVO.setReason(rs.getString("reason"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return course_listVO;
	}

	@Override
	public List<Course_listVO> getAll() {
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_listVO course_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				course_listVO = new Course_listVO();
				course_listVO.setCt_no(rs.getString("ct_no"));
				course_listVO.setStu_acc(rs.getString("stu_acc"));
				course_listVO.setCl_date(rs.getDate("cl_date"));
				course_listVO.setCrs_time(rs.getInt("crs_time"));
				course_listVO.setStu_pay_sta(rs.getInt("stu_pay_sta"));
				course_listVO.setStu_pay_date(rs.getDate("stu_pay_date"));
				course_listVO.setReport_sta(rs.getInt("report_sta"));
				course_listVO.setReport_ct(rs.getString("report_ct"));
				course_listVO.setFeedback(rs.getString("feedback"));
				course_listVO.setEvaluation_cao(rs.getString("evaluation_cao"));
				course_listVO.setEvaluation_crs(rs.getString("evaluation_crs"));
				course_listVO.setN_sta(rs.getInt("n_sta"));
				course_listVO.setReason(rs.getString("reason"));
				list.add(course_listVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public List<Course_listVO> getReportSta(Integer report_sta) {
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_listVO course_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_REPORT_STA);
			pstmt.setInt(1, report_sta);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				course_listVO = new Course_listVO();
				course_listVO.setCt_no(rs.getString("ct_no"));
				course_listVO.setStu_acc(rs.getString("stu_acc"));
				course_listVO.setCl_date(rs.getDate("cl_date"));
				course_listVO.setCrs_time(rs.getInt("crs_time"));
				course_listVO.setStu_pay_sta(rs.getInt("stu_pay_sta"));
				course_listVO.setStu_pay_date(rs.getDate("stu_pay_date"));
				course_listVO.setReport_sta(rs.getInt("report_sta"));
				course_listVO.setReport_ct(rs.getString("report_ct"));
				course_listVO.setFeedback(rs.getString("feedback"));
				course_listVO.setEvaluation_cao(rs.getString("evaluation_cao"));
				course_listVO.setEvaluation_crs(rs.getString("evaluation_crs"));
				course_listVO.setN_sta(rs.getInt("n_sta"));
				course_listVO.setReason(rs.getString("reason"));
				list.add(course_listVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}



	

}
