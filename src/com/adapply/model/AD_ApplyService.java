package com.adapply.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class AD_ApplyService {

	private AD_ApplyDAO_interface dao;
	
	public AD_ApplyService(){
		dao = new AD_ApplyDAO();
	}
	public AD_ApplyVO addAD(String mem_no,Date pay_date,String ad_name,String ad_url,Date ad_ondate,Date ad_offdate,String ad_ctx,byte[] ad_pt){
		AD_ApplyVO ad_ApplyVO = new AD_ApplyVO();
		ad_ApplyVO.setMem_no(mem_no);
		ad_ApplyVO.setPay_date(pay_date);
		ad_ApplyVO.setAd_name(ad_name);
		ad_ApplyVO.setAd_url(ad_url);
		ad_ApplyVO.setAd_ondate(ad_ondate);
		ad_ApplyVO.setAd_offdate(ad_offdate);
		ad_ApplyVO.setAd_ctx(ad_ctx);
		ad_ApplyVO.setAd_pt(ad_pt);
		dao.insert(ad_ApplyVO);
		return ad_ApplyVO;
	}
	public AD_ApplyVO updateAD(String ad_name,String ad_url,Date ad_ondate,Date ad_offdate,String ad_ctx,byte[] ad_pt,String ad_no){
		AD_ApplyVO ad_ApplyVO = new AD_ApplyVO();
		
		ad_ApplyVO.setAd_name(ad_name);
		ad_ApplyVO.setAd_url(ad_url);
		ad_ApplyVO.setAd_ondate(ad_ondate);
		ad_ApplyVO.setAd_offdate(ad_offdate);
		ad_ApplyVO.setAd_ctx(ad_ctx);
		ad_ApplyVO.setAd_pt(ad_pt);
		ad_ApplyVO.setAd_no(ad_no);
		dao.update(ad_ApplyVO);
		return ad_ApplyVO;
	}
	public AD_ApplyVO updateADStat(Integer arv_stat,String ad_no){
		AD_ApplyVO ad_ApplyVO = new AD_ApplyVO();
		
		ad_ApplyVO.setArv_stat(arv_stat);
		ad_ApplyVO.setAd_no(ad_no);
		dao.update_stat(ad_ApplyVO);
		return ad_ApplyVO;
	}
	public void deleteAD(String ad_no){
		dao.delete(ad_no);
	}
	public AD_ApplyVO getOneAD(String ad_no){
		return dao.findByPrimaryKey(ad_no);
	}
	public List<AD_ApplyVO> getAll(){
		return dao.getAll();
	}
	public Set<AD_ApplyVO> getStat(Integer arv_stat){
		return dao.getStat(arv_stat);
	}
}
