package com.platinf.model;

import java.util.*;


public interface PlatinfDAO_interface {
	public void insert(PlatinfVO platinfVO);
    public void update(PlatinfVO platinfVO);
    public void updateTwo(PlatinfVO platinfVO);
    public void delete(String pin_no);
    public PlatinfVO findByPrimaryKey(String pin_no);
    public List<PlatinfVO> getAll();
    

}