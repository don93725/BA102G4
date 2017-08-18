package com.don.inteface;

import java.util.List;

import com.forum.domain.Article_comments;

public interface ServiceIntface<T> {
	//自訂條件找分頁資料
	public List<Article_comments> getPageData(int thisPage,int pageSize,String art_no);
	
	
}
