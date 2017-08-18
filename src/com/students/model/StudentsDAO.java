package com.students.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.coaches.model.CoachesVO;
import com.members.model.MembersVO;

public class StudentsDAO implements StudentsDAO_interface{
	
	//連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_MEM =
			"Insert into members(mem_no, mem_acc, mem_rank, mem_nickname, mr_num)"
			+ "Values(mem_no_seq.NEXTVAL, ?, '0', ?, default)";	
	private static final String INSERT_STU =
			"Insert into students(stu_acc, stu_no, stu_psw, stu_sta, stu_name, stu_sex, stu_id, stu_mail, stu_into, stu_pic, stu_sto)"
			+ "values(?, ?, ?, default, ?, ?, ?, ?, ?, ? , default)";
	private static final String LOGIN_STU =
			"Select * from students where stu_acc = ? and stu_psw = ?";
	private static final String PIC_STU =
			"Select stu_pic from students where stu_no = ?";
	private static final String UPDATE_STU =
			"Update students set stu_name = ?, stu_mail = ?, stu_into = ? where stu_acc = ?";
	private static final String GET_ALL_STU =
			"Select * from students where stu_sta = 1 order by stu_no";
	private static final String SEARCH_STU =
			"Select * from students ";
	private static final String LOOK_SEARCH_MEM =
			"Select * from students where stu_acc = ?　and stu_no = ?";
	
	@Override
	public void insert(MembersVO membersVO, StudentsVO studentsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String mem_no = null;
		
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			//新增會員
			String cols[] = { "mem_no" };
			pstmt = con.prepareStatement(INSERT_MEM, cols);
			pstmt.setString(1, membersVO.getMem_acc());
			pstmt.setString(2, membersVO.getMem_nickname());
					
			System.out.println("Insert into members(mem_no, mem_acc, mem_rank, mem_nickname)"
					+ "Values(mem_no_seq.NEXTVAL," + membersVO.getMem_acc() +", '1', " + membersVO.getMem_nickname() + ", default)");
			pstmt.executeUpdate();
			
			//取得自增主鍵
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				mem_no = rs.getString(1);
							
				studentsVO.setStu_acc(membersVO.getMem_acc());
				studentsVO.setStu_no(mem_no);			
				System.out.println("(CoachesDAO)coa_acc= " + studentsVO.getStu_acc());
				} else {
					System.out.println("(CoachesDAO)沒有rs");
				}
				rs.close();
						
						
			//同時新增學健身者
			addWithMem_no(con, studentsVO);
						
			con.commit();
			
			//清空指令，重複利用
			pstmt.clearParameters();
			con.setAutoCommit(true);
			
		} catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//新增members的同時，新增students
	private static void addWithMem_no(Connection con, StudentsVO studentsVO){
		PreparedStatement pstmt = null;
			
		try {
			pstmt = con.prepareStatement(INSERT_STU);
					
			pstmt.setString(1, studentsVO.getStu_acc());
			pstmt.setString(2, studentsVO.getStu_no());
			pstmt.setString(3, studentsVO.getStu_psw());
			pstmt.setString(4, studentsVO.getStu_name());
			pstmt.setInt(5, studentsVO.getStu_sex());
			pstmt.setString(6, studentsVO.getStu_id());
			pstmt.setString(7, studentsVO.getStu_mail());
			pstmt.setString(8, studentsVO.getStu_into());
			pstmt.setBytes(9, studentsVO.getStu_pic());
						
			pstmt.executeUpdate();
					
		} catch (SQLException se) {
			se.getStackTrace();
			try{
				con.rollback();
			} catch (Exception e) {
				e.getStackTrace();
			}

		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public StudentsVO login(String stu_account, String stu_password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentsVO studentsVO = new StudentsVO();
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOGIN_STU);
			pstmt.setString(1, stu_account);
			pstmt.setString(2, stu_password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				studentsVO.setStu_acc(rs.getString("stu_acc"));
				studentsVO.setStu_no(rs.getString("stu_no"));
				studentsVO.setStu_psw(rs.getString("stu_psw"));
				studentsVO.setStu_sta(rs.getInt("stu_sta"));
				studentsVO.setStu_name(rs.getString("stu_name"));
				studentsVO.setStu_sex(rs.getInt("stu_sex"));
				studentsVO.setStu_id(rs.getString("stu_id"));
				studentsVO.setStu_mail(rs.getString("stu_mail"));
				studentsVO.setStu_into(rs.getString("stu_into"));
				studentsVO.setStu_pic(rs.getBytes("stu_pic"));
				studentsVO.setStu_sto(rs.getInt("stu_sto"));

				return studentsVO;
			}else {
				return null;
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

	public byte[] getPicByte(String stu_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] picByte = null;
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(PIC_STU);
			
			pstmt.setString(1, stu_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				picByte = rs.getBytes("stu_pic");
			}
			
			con.commit();
			con.setAutoCommit(true);
			
			
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
		return picByte;
	}

	public void update(StudentsVO studentsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_STU);
			pstmt.setString(1, studentsVO.getStu_name());
			pstmt.setString(2, studentsVO.getStu_mail());
			pstmt.setString(3, studentsVO.getStu_into());
			pstmt.setString(4, studentsVO.getStu_acc());
			
			pstmt.executeUpdate();
			System.out.println("CCC");
			con.commit();
		}catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<StudentsVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentsVO studentsVO= null;
		List<StudentsVO> studentsList = new ArrayList<StudentsVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STU);
			rs = pstmt.executeQuery();
			while(rs.next()){
				studentsVO = new StudentsVO();
				studentsVO.setStu_no(rs.getString("stu_no"));
				studentsVO.setStu_name(rs.getString("stu_name"));
				studentsVO.setStu_into(rs.getString("stu_into"));
				studentsList.add(studentsVO);
			}
		} catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return studentsList;
	}
	
	public List<StudentsVO> searchStu(String search_Name, String search_Type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentsVO studentsVO= null;
		List<StudentsVO> studentsList = new ArrayList<StudentsVO>();
		String SQL = null;
		try {
			if(search_Name.trim().length() == 0 && search_Type.trim().length() == 0 ) {
				SQL = SEARCH_STU + "order by stu_no";
			}else if(search_Name.trim().length() != 0 && search_Type.trim().length() != 0) {
				SQL = SEARCH_STU + "where stu_name like '%" + search_Name +"%' and stu_into like '%" + search_Type + "%' order by stu_no";
			}else if(search_Name.trim().length() == 0 && search_Type.trim().length() != 0) {
				SQL = SEARCH_STU + "where stu_into like '%" + search_Type + "%' order by stu_no";
			}else if(search_Name.trim().length() != 0 && search_Type.trim().length() == 0) {
				SQL = SEARCH_STU + "where stu_name like '%" + search_Name + "%' order by stu_no";
			}
			System.out.println("(SDAO)1= " + search_Name);
			System.out.println("(SDAO)2= " + search_Type);
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				studentsVO = new StudentsVO();
				studentsVO.setStu_no(rs.getString("stu_no"));
				studentsVO.setStu_name(rs.getString("stu_name"));
				studentsVO.setStu_into(rs.getString("stu_into"));
				studentsList.add(studentsVO);
				System.out.println("(SDAO)= " + rs.getString("stu_name"));
			}
		} catch(SQLException se) {
			try {
				se.printStackTrace();
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return studentsList;
	}

	public StudentsVO look_search_mem(MembersVO membersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StudentsVO studentsVO = null;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(LOOK_SEARCH_MEM);
			pstmt.setString(1, membersVO.getMem_acc());
			pstmt.setString(2, membersVO.getMem_no());

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				studentsVO = new StudentsVO();
				studentsVO.setStu_acc(rs.getString("Stu_acc"));
				studentsVO.setStu_no(rs.getString("Stu_no"));
				studentsVO.setStu_psw(rs.getString("Stu_psw"));
				studentsVO.setStu_sta(rs.getInt("Stu_sta"));
				studentsVO.setStu_name(rs.getString("Stu_name"));
				studentsVO.setStu_sex(rs.getInt("Stu_sex"));
				studentsVO.setStu_id(rs.getString("Stu_id"));
				studentsVO.setStu_mail(rs.getString("Stu_mail"));
				studentsVO.setStu_into(rs.getString("Stu_into"));
				studentsVO.setStu_pic(rs.getBytes("Stu_pic"));
				studentsVO.setStu_sto(rs.getInt("Stu_sto"));

				return studentsVO;
			}else {
				return null;
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

}
