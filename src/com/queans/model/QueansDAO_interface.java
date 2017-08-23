package com.queans.model;

import java.util.*;




public interface QueansDAO_interface {
	public void insert(QueansVO queansVO);
    public void update(QueansVO queansVO);
    public void updateTwo(QueansVO queansVO);
    public void delete(String que_no);
    public QueansVO findByPrimaryKey(String que_no);
    public List<QueansVO> getAll();
    public List<QueansVO> getPartByType(String que_type);
    public List<QueansVO> getPartByDate(String upd_date);

}
