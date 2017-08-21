package com.function.model;

import java.util.List;

public class FunctionService {

	private FunctionDAO_interface dao;
	
	public FunctionService(){
		dao = new FunctionDAO();
	}
	
	public FunctionVO addFun(String f_no,String fname){
		
		FunctionVO functionVO = new FunctionVO();
		
		functionVO.setF_no(f_no);
		functionVO.setFname(fname);
		dao.insert(functionVO);
		return functionVO;
	}
	
	public FunctionVO updateFun(String f_no,String fname){
		FunctionVO functionVO = new FunctionVO();
		
		functionVO.setF_no(f_no);
		functionVO.setFname(fname);
		dao.update(functionVO);
		return functionVO;
	}
	
	public void deleteFun(String f_no){
		dao.delete(f_no);
	}
	
	public FunctionVO getOneFun(String f_no){
		return dao.findByPrimaryKey(f_no);
	}
	
	public List<FunctionVO> getAll(){
		return dao.getAll();
	}
}
