package com.forum.service;

import java.util.List;

import com.forum.dao.ArticlesDAO;
import com.forum.dao.ForumsDAO;
import com.forum.domain.Articles;
import com.members.model.MembersVO;

public class ArticlesService {
	//含相片
	public boolean add(String mem_no, String forum_no, String art_type_name, String art_name, String art_ctx) {
		System.out.println(mem_no);
		Articles articles=new Articles();
		MembersVO members = new MembersVO();
		members.setMem_no(mem_no);
		articles.setMem_no(members);
		articles.setForum_no(forum_no);
		articles.setArt_type(art_type_name);
		articles.setArt_name(art_name);
		articles.setArt_ctx(art_ctx);
		boolean result = new ArticlesDAO().executeInsert(articles);
		return result;
	}
	

	public boolean update(String art_type_name, String art_name, String art_ctx,String art_no) {
		Articles articles=new Articles();
		articles.setArt_type(art_type_name);
		articles.setArt_name(art_name);
		articles.setArt_ctx(art_ctx);
		articles.setArt_no(art_no);
		
		boolean result = new ArticlesDAO().updateByVO(articles);
		
		
		return result;
	}
	
	public List<Articles> getPageData(int thisPage,int pageSize,String forum_no) {
		String where = "forum_no="+forum_no;
		String order = "art_add_date desc";		
		List<Articles> articles=new ArticlesDAO().pageAndRank(thisPage, pageSize, order, where);
		return articles;
	}
	 public boolean increaseViews(String art_no){
		 ArticlesDAO articlesDAO = new ArticlesDAO();
		 String col="art_views,art_mviews";
		 Object[] param = {art_no};
		 Object[] viewVal = articlesDAO.getCol(col, param);		 
		 for(int i = 0 ; i<viewVal.length ; i++){
			 viewVal[i] = Integer.parseInt(viewVal[i].toString())+1;			 
		 }		 
		 String SQL = "update articles set art_views=?,art_mviews=? where art_no="+art_no;
		 
		 boolean result = articlesDAO.executeUpdate(SQL, viewVal);
		 return result;
	 }
	
}
