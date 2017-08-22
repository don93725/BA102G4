package com.course_list.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.course_list.model.Course_listDAO;
import com.course_list.model.Course_listVO;
import com.course_picture.model.Course_pictureVO;
import com.coaches.model.CoachesVO;
import com.course.model.CourseVO;
import com.course_time.model.Course_timeVO;
import com.gyms.model.GymsVO;
import com.place.model.PlaceVO;
import com.students.model.StudentsVO;


public class Course_listDAO implements Course_listDAO_interface{
	
	private static DataSource ds = null;
	private static final String INSERT_STMT = "INSERT INTO course_list (ct_no,stu_acc,cl_date,crs_time,stu_pay_sta,stu_pay_date,report_sta,report_ct,feedback,evaluation_cao,evaluation_crs,n_sta,reason) VALUES (?, ?, ?, ?, default, null, default, null, null, null, null, default, null)";
	private static final String GET_ALL_STMT = "SELECT * FROM course_list cl join course_time ct ON cl.ct_no = ct.ct_no join course c ON c.crs_no = ct.crs_no join coaches coa ON　c.c_acc = coa.coa_acc left outer join place p ON p.p_no = ct.p_no where stu_acc = ? AND ct.status = 1";
	private static final String GET_ALL_OPEN_STMT = "SELECT * FROM course_list cl join course_time ct ON cl.ct_no = ct.ct_no join course c ON c.crs_no = ct.crs_no join coaches coa ON　c.c_acc = coa.coa_acc left outer join place p ON p.p_no = ct.p_no where stu_acc = ? AND ct.status = 2";
	private static final String GET_ALL_RECORD_STMT = "SELECT * FROM course_list cl join course_time ct ON cl.ct_no = ct.ct_no join course c ON c.crs_no = ct.crs_no join coaches coa ON　c.c_acc = coa.coa_acc left outer join place p ON p.p_no = ct.p_no where stu_acc = ? AND ct.status = 3";
	private static final String GET_ALL_BY_CT_NO = "SELECT * FROM course_list cl join students s on cl.stu_acc = s.stu_acc where cl.ct_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM course_list cl join course_time ct ON cl.ct_no = ct.ct_no join course c ON c.crs_no = ct.crs_no join coaches coa ON　c.c_acc = coa.coa_acc left outer join place p ON p.p_no = ct.p_no where cl.ct_no = ? AND cl.stu_acc = ?";
	private static final String DELETE = "DELETE FROM course_list  where ct_no = ? AND stu_acc = ?";
	private static final String UPDATE = "UPDATE course_list  set crs_no=?, p_no=?, crs_date=?, deadline=?, crs_time=?, price=?, limit=?, class_num=?, status=? where ct_no = ?";
	private static final String COUNT = "SELECT COUNT(*) from course_list where ct_no = ?";
	private static final String PAY = "UPDATE course_list set stu_pay_sta = 1,stu_pay_date = sysdate where ct_no = ? AND stu_acc = ?";
	private static final String GET_REPORT_STA = "SELECT * FROM COURSE_LIST WHERE REPORT_STA=?";
	private static final String UPDATE_MEM_CR_NUM="UPDATE MEMBERS SET MR_NUM=MR_NUM+1 WHERE MEM_acc=(select c_acc FROM COURSE WHERE CRS_NO=(SELECT CRS_NO FROM COURSE_TIME WHERE CT_NO=(SELECT CT_NO FROM COURSE_LIST WHERE CT_NO=?)))";
	private static final String UPDATE_REP_STA = "UPDATE course_list  set REPORT_STA=2 where ct_no=?";

	
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
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, course_listVO.getCt_no());
			pstmt.setString(2, course_listVO.getStu_acc());
			pstmt.setDate(3, course_listVO.getCl_date());
			pstmt.setInt(4, course_listVO.getCrs_time());

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
	public void update(Course_listVO course_listVO) {
		// TODO Auto-generated method stub
		
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
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		CoachesVO coachesVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ct_no);
			pstmt.setString(2, stu_acc);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO = new CoachesVO();
				placeVO = new PlaceVO();
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
				course_timeVO.setPrice(rs.getString("price"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name(rs.getString("p_name")==null?"無":rs.getString("p_name"));
				course_listVO.setCoachesVO(coachesVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
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
	public List<Course_listVO> getAll(String stu_acc) {
		Course_listDAO course_listDAO = new Course_listDAO();
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_listVO course_listVO = null;
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		CoachesVO coachesVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, stu_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO = new CoachesVO();
				placeVO = new PlaceVO();
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
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setLimit(rs.getString("limit"));
				courseVO.setCategory(rs.getString("category"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name(rs.getString("p_name")==null?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_listVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				course_listVO.setCoachesVO(coachesVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
				
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
	public void pay(String ct_no, String stu_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(PAY);
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
	public boolean findSignUp(String ct_no, String stu_acc) {
		boolean result = true;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from course_list where ct_no = ? AND stu_acc = ?");
			pstmt.setString(1, ct_no);
			pstmt.setString(2, stu_acc);
			rs = pstmt.executeQuery();
			
			result = rs.next();


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
		return result;
	}

	@Override
	public List<Course_listVO> getAllByCt_no(String ct_no) {
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_listVO course_listVO = null;
		StudentsVO studentsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_CT_NO);
			pstmt.setString(1, ct_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_listVO = new Course_listVO();
				studentsVO = new StudentsVO();
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
				course_listVO.setStudentsVO(studentsVO);
				
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
	public void leave(String reason,String ct_no, String stu_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("Update course_list set n_sta = 2,reason = ? where ct_no = ? AND stu_acc =?");
			pstmt.setString(1, reason);
			pstmt.setString(2, ct_no);
			pstmt.setString(3, stu_acc);

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
	public List<Course_listVO> getAllOpen(String stu_acc) {
		Course_listDAO course_listDAO = new Course_listDAO();
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_listVO course_listVO = null;
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		CoachesVO coachesVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_OPEN_STMT);
			pstmt.setString(1, stu_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO = new CoachesVO();
				placeVO = new PlaceVO();
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
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setLimit(rs.getString("limit"));
				courseVO.setCategory(rs.getString("category"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name(rs.getString("p_name")==null?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_listVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				course_listVO.setCoachesVO(coachesVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
				
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
	public void report(String report_ct, String ct_no, String stu_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("Update course_list set report_sta = 1,report_ct = ? where ct_no = ? AND stu_acc =?");
			pstmt.setString(1, report_ct);
			pstmt.setString(2, ct_no);
			pstmt.setString(3, stu_acc);

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
	public void evaluation(String evaluation_coa, String evaluation_crs, String feedback, String ct_no,
			String stu_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("Update course_list set evaluation_cao = ?,evaluation_crs = ?,feedback = ? where ct_no = ? AND stu_acc =?");
			pstmt.setString(1, evaluation_coa);
			pstmt.setString(2, evaluation_crs);
			pstmt.setString(3, feedback);
			pstmt.setString(4, ct_no);
			pstmt.setString(5, stu_acc);

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
	public List<Course_listVO> getAllRecord(String stu_acc) {
		Course_listDAO course_listDAO = new Course_listDAO();
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_listVO course_listVO = null;
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		CoachesVO coachesVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_RECORD_STMT);
			pstmt.setString(1, stu_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO = new CoachesVO();
				placeVO = new PlaceVO();
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
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setLimit(rs.getString("limit"));
				courseVO.setCategory(rs.getString("category"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name(rs.getString("p_name")==null?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_listVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				course_listVO.setCoachesVO(coachesVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
				
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
	public void deleteCalendar(String cl_date,Integer crs_time,String stu_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("DELETE FROM course_list where cl_date = ? AND crs_time = ? AND stu_acc = ?");
			pstmt.setDate(1, java.sql.Date.valueOf(cl_date));
			pstmt.setInt(2, crs_time);
			pstmt.setString(3, stu_acc);
			int c = pstmt.executeUpdate();

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
	@Override
	public void updateCRNum(Course_listVO course_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM_CR_NUM);
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
	public void updateRepSta(Course_listVO course_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REP_STA);
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
	public List<Course_listVO> getReserve(String stu_acc) {
		// TODO Auto-generated method stub
		
	
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		GymsVO gymsVO = null;
		Course_listVO course_listVO = null;
		Course_pictureVO course_pictureVO = null;
		Course_listDAO course_listDAO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		course_listDAO = new Course_listDAO();				
		
		try {

			con = ds.getConnection();
			
		
			
			String finalSQL = "SELECT * FROM COURSE_LIST CL JOIN COURSE_TIME  CT  ON CL.CT_NO = CT.CT_NO LEFT OUTER JOIN PLACE P  ON CT.P_NO = P.P_NO JOIN STUDENTS STU ON cl.stu_acc = stu.stu_acc join course_picture cp ON CT.crs_no = cp.crs_no JOIN COURSE CRS ON CT.CRS_NO = crs.crs_no JOIN GYMS G ON  P.G_ACC = G.GYM_ACC WHERE CL.STU_ACC ="
			           +"'"+stu_acc+"'"
			          ;
			      
			          
				
			System.out.println(finalSQL);
			
			
			pstmt = con.prepareStatement(finalSQL);
			
			//pstmt.setString(4,coa_name);
			
			
			rs = pstmt.executeQuery();
			
			System.out.print(pstmt + " " + rs);
			
			while (rs.next()) {
				
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO=new CoachesVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				course_pictureVO = new Course_pictureVO();
				
				
				
				
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
				
				System.out.println("AAAA");
				
				
				courseVO.setCrs_name(rs.getString("crs_name"));
				
				System.out.println("A");
				courseVO.setCategory(rs.getString("category"));
				//coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name((rs.getString("p_name")==null)?"null":rs.getString("p_name"));
				
				System.out.println("B");
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				System.out.println("C");
				course_pictureVO.setCrs_base(rs.getString("crs_base"));
				System.out.println("D");
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				gymsVO.setGym_add(rs.getString("gym_add"));
				System.out.println("E");
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				System.out.println("G");
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
				course_listVO.setGymsVO(gymsVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourse_pictureVO(course_pictureVO);
				
				
				
				
				
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
	public List<Course_listVO> getCoachReserve(String coa_acc) {
		// TODO Auto-generated method stub
		
	
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		GymsVO gymsVO = null;
		Course_listVO course_listVO = null;
		Course_pictureVO course_pictureVO = null;
		Course_listDAO course_listDAO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		course_listDAO = new Course_listDAO();				
		
		try {

			con = ds.getConnection();
			
		
			
			String finalSQL = "SELECT * FROM COURSE_LIST CL JOIN COURSE_TIME  CT  ON CL.CT_NO = CT.CT_NO LEFT OUTER JOIN PLACE P  ON CT.P_NO = P.P_NO JOIN STUDENTS STU ON cl.stu_acc = stu.stu_acc join course_picture cp ON CT.crs_no = cp.crs_no JOIN COURSE CRS ON CT.CRS_NO = crs.crs_no JOIN GYMS G ON  P.G_ACC = G.GYM_ACC WHERE CL.STU_ACC ="
			           +"'"+coa_acc+"'"
			          ;
			      
			          
				
			System.out.println(finalSQL);
			
			
			pstmt = con.prepareStatement(finalSQL);
			
			//pstmt.setString(4,coa_name);
			
			
			rs = pstmt.executeQuery();
			
			System.out.print(pstmt + " " + rs);
			
			while (rs.next()) {
				
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO=new CoachesVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				course_pictureVO = new Course_pictureVO();
				
				
				
				
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
				
				System.out.println("AAAA");
				
				
				courseVO.setCrs_name(rs.getString("crs_name"));
				
				System.out.println("A");
				courseVO.setCategory(rs.getString("category"));
				//coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name((rs.getString("p_name")==null)?"null":rs.getString("p_name"));
				
				System.out.println("B");
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				System.out.println("C");
				course_pictureVO.setCrs_base(rs.getString("crs_base"));
				System.out.println("D");
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				gymsVO.setGym_add(rs.getString("gym_add"));
				System.out.println("E");
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				System.out.println("G");
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
				course_listVO.setGymsVO(gymsVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourse_pictureVO(course_pictureVO);
				
				
				
				
				
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
	public List<Course_listVO> getReady(String stu_acc) {
		// TODO Auto-generated method stub
		
	
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		GymsVO gymsVO = null;
		Course_listVO course_listVO = null;
		Course_pictureVO course_pictureVO = null;
		Course_listDAO course_listDAO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		course_listDAO = new Course_listDAO();				
		
		try {

			con = ds.getConnection();
			
		
			
			String finalSQL = "SELECT * FROM COURSE_LIST CL JOIN COURSE_TIME  CT  ON CL.CT_NO = CT.CT_NO LEFT OUTER JOIN PLACE P  ON CT.P_NO = P.P_NO JOIN STUDENTS STU ON cl.stu_acc = stu.stu_acc join course_picture cp ON CT.crs_no = cp.crs_no JOIN COURSE CRS ON CT.CRS_NO = crs.crs_no join coaches coa on CRS.c_acc=coa.coa_acc JOIN GYMS G ON  P.G_ACC = G.GYM_ACC WHERE CT.status = 2 AND CL.STU_ACC ="
			           +"'"+stu_acc+"'"
			          ;
			      
			          
				
			System.out.println(finalSQL);
			
			
			pstmt = con.prepareStatement(finalSQL);
			
			//pstmt.setString(4,coa_name);
			
			
			rs = pstmt.executeQuery();
			
			System.out.print(pstmt + " " + rs);
			
			while (rs.next()) {
				
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO=new CoachesVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				course_pictureVO = new Course_pictureVO();
				
				
				
				
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
				
				System.out.println("AAAA");
				
				coachesVO.setCoa_name(rs.getString("coa_name"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				
				System.out.println("A");
				courseVO.setCategory(rs.getString("category"));
				//coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name((rs.getString("p_name")==null)?"null":rs.getString("p_name"));
				
				System.out.println("B");
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				System.out.println("C");
				course_pictureVO.setCrs_base(rs.getString("crs_base"));
				System.out.println("D");
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				gymsVO.setGym_add(rs.getString("gym_add"));
				System.out.println("E");
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				System.out.println("G");
				
				course_listVO.setCoachesVO(coachesVO);
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
				course_listVO.setGymsVO(gymsVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourse_pictureVO(course_pictureVO);
				
				
				
				
				
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
	public List<Course_listVO> getFinished(String stu_acc) {
		// TODO Auto-generated method stub
		
	
		List<Course_listVO> list = new ArrayList<Course_listVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		GymsVO gymsVO = null;
		Course_listVO course_listVO = null;
		Course_pictureVO course_pictureVO = null;
		Course_listDAO course_listDAO = null ;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		course_listDAO = new Course_listDAO();				
		
		try {

			con = ds.getConnection();
			
		
			
			String finalSQL = "SELECT * FROM COURSE_LIST CL JOIN COURSE_TIME  CT  ON CL.CT_NO = CT.CT_NO LEFT OUTER JOIN PLACE P  ON CT.P_NO = P.P_NO JOIN STUDENTS STU ON cl.stu_acc = stu.stu_acc join course_picture cp ON CT.crs_no = cp.crs_no JOIN COURSE CRS ON CT.CRS_NO = crs.crs_no join coaches coa on CRS.c_acc=coa.coa_acc JOIN GYMS G ON  P.G_ACC = G.GYM_ACC WHERE CT.status = 2 AND CL.n_sta=1  AND CL.STU_ACC ="
			           +"'"+stu_acc+"'"
			          ;
			      
			          
				
			System.out.println(finalSQL);
			
			
			pstmt = con.prepareStatement(finalSQL);
			
			//pstmt.setString(4,coa_name);
			
			
			rs = pstmt.executeQuery();
			
			System.out.print(pstmt + " " + rs);
			
			while (rs.next()) {
				
				course_listVO = new Course_listVO();
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO=new CoachesVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				course_pictureVO = new Course_pictureVO();
				
				
				
				
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
				
				System.out.println("AAAA");
				
				coachesVO.setCoa_name(rs.getString("coa_name"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				
				System.out.println("A");
				courseVO.setCategory(rs.getString("category"));
				//coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name((rs.getString("p_name")==null)?"null":rs.getString("p_name"));
				
				System.out.println("B");
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				System.out.println("C");
				course_pictureVO.setCrs_base(rs.getString("crs_base"));
				System.out.println("D");
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				gymsVO.setGym_add(rs.getString("gym_add"));
				System.out.println("E");
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				System.out.println("G");
				
				course_listVO.setCoachesVO(coachesVO);
				course_listVO.setCourseVO(courseVO);
				course_listVO.setPlaceVO(placeVO);
				course_listVO.setGymsVO(gymsVO);
				course_listVO.setCourse_timeVO(course_timeVO);
				course_listVO.setCourse_pictureVO(course_pictureVO);
				
				
				
				
				
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
