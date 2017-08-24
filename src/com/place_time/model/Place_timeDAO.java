package com.place_time.model;

import java.sql.Connection;
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

import com.gyms.model.GymsVO;
import com.place.model.PlaceVO;
import com.place_report.model.PlaceReportVO;

public class Place_timeDAO implements Place_timeDAO_interface {
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
			
	private static final String INSERT_STMT = "INSERT INTO PLACE_TIME (PT_NO,P_NO,OPC_ACC,RP_DATE,RP_TIME,OP_DATE,PBU_PRICE,PAU_PRICE,PBU_DATE) VALUES (PLACE_TIME_sq.Nextval,?,?,?,?,?,?,?,?)";

	private static final String GET_ALL_STMT = "SELECT * FROM PLACE_TIME pt JOIN place p ON pt.p_no = p.p_no where pt.opc_acc = ?";
	private static final String GET_ALL_COA_STMT = "SELECT * FROM PLACE_TIME pt JOIN place p ON pt.p_no = p.p_no JOIN gyms g on g.gym_acc = p.g_acc where pt.opc_acc = ? AND rp_date > sysdate";
	private static final String GET_ALL_USED_STMT = "SELECT * FROM PLACE_TIME pt JOIN place p ON pt.p_no = p.p_no JOIN gyms g on g.gym_acc = p.g_acc where pt.opc_acc = ? AND rp_date < sysdate";
	private static final String GET_ONE_STMT = "SELECT * FROM PLACE_TIME pt JOIN place p ON pt.p_no = p.p_no JOIN gyms g on g.gym_acc = p.g_acc where pt.pt_no = ?";
	// private static final String GET_Emps_ByDeptno_STMT = "SELECT
	// empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno
	// FROM emp2 where deptno = ? order by empno";

	// private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno
	// = ?";
	private static final String DELETE = "DELETE FROM PLACE_time where PT_NO = ?";

	private static final String UPDATE = "UPDATE PLACE_time set P_NO=?,OPC_ACC=?,RP_DATE=?,RP_TIME=?,OP_DATE=?,PBU_PRICE=?,PAU_PRICE=?,PBU_DATE=? where PT_NO = ?";

	@Override
	public void insert(Place_timeVO place_timeVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, place_timeVO.getP_no());
			pstmt.setString(2, place_timeVO.getOpc_acc());
			pstmt.setDate(3, place_timeVO.getRp_date());
			pstmt.setInt(4, place_timeVO.getRp_time());
			pstmt.setDate(5, place_timeVO.getOp_date());
			pstmt.setString(6, place_timeVO.getPbu_price());
			pstmt.setString(7, place_timeVO.getPau_price());
			pstmt.setDate(8, place_timeVO.getPbu_date());

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
	public void update(Place_timeVO place_timeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, place_timeVO.getP_no());
			pstmt.setString(2, place_timeVO.getOpc_acc());
			pstmt.setDate(3, place_timeVO.getRp_date());
			pstmt.setInt(4, place_timeVO.getRp_time());
			pstmt.setDate(5, place_timeVO.getOp_date());
			pstmt.setString(6, place_timeVO.getPbu_price());
			pstmt.setString(7, place_timeVO.getPau_price());
			pstmt.setDate(8, place_timeVO.getPbu_date());
			pstmt.setString(9, place_timeVO.getPt_no());

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
	public void delete(String pt_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, pt_no);

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
	public Place_timeVO findByPrimaryKey(String pt_no) {
		// TODO Auto-generated method stub

		Place_timeVO place_timeVO = null;
		PlaceVO placeVO = null;
		GymsVO gymsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, pt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_timeVO = new Place_timeVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				place_timeVO.setPt_no(rs.getString("PT_NO"));
				place_timeVO.setP_no(rs.getString("P_NO"));
				place_timeVO.setOpc_acc(rs.getString("OPC_ACC"));
				place_timeVO.setRp_date(rs.getDate("RP_DATE"));
				place_timeVO.setRp_time(rs.getInt("RP_TIME"));
				place_timeVO.setOp_date(rs.getDate("OP_DATE"));
				place_timeVO.setPbu_price(rs.getString("PBU_PRICE"));
				place_timeVO.setPau_price(rs.getString("PAU_PRICE"));
				place_timeVO.setPbu_date(rs.getDate("PBU_DATE"));
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_cap(rs.getInt("p_cap"));
				gymsVO.setGym_name(rs.getString("gym_name"));
				gymsVO.setGym_mail(rs.getString("gym_mail"));
				gymsVO.setGym_into(rs.getString("gym_into"));
				
				place_timeVO.setPlaceVO(placeVO);
				place_timeVO.setGymsVO(gymsVO);

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
		return place_timeVO;
	}

	@Override
	public List<Place_timeVO> getAll(String opc_acc) {
		// TODO Auto-generated method stub
		List<Place_timeVO> list = new ArrayList<Place_timeVO>();
		Place_timeVO place_timeVO = null;
		PlaceVO placeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1,opc_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_timeVO = new Place_timeVO();
				placeVO = new PlaceVO();
				place_timeVO.setPt_no(rs.getString("PT_NO"));
				place_timeVO.setP_no(rs.getString("P_NO"));
				place_timeVO.setOpc_acc(rs.getString("OPC_ACC"));
				place_timeVO.setRp_date(rs.getDate("RP_DATE"));
				place_timeVO.setRp_time(rs.getInt("RP_TIME"));
				place_timeVO.setOp_date(rs.getDate("OP_DATE"));
				place_timeVO.setPbu_price(rs.getString("PBU_PRICE"));
				place_timeVO.setPau_price(rs.getString("PAU_PRICE"));
				place_timeVO.setPbu_date(rs.getDate("PBU_DATE"));
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setP_name(rs.getString("p_name"));
				place_timeVO.setPlaceVO(placeVO);
				list.add(place_timeVO); // Store the row in the list
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
	public List<Place_timeVO> getAllCoa(String c_acc) {
		List<Place_timeVO> list = new ArrayList<Place_timeVO>();
		Place_timeVO place_timeVO = null;
		PlaceVO placeVO = null;
		GymsVO gymsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_COA_STMT);
			pstmt.setString(1,c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_timeVO = new Place_timeVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				place_timeVO.setPt_no(rs.getString("PT_NO"));
				place_timeVO.setP_no(rs.getString("P_NO"));
				place_timeVO.setOpc_acc(rs.getString("OPC_ACC"));
				place_timeVO.setRp_date(rs.getDate("RP_DATE"));
				place_timeVO.setRp_time(rs.getInt("RP_TIME"));
				place_timeVO.setOp_date(rs.getDate("OP_DATE"));
				place_timeVO.setPbu_price(rs.getString("PBU_PRICE"));
				place_timeVO.setPau_price(rs.getString("PAU_PRICE"));
				place_timeVO.setPbu_date(rs.getDate("PBU_DATE"));
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_cap(rs.getInt("p_cap"));
				gymsVO.setGym_name(rs.getString("gym_name"));
				gymsVO.setGym_mail(rs.getString("gym_mail"));
				
				place_timeVO.setPlaceVO(placeVO);
				place_timeVO.setGymsVO(gymsVO);
				list.add(place_timeVO); // Store the row in the list
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
	public void payPlace_time(String pt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("Update place_time set pbu_date = sysdate where pt_no = ?");
			
			pstmt.setString(1, pt_no);

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
	public List<Place_timeVO> getAllUsed(String c_acc) {
		List<Place_timeVO> list = new ArrayList<Place_timeVO>();
		Place_timeVO place_timeVO = null;
		PlaceVO placeVO = null;
		GymsVO gymsVO = null;
		PlaceReportVO placeReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_USED_STMT);
			pstmt.setString(1,c_acc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				place_timeVO = new Place_timeVO();
				placeVO = new PlaceVO();
				gymsVO = new GymsVO();
				place_timeVO.setPt_no(rs.getString("PT_NO"));
				place_timeVO.setP_no(rs.getString("P_NO"));
				place_timeVO.setOpc_acc(rs.getString("OPC_ACC"));
				place_timeVO.setRp_date(rs.getDate("RP_DATE"));
				place_timeVO.setRp_time(rs.getInt("RP_TIME"));
				place_timeVO.setOp_date(rs.getDate("OP_DATE"));
				place_timeVO.setPbu_price(rs.getString("PBU_PRICE"));
				place_timeVO.setPau_price(rs.getString("PAU_PRICE"));
				place_timeVO.setPbu_date(rs.getDate("PBU_DATE"));
				place_timeVO.setReport(rs.getInt("report"));
				place_timeVO.setEva(rs.getInt("eva"));
				place_timeVO.setEva_ct(rs.getString("eva_ct"));
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setP_name(rs.getString("p_name"));
				placeVO.setP_into(rs.getString("p_into"));
				placeVO.setP_add(rs.getString("p_add"));
				placeVO.setP_latlng(rs.getString("p_latlng"));
				placeVO.setP_cap(rs.getInt("p_cap"));
				gymsVO.setGym_name(rs.getString("gym_name"));
				gymsVO.setGym_mail(rs.getString("gym_mail"));
				
				place_timeVO.setPlaceVO(placeVO);
				place_timeVO.setGymsVO(gymsVO);
				list.add(place_timeVO); // Store the row in the list
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
	public void report(String pt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement("Update place_time set report = 1 where pt_no = ?");
			
			pstmt.setString(1, pt_no);

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


}
