package com.membersreport.model;

import java.util.List;
import java.util.Set;

public class MembersReportService {

	private MembersReportDAO_interface dao;
	public MembersReportService(){
		dao = new MembersReportDAO();
	}
	
	public MembersReportVO addMR(String mr_def,String mr_pro,String mr_ctx,String ref_ctx,byte[] mr_pt){
		MembersReportVO membersReportVO = new MembersReportVO();
		membersReportVO.setMr_def(mr_def);
		membersReportVO.setMr_pro(mr_pro);
		membersReportVO.setMr_ctx(mr_ctx);
		membersReportVO.setRef_ctx(ref_ctx);
		membersReportVO.setMr_pt(mr_pt);
		dao.insert(membersReportVO);
		return membersReportVO;
	
	}
	public MembersReportVO updateMR(String mr_no){
		MembersReportVO membersReportVO = new MembersReportVO();
		membersReportVO.setMr_no(mr_no);
		dao.update(membersReportVO);
		return membersReportVO;
	}
	public MembersReportVO updateMRNum(String mr_no){
		MembersReportVO membersReportVO = new MembersReportVO();
		membersReportVO.setMr_no(mr_no);
		dao.updateMRNum(membersReportVO);
		return membersReportVO;
	}
	public void deleteMR(String mr_no){
		dao.delete(mr_no);
	}
	public MembersReportVO getOneMR(String mr_no){
		return dao.findByPrimaryKey(mr_no);
	}
	public Set<MembersReportVO> getStat(Integer mr_stat){
		return dao.getStat(mr_stat);
	}
	public List<MembersReportVO> getAll(){
		return dao.getAll();
	}
}
