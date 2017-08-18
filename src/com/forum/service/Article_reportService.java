package com.forum.service;

import java.util.Date;
import java.util.List;

import com.don.inteface.DAOInterface;
import com.forum.dao.Article_reportDAO;
import com.forum.domain.Article_report;
import com.forum.domain.Articles;
import com.members.model.MembersVO;

public class Article_reportService {
	//封裝新增物件

	public boolean add(String art_no, String rpt_mem_no, String rpt_type, String rpt_ctx){
	Article_report article_report = new Article_report();
	Articles articles = new Articles();
	articles.setArt_no(art_no);
	article_report.setArt_no(articles);
	MembersVO members = new MembersVO();
	members.setMem_no(rpt_mem_no);
	article_report.setRpt_mem_no(members);
	article_report.setRpt_type(rpt_type);
	article_report.setRpt_ctx(rpt_ctx);
	DAOInterface dao = new Article_reportDAO();
	boolean result = dao.executeInsert(article_report);
	return result;
	}
	public List<Article_report> pageAndRank(String page, int pageSize,String forum_no,String rpt_type){
		DAOInterface dao = new Article_reportDAO();
		String order = "rpt_time desc";
		String where = "forum_no="+forum_no+" and rpt_stat=0";
		if(rpt_type!=null){
			where = where + " and rpt_type='"+rpt_type+"'";
		}		
		List<Article_report> list = dao.pageAndRank(Integer.parseInt(page), pageSize, order, where);
		return list;
		
	}
	public boolean updateVO(String art_rpt_no){
		DAOInterface dao = new Article_reportDAO();
		Article_report article_report = new Article_report();
		article_report.setArt_rpt_no(art_rpt_no);
		boolean result = dao.updateByVO(article_report);
		return result;
	}
	public int countAllPage(String forum_no,int pageSize){
		Article_reportDAO dao = new Article_reportDAO();
		String SQL = "select count(*) from article_report where art_no in (select art_no from articles where forum_no="+forum_no+")";
		int result = dao.countBySQL(SQL);
		System.out.println(result);
		
		return (result-1) / pageSize +1;
	}
	
	
}
