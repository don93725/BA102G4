package com.course_time.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.course_picture.model.Course_pictureVO;
import com.course_time.model.Course_timeVO;
import com.gyms.model.GymsVO;
import com.members.model.MembersVO;
import com.course_list.model.Course_listVO;
import com.coaches.model.CoachesVO;
import com.comments.model.Board_cmt;
import com.comments.model.Board_cmtDAO;
import com.course.model.CourseVO;
import com.course.query.CourseQuery;
import com.course_list.model.Course_listDAO;
import com.course_picture.model.Course_pictureService;
import com.place.model.PlaceVO;
import com.students.model.StudentsVO;

public class Course_timeDAO implements Course_timeDAO_interface {
	private static DataSource ds = null;
	private static final String INSERT_STMT = "INSERT INTO course_time (ct_no,crs_no,p_no,crs_date,deadline,crs_time,price,limit,class_num,status) VALUES (? || lpad(course_time_sq.NEXTVAL,6,'0'), ?, ?, ?, ?, ?, ?, ?, ?, default)";
	private static final String GET_ALL_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no where c.c_acc = ? AND ct.status = 1";
	private static final String GET_ALL_OPEN_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no where c.c_acc = ? AND ct.status = 2 AND ct.deadline < sysdate AND ct.crs_date > sysdate";
	private static final String GET_ALL_RECORD_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no where c.c_acc = ? AND ct.status = 2 AND ct.crs_date < sysdate";
	private static final String GET_ALL_CRSLIST_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no join coaches coa ON c.c_acc = coa.coa_acc where ct.status = 1 order by ct.crs_date";
	private static final String GET_ALL_CRSLIST_SELECT_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no join coaches coa ON c.c_acc = coa.coa_acc where ct.status = 1";
	private static final String GET_ONE_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no join coaches coa ON c.c_acc = coa.coa_acc where ct.ct_no = ? order by ct.crs_date";
	private static final String DELETE = "DELETE FROM course_time  where ct_no = ?";
	private static final String DELETE_CALENDAR = "delete from COURSE_TIME where crs_date = ? AND crs_time = ? AND crs_no in (select crs_no from course where c_acc = ?)";
	private static final String UPDATE = "UPDATE course_time  set p_no=?, crs_date=?, deadline=?, crs_time=?, price=? where ct_no = ?";
	private static final String GET_ALL_BY_CRSNO_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no join coaches coa ON c.c_acc = coa.coa_acc where ct.status = 1 AND ct.crs_no = ? order by ct.crs_date";
	private static final String GET_ALL_BEFORELIST_STMT = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no join coaches coa ON c.c_acc = coa.coa_acc where ct.status = 1 AND ct.deadline < sysdate";
	private static final String GET_ALL_STU = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join place p ON ct.p_no = p.p_no join course_list cl ON ct.ct_no = cl.ct_no join students stu ON stu.stu_acc = cl.stu_acc join members m ON m.MEM_NO = stu.stu_no where c.c_acc = ? AND ct.ct_no = ?";
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Course_timeVO course_timeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, course_timeVO.getCourseVO().getCategory());
			pstmt.setString(2, course_timeVO.getCourseVO().getCrs_no());
			pstmt.setString(3, course_timeVO.getP_no());
			pstmt.setDate(4, course_timeVO.getCrs_date());
			pstmt.setDate(5, course_timeVO.getDeadline());
			pstmt.setInt(6, course_timeVO.getCrs_time());
			pstmt.setString(7, course_timeVO.getPrice());
			pstmt.setString(8, course_timeVO.getLimit());
			pstmt.setString(9, course_timeVO.getClass_num());


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
	public void update(Course_timeVO course_timeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, course_timeVO.getP_no());
			pstmt.setDate(2, course_timeVO.getCrs_date());
			pstmt.setDate(3, course_timeVO.getDeadline());
			pstmt.setInt(4, course_timeVO.getCrs_time());
			pstmt.setString(5, course_timeVO.getPrice());
			pstmt.setString(6, course_timeVO.getCt_no());
			
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
	public void delete(String ct_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ct_no);

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
	public Course_timeVO findByPK(String ct_no) {
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		Course_pictureService course_pictureSVC = new Course_pictureService();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ct_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				coachesVO = new CoachesVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				Board_cmtDAO board_cmtDAO = new Board_cmtDAO();
				List<Board_cmt> comments = board_cmtDAO.pageAndRank("3",rs.getString("ct_no"));
				String sql = "select count(*) from board_cmt where cmt_type=3 and org_no='"+rs.getString("ct_no")+"'";
				course_timeVO.setCmtNum(board_cmtDAO.countBySQL(sql));
				course_timeVO.setComments(comments);
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setDetails(rs.getString("details"));
				courseVO.setCategory(rs.getString("category"));
				courseVO.setPicList(course_pictureSVC.getAll(rs.getString("crs_no")));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCoachesVO(coachesVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
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
		return course_timeVO;
	}

	@Override
	public List<Course_timeVO> getAll(String c_acc) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
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
	public List<Course_timeVO> getAllOpen(String c_acc) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_OPEN_STMT);
			pstmt.setString(1, c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setC_acc(rs.getString("c_acc"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
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
	public List<Course_timeVO> getAllRecord(String c_acc) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_RECORD_STMT);
			pstmt.setString(1, c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
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
	public List<Course_timeVO> getAllCrsList() {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CRSLIST_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				coachesVO = new CoachesVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCoachesVO(coachesVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
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
	public List<Course_timeVO> getAllCrsListSelect(String select) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CRSLIST_SELECT_STMT + select + " order by ct.crs_date");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				coachesVO = new CoachesVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCoachesVO(coachesVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
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
	public List<Course_timeVO> getAllByCrs_no(String crs_no) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		Course_pictureService course_pictureSVC = new Course_pictureService();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_CRSNO_STMT);
			pstmt.setString(1, crs_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				coachesVO = new CoachesVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				courseVO.setPicList(course_pictureSVC.getAll(rs.getString("crs_no")));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCoachesVO(coachesVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
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
	public List<Course_timeVO> getAllBeforeList() {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BEFORELIST_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				placeVO = new PlaceVO();
				coachesVO = new CoachesVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				placeVO.setP_name((rs.getString("p_name")==null)?"無":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				coachesVO.setCoa_no(rs.getString("coa_no"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCoachesVO(coachesVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
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
	public void open(String ct_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("Update coruse_time set status = 2 where ct_no = ?");
			pstmt.setString(1, ct_no);
			
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
	public List<Course_timeVO> getClass(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();
		Course_pictureVO course_pictureVO = new Course_pictureVO();
		try {
									
						
			con = ds.getConnection();
			String finalSQL = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no left outer join course_picture cp ON ct.crs_no = cp.crs_no join coaches coa on c.c_acc=coa.coa_acc left outer join place p  ON ct.p_no = p.p_no "
		          + CourseQuery.get_WhereCondition(map)
		          +"AND ct.status = 1"
		          +" "
		          + "order by ct_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO = new CoachesVO();
				placeVO = new PlaceVO();
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				
				System.out.println(rs.getString("crs_name")+ rs.getString("coa_name")+rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name((rs.getString("p_name")==null)?"null":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				course_pictureVO.setCrs_base(rs.getString("crs_base"));
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setCoachesVO(coachesVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setCourse_pictureVO(course_pictureVO);
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				list.add(course_timeVO); // Store the row in the list
		
				
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
	public List<Course_timeVO> getItem(String category, String crs_date, String p_name) {
		// TODO Auto-generated method stub
		
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		CourseVO courseVO = null;
		PlaceVO placeVO = null;
		CoachesVO coachesVO = null;
		GymsVO gymsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();
						
		
		try {

			con = ds.getConnection();
			
			String finalSQL = "SELECT * FROM COURSE_TIME CT JOIN COURSE C ON CT.CRS_NO = C.CRS_NO JOIN COACHES COA ON C.C_ACC=COA.COA_ACC LEFT OUTER JOIN PLACE P  ON CT.P_NO = P.P_NO JOIN GYMS G ON  P.G_ACC = G.GYM_ACC WHERE "
			          +" "
			          + "TO_CHAR(CRS_DATE,'yyyy-mm-dd')="+"'"+crs_date+"'"
			          +" "
			          +"AND C.CATEGORY="+"'"+category+"'"
			          +" "
			          +"AND P.P_NAME="+"'"+p_name+"'"
			          +" "
			          +"AND CT.STATUS = 1";
			      
			          
				
			System.out.println(finalSQL);
			
			
			pstmt = con.prepareStatement(finalSQL);
			
			//pstmt.setString(4,coa_name);
			
			
			rs = pstmt.executeQuery();
			
			System.out.print(pstmt + " " + rs);
			
			while (rs.next()) {
				
				course_timeVO = new Course_timeVO();
				courseVO = new CourseVO();
				coachesVO=new CoachesVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				
				
				course_timeVO.setCt_no(rs.getString("ct_no"));
				course_timeVO.setCrs_no(rs.getString("crs_no"));
				course_timeVO.setP_no(rs.getString("p_no"));
				course_timeVO.setCrs_date(rs.getDate("crs_date"));
				course_timeVO.setDeadline(rs.getDate("deadline"));
				course_timeVO.setCrs_time(rs.getInt("crs_time"));
				course_timeVO.setPrice(rs.getString("price"));
				course_timeVO.setLimit(rs.getString("limit"));
				course_timeVO.setClass_num(rs.getString("class_num"));
				course_timeVO.setStatus(rs.getInt("status"));
				
				System.out.println(rs.getString("crs_name")+ rs.getString("coa_name")+rs.getInt("status"));
				courseVO.setCrs_name(rs.getString("crs_name"));
				courseVO.setCategory(rs.getString("category"));
				coachesVO.setCoa_name(rs.getString("coa_name"));
				placeVO.setP_name((rs.getString("p_name")==null)?"�L":rs.getString("p_name"));
				placeVO.setP_no(rs.getString("p_no"));
				
				gymsVO.setGym_latlng(rs.getString("gym_latlng"));
				
				course_timeVO.setCourseVO(courseVO);
				course_timeVO.setCoachesVO(coachesVO);
				course_timeVO.setPlaceVO(placeVO);
				course_timeVO.setGymsVO(gymsVO);
				
				course_timeVO.setCount(course_listDAO.count(rs.getString("ct_no")));
				
				
				
				list.add(course_timeVO); // Store the row in the list
			
				
			
			
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
	

	public boolean findSignUp(String ct_no, String stu_acc) {
		Course_listVO course_listVO = null;
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
	public void deleteCalendar(String crs_date, Integer crs_time, String c_acc) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_CALENDAR);
			pstmt.setDate(1, java.sql.Date.valueOf(crs_date));
			pstmt.setInt(2, crs_time);
			pstmt.setString(3, c_acc);

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
	public List<Course_timeVO> getStuByCt(String c_acc,String ct_no) {
		List<Course_timeVO> list = new ArrayList<Course_timeVO>();
		Course_timeVO course_timeVO = null;
		StudentsVO studentsVO = null;
		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Course_listDAO course_listDAO = new Course_listDAO();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STU);
			pstmt.setString(1, c_acc);
			pstmt.setString(2, ct_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				course_timeVO = new Course_timeVO();
				studentsVO = new StudentsVO();
				membersVO = new MembersVO();
				studentsVO.setStu_name(rs.getString("stu_name"));
				studentsVO.setStu_mail(rs.getString("stu_mail"));
				membersVO.setMem_nickname(rs.getString("mem_nickname"));
				membersVO.setMem_no(rs.getString("mem_no"));
				membersVO.setMem_rank(rs.getString("mem_rank"));
				course_timeVO.setStudentsVO(studentsVO);
				course_timeVO.setMembersVO(membersVO);
				list.add(course_timeVO); // Store the row in the list
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