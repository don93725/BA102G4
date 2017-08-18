package com.forum.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;
import com.forum.domain.Article_comments;
import com.forum.domain.Articles;
import com.members.model.MembersVO;

public class ArticlesDAO extends BasicDAO implements DAOInterface<Articles> {

	// 建置查詢

	public List<Articles> getVOBySQL(String SQL, Object[] param) {
		List list = new SQLHelper().executeQuery(SQL, param);
		List<Articles> tempList = new ArrayList<Articles>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			Articles articles = new Articles();
			if (obj[0] != null) {
				articles.setArt_no(String.valueOf(obj[0]));
				Article_commentsDAO article_commentsDAO = new Article_commentsDAO();
				String sql="select art_cmt_no,art_no,a.mem_no,art_cmt_ctx,art_cmt_img,art_cmt_time,mem_nickname,mem_rank from Article_comments a join (select mem_nickname,mem_rank,mem_no from members) b on a.mem_no=b.mem_no "+ 
						   "where (art_cmt_time,art_cmt_no) in (select max(art_cmt_time),art_cmt_no from Article_comments where art_no="+String.valueOf(obj[0])+" group by art_cmt_no)";
				List<Article_comments> temp = article_commentsDAO.getVOBySQL(sql, null);
				if(temp.size()!=0){
					articles.setNewestCmmt(temp.get(0));					
				}
			}
			if (obj[1] != null) {
				MembersVO members = new MembersVO();
				members.setMem_no((String) obj[1]);
				if(obj[10] != null){
					members.setMem_nickname((String) obj[10]);					
				}
				if(obj[11] != null){
					members.setMem_rank((String) obj[11]);					
				}
				articles.setMem_no(members);
			}
			if (obj[2] != null) {
				articles.setForum_no((String) obj[2]);
			}
			if (obj[3] != null) {
				articles.setArt_type((String) obj[3]);
			}
			if (obj[4] != null) {
				articles.setArt_add_date((Date) obj[4]);
			}
			if (obj[5] != null) {
				articles.setArt_upd_date((Date) obj[5]);
			}
			if (obj[6] != null) {
				articles.setArt_name((String) obj[6]);
			}
			if (obj[7] != null) {
				BufferedReader br =null;
				try {
					Clob clob = (Clob) obj[7];				
					br = new BufferedReader(clob.getCharacterStream());
					StringBuilder msg_ctx = new StringBuilder();
					String temp = null;
					while( (temp = br.readLine()) !=null){
						msg_ctx.append(temp);
					}
					br.close();
					articles.setArt_ctx(msg_ctx.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (obj[8] != null) {
				articles.setArt_views(Integer.parseInt(obj[8].toString()));
			}
			if (obj[9] != null) {
				articles.setArt_mviews(Integer.parseInt(obj[9].toString()));
			}			
			tempList.add(articles);
		}
		return tempList;
	}
	// 建置查詢單筆

	public Articles getVOByPK(String art_no) {
		String SQL = "Select * from articles a join (select mem_nickname,mem_no from members) b on a.mem_no=b.mem_no where art_no=?";
		Object[] param = { art_no };
		List<Articles> list = getVOBySQL(SQL, param);
		Articles articles = list.get(0);
		return articles;
	}
	// 建置查詢全部

	public List<Articles> getAll() {
		String SQL = "select * from articles a join (select mem_nickname,mem_no from members) b on a.mem_no=b.mem_no";
		List<Articles> list = getVOBySQL(SQL, null);
		return list;
	}
	// 算數量

	public int countAll() {
		String SQL = "select * from articles ";
		return countBySQL(SQL);
	}

	// 建置修改

	public boolean updateByVO(Articles articles) {
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		Clob clob =null;
		try {
			clob = con.createClob();
			clob.setString(1, articles.getArt_ctx());
			String sql = "update articles set art_type=?,art_upd_date=sysdate,art_name=?,art_ctx=? where art_no=?";
			pstmt = con.prepareStatement(sql);
			Object[] param = { articles.getArt_type(), articles.getArt_name(), clob,
					articles.getArt_no() };
			 for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]);
			}
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 建置修改相片一起處理


	// 建置新增有照片

	public boolean executeInsert(Articles articles) {
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		Clob clob =null;
		try {
			clob = con.createClob();
			clob.setString(1, articles.getArt_ctx());
			String SQL = "insert into articles values(articles_pk_seq.nextval,?,?,?,SYSDATE,null,?,?,default,default)";
			Object[] param = { articles.getMem_no().getMem_no(), articles.getForum_no(), articles.getArt_type(),
					articles.getArt_name(), clob };
			pstmt = con.prepareStatement(SQL);
			for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]);
			}
			pstmt.executeUpdate();			
			result = true;

			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			helper.close(con, pstmt);
		}
		return result;
	}
	// 建置刪除

	public boolean executeDelete(String art_no) {
		SQLHelper helper = new SQLHelper();
		Connection con = helper.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			con.setAutoCommit(false);
			Article_reportDAO article_reportDAO = new Article_reportDAO();
			result = article_reportDAO.executeDelete(art_no,con);
			if(result){			
				
				Article_commentsDAO article_commentsDAO = new Article_commentsDAO();
				result = article_commentsDAO.executeDelete(art_no, con);
				if(result){
					String SQL = "delete from articles where art_no="+art_no;
					pstmt = con.prepareStatement(SQL);
					pstmt.executeUpdate();					
				}
				con.commit();
				result = true;
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			helper.close(con, pstmt);
		}
		return result;
	}
	// 建置分頁(彈性排序可設條件)

	public List<Articles> pageAndRank(int page, int pageSize, String order, String where) {
		int firstPage = (page - 1) * pageSize + 1;
		int lastPage = page * pageSize;
		String SQL = "select art_no,mem_no,forum_no,art_type,art_add_date,art_upd_date,art_name,art_ctx,art_views,art_mviews,mem_nickname,mem_rank from (select art_no,mem_no,forum_no,art_type,art_add_date,art_upd_date,art_name,art_ctx,art_views,art_mviews,mem_nickname,mem_rank, rownum rn from (select art_no,a.mem_no,forum_no,art_type,art_add_date,art_upd_date,art_name,art_ctx,art_views,art_mviews,mem_nickname,mem_rank from articles a join (select mem_nickname,mem_rank,mem_no from members) b on a.mem_no=b.mem_no";
		if (where != null) {
			SQL = SQL + " where " + where;
		}
		SQL = SQL + " order by " + order + ")) where rn between " + firstPage + " and " + lastPage;
		List<Articles> list = getVOBySQL(SQL, null);
		return list;
	}
	// 建置分頁(彈性排序不設條件)

	public List<Articles> pageAndRank(int page, int pageSize, String order) {
		List<Articles> list = pageAndRank(page, pageSize, order);
		return list;
	}
	// 建置分頁(PK排序)

	public List<Articles> pageAndRankByPk(int page, int pageSize) {
		List<Articles> list = pageAndRank(page, pageSize, "art_no");
		return list;
	}
	// 建置取得欄位資料

	public Object[] getCol(String col, Object[] param) {
		String SQL = "select " + col + " from articles where art_no=?";
		List<Object[]> list = new SQLHelper().executeQuery(SQL, param);
		Object[] colData = list.get(0);
		return colData;
	}
	// Service層實作

	// public class ArticlesServiece implements ServiceIntface<Articles>{
	// //封裝新增物件
	//
	// public boolean add(String art_no, String mem_no, String forum_no, String
	// art_type, Date art_add_date, Date art_upd_date, String art_name, String
	// art_ctx, Integer art_views, Integer art_mviews){
	// Articles articles = new Articles();
	// articles.setArt_no(art_no);
	// articles.setMem_no(mem_no);
	// articles.setForum_no(forum_no);
	// articles.setArt_type(art_type);
	// articles.setArt_add_date(art_add_date);
	// articles.setArt_upd_date(art_upd_date);
	// articles.setArt_name(art_name);
	// articles.setArt_ctx(art_ctx);
	// articles.setArt_views(art_views);
	// articles.setArt_mviews(art_mviews);
	// DAOInterface dao = new ArticlesDAO();
	// boolean result = dao.executeInsert(articles);
	// return result;
	// }

}
