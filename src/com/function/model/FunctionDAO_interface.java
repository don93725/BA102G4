package com.function.model;

import java.util.List;

public interface FunctionDAO_interface {
	
	public void insert(FunctionVO functionVO);
	public void update(FunctionVO functionVO);
	public void delete(String f_no);
	public FunctionVO findByPrimaryKey(String f_no);
	public List<FunctionVO> getAll();
}
