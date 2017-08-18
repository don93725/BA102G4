package com.forum.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.don.inteface.DAOInterface;
import com.don.util.BasicDAO;
import com.don.util.SQLHelper;
import com.forum.domain.Art_types;
import com.forum.domain.Article_comments;
import com.members.model.MembersVO;

import oracle.sql.BLOB;

public class Article_commentsDAO extends BasicDAO implements DAOInterface<Article_comments>{
	//建置查詢	
	public List<Article_comments> getVOBySQL(String SQL,Object[] param){
	List list = new SQLHelper().executeQuery(SQL, param);
	List<Article_comments> tempList = new ArrayList<Article_comments>();
	for(int i = 0 ; i < list.size() ; i++){
	Object[] obj =(Object[]) list.get(i);
	Article_comments article_comments= new Article_comments();
	if(obj[0]!=null){
	article_comments.setArt_cmt_no((String)obj[0]);
	}
	if(obj[1]!=null){
	article_comments.setArt_no((String)obj[1]);
	}
	if(obj[2]!=null){
		MembersVO members = new MembersVO();
		members.setMem_no((String) obj[2]);
		members.setMem_nickname((String) obj[6]);
		members.setMem_rank((String) obj[7]);
		article_comments.setMem_no(members);
	}
	if(obj[3]!=null){
	article_comments.setArt_cmt_ctx((String)obj[3]);
	}	
	if(obj[5]!=null){
	article_comments.setArt_cmt_time((Date)obj[5]);
	}
	tempList.add(article_comments);
	}
	return tempList;
	}
	//建置查詢單筆

	public Article_comments getVOByPK(String art_cmt_no){
	String SQL ="Select * from article_comments a join (select mem_nickname,mem_no from members) b on a.mem_no=b.mem_no where art_cmt_no=?";
	Object[] param ={art_cmt_no};
	List<Article_comments> list=getVOBySQL(SQL,param);
	Article_comments article_comments=list.get(0);
	return article_comments;
	}
	//建置查詢全部

	public List<Article_comments> getAll(){
	String SQL="select * from article_comments a join (select mem_nickname,mem_no from members) b on a.mem_no=b.mem_no";
	List<Article_comments> list=getVOBySQL(SQL,null);
	return list;
	}
	//算數量

	public int countAll(){
	String SQL = "select * from article_comments";
	return countBySQL(SQL);
	}
	//建置修改

	public boolean updateByVO(Article_comments article_comments){
	String SQL="update article_comments set art_no=?,mem_no=?,art_cmt_ctx=?,art_cmt_img=?,art_cmt_time=? where art_cmt_no=?";
	Object[] param ={article_comments.getArt_cmt_no(),article_comments.getArt_no(),article_comments.getMem_no(),article_comments.getArt_cmt_ctx(),article_comments.getArt_cmt_img(),article_comments.getArt_cmt_time()};
	boolean updateResult = new SQLHelper().executeUpdate(SQL,param);
	return updateResult;
	}
	
	
	
	
	//建置新增

	public boolean executeInsert(Article_comments article_comments){
	SQLHelper helper =	new SQLHelper();
	Connection con = helper.getConnection();
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	boolean result = false;
	try {
		con.setAutoCommit(false);
		String SQL="insert into article_comments values(article_comments_pk_seq.nextval,?,?,?,?,sysdate)";
		String[] keys = {"art_cmt_no"};
		pstmt = con.prepareStatement(SQL, keys);
		Object[] param ={article_comments.getArt_no(),article_comments.getMem_no().getMem_no(),article_comments.getArt_cmt_ctx(),article_comments.getArt_cmt_img()};
		for(int i = 0 ; i < param.length ; i++){
			pstmt.setObject(i+1, param[i]);
		}
		
		pstmt.executeUpdate();
		if(article_comments.getArt_cmt_img()!=null){			
			rs= pstmt.getGeneratedKeys(); 
			String art_cmt_ctx = article_comments.getArt_cmt_ctx();
			String key=null;
			if(rs.next()){
				key = rs.getString(1);
				art_cmt_ctx = art_cmt_ctx.replace("$ArtCmtPrimaryKey$", key) ;
				System.out.println(art_cmt_ctx);
			}	
			rs.close();
			
			String updateSQL = "update article_comments set art_cmt_ctx=? where art_cmt_no=?";
			Object[] updateParam = {art_cmt_ctx,key};
			System.out.println(art_cmt_ctx);
			pstmt = con.prepareStatement(updateSQL);	
			for(int i =0; i < updateParam.length ; i++){
				pstmt.setObject(i+1, updateParam[i]);			
			}
			pstmt.executeUpdate();			
		}
		con.commit();
		result = true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} finally{
		helper.close(con,pstmt,rs);
	}
	return result;
	
	
	}
	//建置刪除

	public boolean executeDelete(String art_cmt_no){
	String SQL="delete from article_comments where art_cmt_no=?";
	Object[] param={art_cmt_no};
	boolean deleteResult = new SQLHelper().executeUpdate(SQL,param);
	return deleteResult;
	}
	//帶連線
	public boolean executeDelete(String art_no,Connection conn){
		SQLHelper helper =	new SQLHelper();
		Connection con = conn;
		PreparedStatement pstmt =null;
		boolean result = false;		
		try {
			String SQL="delete from article_comments where art_no=?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setObject(1, art_no);
			pstmt.executeUpdate();
			result=true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return result;
		}
	//建置分頁(彈性排序可設條件)

	public List<Article_comments> pageAndRank(int page,int pageSize,String order,String where){
	int firstPage=(page-1)*pageSize;
	int lastPage = page*pageSize-1;
	if(page==1){
		firstPage = 1;		
	}
	String SQL="select art_cmt_no,art_no,mem_no,art_cmt_ctx,art_cmt_img,art_cmt_time,mem_nickname,mem_rank from (select art_cmt_no,art_no,mem_no,art_cmt_ctx,art_cmt_img,art_cmt_time,mem_nickname,mem_rank, rownum rn from (select art_cmt_no,art_no,a.mem_no,art_cmt_ctx,art_cmt_img,art_cmt_time,mem_nickname,mem_rank from article_comments a join (select mem_nickname,mem_rank,mem_no from members) b on a.mem_no=b.mem_no";
	if(where!=null){
	SQL = SQL +" where " + where;
	}
	SQL = SQL+ " order by "+order+")) where rn between "+firstPage+" and "+lastPage;
	List<Article_comments> list=getVOBySQL(SQL,null);
	return list;
	}
	//建置分頁(彈性排序不設條件)

	public List<Article_comments> pageAndRank(int page,int pageSize,String order){
	List<Article_comments> list=pageAndRank(page,pageSize,order);
	return list;
	}
	//建置分頁(PK排序)

	public List<Article_comments> pageAndRankByPk(int page,int pageSize){
	List<Article_comments> list=pageAndRank(page,pageSize,"art_cmt_no");
	return list;
	}
	//尋找圖片
	public byte[] getPic(String art_cmt_no){
		String SQL= "select art_cmt_img from article_comments where art_cmt_no="+art_cmt_no;
		byte[] b  = new SQLHelper().getPic(SQL, null);		
		return b;
	}
}
