package com.fitkw.model;

import java.util.*;


public interface FitkwDAO_interface {
	public void insert(FitkwVO fitkwVO);
    public void update(FitkwVO fitkwVO);
    public void updateTwo(FitkwVO fitkwVO);
    public void delete(String fik_no);
    public FitkwVO findByPrimaryKey(String fik_no);
    public List<FitkwVO> getAll();
    public List<FitkwVO> getPartByType(String fik_type);
    public List<FitkwVO> getPartByDate(String upd_date);
	List<FitkwVO> getFrontAll();

}