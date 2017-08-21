package com.place_report.model;

import java.util.List;



public class PlaceReportService {

	private PlaceReportDAO_interface dao;
	public PlaceReportService(){
		dao = new PlaceReportDAO();
	}
	
	public PlaceReportVO addPR(String p_no,String mem_no,String pr_ctx,String ref_ctx,byte[] pr_pt){
		PlaceReportVO placeReportVO = new PlaceReportVO();
		placeReportVO.setP_no(p_no);
		placeReportVO.setMem_no(mem_no);
		placeReportVO.setPr_ctx(pr_ctx);
		placeReportVO.setRef_ctx(ref_ctx);
		placeReportVO.setPr_pt(pr_pt);
		dao.insert(placeReportVO);
		return placeReportVO;
	}
	public PlaceReportVO updatePR(String pr_no){
		PlaceReportVO placeReportVO = new PlaceReportVO();
		placeReportVO.setPr_no(pr_no);
		dao.update(placeReportVO);
		return placeReportVO;
	}
	public PlaceReportVO updatePRNum(String p_no){
		PlaceReportVO placeReportVO = new PlaceReportVO();
		placeReportVO.setP_no(p_no);
		dao.updatePRNum(placeReportVO);
		return placeReportVO;
	}
	public List<PlaceReportVO> getStat(Integer pr_stat){
		return dao.getStat(pr_stat);
	}
	public List<PlaceReportVO> getAll(){
		return dao.getAll();
	}
	
}
