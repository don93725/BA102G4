package com.fitkw.model;

import java.util.List;

public class FitkwService {

	private FitkwDAO_interface dao;
	

	public FitkwService() {
		dao = new FitkwDAO();
		
	}

	public FitkwVO addFitkw(String fik_type, String fik_title,
			String fik_ctx, byte[] fik_photo) {

		FitkwVO fitkwVO = new FitkwVO();

		fitkwVO.setFik_type(fik_type);
		fitkwVO.setFik_title(fik_title);
		//fitkwVO.setUpd_date(upd_date);
		fitkwVO.setFik_ctx(fik_ctx);
		fitkwVO.setFik_photo(fik_photo);
		dao.insert(fitkwVO);

		return fitkwVO;
	}

	public FitkwVO updateFitkw(String fik_no, String fik_type, String fik_title,
			String fik_ctx, byte[] fik_photo) {

		FitkwVO fitkwVO = new FitkwVO();

		fitkwVO.setFik_no(fik_no);
		fitkwVO.setFik_type(fik_type);
		fitkwVO.setFik_title(fik_title);
		//fitkwVO.setUpd_date(upd_date);
		fitkwVO.setFik_ctx(fik_ctx);
		fitkwVO.setFik_photo(fik_photo);
		
		if(fik_photo!=null){
		   dao.update(fitkwVO);}
		else{dao.updateTwo(fitkwVO);}

		return fitkwVO;
	}

	public void deleteFitkw(String fik_no) {
		dao.delete(fik_no);
	}

	public FitkwVO getOneFitkw(String fik_no) {
		return dao.findByPrimaryKey(fik_no);
	}

	public List<FitkwVO> getAll() {
		return dao.getAll();
	}
	public List<FitkwVO> getPartByTitle(String title) {
		System.out.println(title);
		return ((FitkwDAO)dao).getPartByTitle(title);
	}
	public List<FitkwVO> getPartByType(String fik_type) {
		return dao.getPartByType(fik_type);
	}
	public List<FitkwVO> getPartByDate(String upd_date) {
		return dao.getPartByDate(upd_date);
	}
	public byte[] getPic(String fik_no){
		return ((FitkwDAO)dao).getPic(fik_no);
	}
}
