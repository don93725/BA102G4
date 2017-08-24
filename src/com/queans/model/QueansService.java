package com.queans.model;

import java.util.List;


public class QueansService {

	private QueansDAO_interface dao;

	public QueansService() {
		dao = new QueansDAO();
	}

	public QueansVO addQueans(String que_type, String que_title,
			String ans_ctx, byte[] que_photo) {

		QueansVO queansVO = new QueansVO();

		queansVO.setQue_type(que_type);
		queansVO.setQue_title(que_title);
		//queansVO.setUpd_date(upd_date);
		queansVO.setAns_ctx(ans_ctx);
		queansVO.setQue_photo(que_photo);
		dao.insert(queansVO);

		return queansVO;
	}

	public QueansVO updateQueans(String que_no, String que_type, String que_title,
			String ans_ctx, byte[] que_photo) {

		QueansVO queansVO = new QueansVO();

		queansVO.setQue_no(que_no);
		queansVO.setQue_type(que_type);
		queansVO.setQue_title(que_title);
		//queansVO.setUpd_date(upd_date);
		queansVO.setAns_ctx(ans_ctx);
		queansVO.setQue_photo(que_photo);
		
		if(que_photo!=null){
			   dao.update(queansVO);}
			else{dao.updateTwo(queansVO);}
		
		return queansVO;
	}

	public void deleteQueans(String que_no) {
		dao.delete(que_no);
	}

	public QueansVO getOneQueans(String que_no) {
		return dao.findByPrimaryKey(que_no);
	}

	public List<QueansVO> getAll() {
		return dao.getAll();
	}
	public List<QueansVO> getPartByTitle(String title) {
		System.out.println(title);
		return ((QueansDAO)dao).getPartByTitle(title);
	}
	public List<QueansVO> getPartByType(String que_type) {
		return dao.getPartByType(que_type);
	}
	public List<QueansVO> getPartByDate(String upd_date) {
		return dao.getPartByDate(upd_date);
	}
	public byte[] getPic(String que_no){
		return ((QueansDAO)dao).getPic(que_no);
	}
	
}

