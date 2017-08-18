package com.don.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SQLHelper {

	public byte[] getPic(String sql, Object[] param) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		con = getConnection();
		byte[] b = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				b = rs.getBytes(1);
			}

		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
			throw new RuntimeException("A database error occured. "
                    + se.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return b;
	}

	// 查詢
	public ArrayList executeQuery(String sql, Object[] param) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		ArrayList al = new ArrayList();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					if (param[i] != null) {
						pstmt.setObject(i + 1, param[i]);
					}
				}
			}
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (rs.next()) {
				Object[] obj = new Object[colCount];
				for (int i = 0; i < colCount; i++) {
					obj[i] = rs.getObject(i + 1);
				}
				al.add(obj);
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. "
                    + se.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return al;
	}

	// 執行更新
	public boolean executeUpdate(String sql, Object[] param) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateResult = true;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. "
                    + se.getMessage());			
		} finally {
			close(con, pstmt);
		}
		return updateResult;
	}

	// 處理多重交易專用
	public String executeUpdate(String sql, Object[] param, String key, Connection conn) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateResult = true;
		ResultSet rs = null;
		String result = null;
		try {
			con = conn;
			con.setAutoCommit(false);
			if (key != null) {
				String[] keys = {key};
				pstmt = con.prepareStatement(sql,keys);
				
			} else {
				pstmt = con.prepareStatement(sql);
				
			}
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			int num =pstmt.executeUpdate();
			if(key!=null){
				rs = pstmt.getGeneratedKeys();
				rs.next();
				result = rs.getString(1);
			}else {
				result = "ok";
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. "
                    + se.getMessage());

		} finally{
			close(pstmt,rs);
		}
		return result;
	}

	// 執行更新
	public boolean executeUpdate(String[] sql, Object[][] param) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean updateResult = true;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			for (int j = 0; j < sql.length; j++) {
				pstmt = con.prepareStatement(sql[j]);
				if (param[j] != null) {
					for (int i = 0; i < param[j].length; i++) {
						pstmt.setObject(i + 1, param[j][i]);
					}
				}
				pstmt.executeUpdate();
			}
			con.commit();

		} catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. "
                    + se.getMessage());
		} finally {
			close(con, pstmt);
		}
		return updateResult;
	}

	// 創連線池
	static DataSource ds;
	static {
		try {
			Context ctx = new javax.naming.InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G4DB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 獲取連線
	public Connection getConnection() {
		Connection con = null;

		try {

			if (ds != null) {
				con = ds.getConnection();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return con;
	}

	// 關閉連線1
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 關閉連線2
	public static void close(Connection con, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// 關閉連線3
	public static void close(PreparedStatement pstmt,ResultSet rs) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// 關閉連線4
	public static void close(Connection con) {
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}