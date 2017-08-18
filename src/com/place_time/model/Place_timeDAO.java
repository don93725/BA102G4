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

import com.place.model.PlaceVO;

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
	private static final String GET_ONE_STMT = "SELECT PT_NO,P_NO,OPC_ACC,RP_DATE,RP_TIME,OP_DATE,PBU_PRICE,PAU_PRICE,PBU_DATE FROM PLACE_TIME where PT_NO = ?";
	// private static final String GET_Emps_ByDeptno_STMT = "SELECT
	// empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno
	// FROM emp2 where deptno = ? order by empno";

	// private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno
	// = ?";
	private static final String DELETE = "DELETE FROM PLACE_time where PT_NO = ?";

	private static final String UPDATE = "UPDATE PLACE_time set P_NO=?,OPC_ACC=?,RP_DATE=?,RP_TIME=?,OP_DATE=?,PBU_PRICE=?,PAU_PRICE=?,PBU_DATE=? where PT_NO = ?";

	@Override
	public void insert(Place_timeVO place_TimeVo) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, place_TimeVo.getP_no());
			pstmt.setString(2, place_TimeVo.getOpc_acc());
			pstmt.setDate(3, place_TimeVo.getRp_date());
			pstmt.setInt(4, place_TimeVo.getRp_time());
			pstmt.setDate(5, place_TimeVo.getOp_date());
			pstmt.setString(6, place_TimeVo.getPbu_price());
			pstmt.setString(7, place_TimeVo.getPau_price());
			pstmt.setDate(8, place_TimeVo.getPbu_date());

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
	public void update(Place_timeVO place_TimeVo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, place_TimeVo.getP_no());
			pstmt.setString(2, place_TimeVo.getOpc_acc());
			pstmt.setDate(3, place_TimeVo.getRp_date());
			pstmt.setInt(4, place_TimeVo.getRp_time());
			pstmt.setDate(5, place_TimeVo.getOp_date());
			pstmt.setString(6, place_TimeVo.getPbu_price());
			pstmt.setString(7, place_TimeVo.getPau_price());
			pstmt.setDate(8, place_TimeVo.getPbu_date());
			pstmt.setString(9, place_TimeVo.getPt_no());

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

		Place_timeVO place_TimeVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, pt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				place_TimeVo = new Place_timeVO();
				place_TimeVo.setPt_no(rs.getString("PT_NO"));
				place_TimeVo.setP_no(rs.getString("P_NO"));
				place_TimeVo.setOpc_acc(rs.getString("OPC_ACC"));
				place_TimeVo.setRp_date(rs.getDate("RP_DATE"));
				place_TimeVo.setRp_time(rs.getInt("RP_TIME"));
				place_TimeVo.setOp_date(rs.getDate("OP_DATE"));
				place_TimeVo.setPbu_price(rs.getString("PBU_PRICE"));
				place_TimeVo.setPau_price(rs.getString("PAU_PRICE"));
				place_TimeVo.setPbu_date(rs.getDate("PBU_DATE"));

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
		return place_TimeVo;
	}

	@Override
	public List<Place_timeVO> getAll(String opc_acc) {
		// TODO Auto-generated method stub
		List<Place_timeVO> list = new ArrayList<Place_timeVO>();
		Place_timeVO place_TimeVO = null;
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
				place_TimeVO = new Place_timeVO();
				placeVO = new PlaceVO();
				place_TimeVO.setPt_no(rs.getString("PT_NO"));
				place_TimeVO.setP_no(rs.getString("P_NO"));
				place_TimeVO.setOpc_acc(rs.getString("OPC_ACC"));
				place_TimeVO.setRp_date(rs.getDate("RP_DATE"));
				place_TimeVO.setRp_time(rs.getInt("RP_TIME"));
				place_TimeVO.setOp_date(rs.getDate("OP_DATE"));
				place_TimeVO.setPbu_price(rs.getString("PBU_PRICE"));
				place_TimeVO.setPau_price(rs.getString("PAU_PRICE"));
				place_TimeVO.setPbu_date(rs.getDate("PBU_DATE"));
				placeVO.setP_no(rs.getString("p_no"));
				placeVO.setP_name(rs.getString("p_name"));
				place_TimeVO.setPlaceVO(placeVO);
				list.add(place_TimeVO); // Store the row in the list
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
