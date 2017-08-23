package com.annew.model;

import java.util.List;

public class AnnewService {

	private AnnewDAO_interface dao;

	public AnnewService() {
		dao = new AnnewDAO();
	}

	public AnnewVO addAnnew(String ann_title,
			String ann_ctx, byte[] ann_photo/*, byte[] att_no*/) {

		AnnewVO annewVO = new AnnewVO();

		
		annewVO.setAnn_title(ann_title);
		annewVO.setAnn_ctx(ann_ctx);
		annewVO.setAnn_photo(ann_photo);
//		annewVO.setAtt_no(att_no);
		dao.insert(annewVO);

		return annewVO;
	}

	public AnnewVO updateAnnew(String ann_no, String ann_title,
			String ann_ctx, byte[] ann_photo/*, byte[] att_no*/) {

		AnnewVO annewVO = new AnnewVO();

		annewVO.setAnn_no(ann_no);
		annewVO.setAnn_title(ann_title);
		annewVO.setAnn_ctx(ann_ctx);
		annewVO.setAnn_photo(ann_photo);
//		annewVO.setAtt_no(att_no);
		
		if(ann_photo!=null){
			   dao.update(annewVO);}
			else{dao.updateTwo(annewVO);}
		
		return annewVO;
	}

	public void deleteAnnew(String ann_no) {
		dao.delete(ann_no);
	}

	public AnnewVO getOneAnnew(String ann_no) {
		return dao.findByPrimaryKey(ann_no);
	}

	public List<AnnewVO> getAll() {
		return dao.getAll();
	}
	public List<AnnewVO> getPartByTitle(String title) {
		System.out.println(title);
		return ((AnnewDAO)dao).getPartByTitle(title);
	}
	public List<AnnewVO> getPartByDate(String ann_date) {
		return dao.getPartByDate(ann_date);
	}
	public byte[] getPic(String ann_no){
		return ((AnnewDAO)dao).getPic(ann_no);
	}
}
