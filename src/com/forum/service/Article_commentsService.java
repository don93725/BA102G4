package com.forum.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import com.don.util.TransData;
import com.forum.dao.Article_commentsDAO;
import com.forum.domain.Article_comments;
import com.forum.domain.Articles;
import com.members.model.MembersVO;

public class Article_commentsService {
	public boolean add(String art_no, String mem_no, Part part  , String art_cmt_ctx,String path){
		Article_comments article_comments = new Article_comments();
		article_comments.setArt_no(art_no);
		MembersVO members = new MembersVO();
		members.setMem_no(mem_no);
		article_comments.setMem_no(members);
		article_comments.setArt_cmt_ctx(art_cmt_ctx.replace("$ProjectRealPath$", path));
		if(part!=null){				
			try {
				article_comments.setArt_cmt_img(TransData.transBlob(part));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			article_comments.setArt_cmt_img(null);		
		}
		Article_commentsDAO article_commentsDAO = new Article_commentsDAO();
		boolean result = article_commentsDAO.executeInsert(article_comments);		
		
		return result;
		
	}
	public List<Article_comments> getPageData(int thisPage,int pageSize,String art_no){
		String order = "art_cmt_time";
		String where = "art_no="+art_no;
		List<Article_comments> article_comments = new Article_commentsDAO().pageAndRank(thisPage, pageSize, order,where);
		return article_comments;
	}
	
}
