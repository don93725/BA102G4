package com.don.inteface;

import java.util.List;

import com.forum.domain.Articles;

public interface DAOInterface<T> {
	//建置查詢
	public List getVOBySQL(String SQL,Object[] param);
	//建置查詢單筆
	public Object getVOByPK(String no);
	//建置查詢全部
	public List getAll();
	//算數量
	public int countAll();
	//建置刪除
	public boolean executeDelete(String no);
	//
	public List pageAndRank(int page,int pageSize,String order,String where);
	//建置分頁(彈性排序)
	public List pageAndRank(int page,int pageSize,String order);
	//建置分頁(PK排序)
	public List pageAndRankByPk(int page,int pageSize);
	//多行失敗區
	//建置修改
	public boolean updateByVO(T t);
	//建置新增
	public boolean executeInsert(T t);
	//還有隱藏的四個方法
}
