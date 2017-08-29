package com.annew.model;

import java.util.*;




public interface AnnewDAO_interface {
	public void insert(AnnewVO annewVO);
    public void update(AnnewVO annewVO);
    public void updateTwo(AnnewVO annewVO);
    public void delete(String ann_no);
    public AnnewVO findByPrimaryKey(String ann_no);
    public List<AnnewVO> getAll();
    public List<AnnewVO> getPartByDate(String ann_date);
	List<AnnewVO> getFrontAll();

}
