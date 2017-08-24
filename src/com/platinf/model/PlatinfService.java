package com.platinf.model;

import java.util.List;


public class PlatinfService {

	private PlatinfDAO_interface dao;

	public PlatinfService() {
		dao = new PlatinfDAO();
	}

	public PlatinfVO addPlatinf(String com_address, String cp_no, String cs_email, 
			String pr_policy, byte[] pin_photo) {

		PlatinfVO platinfVO = new PlatinfVO();

		platinfVO.setCom_address(com_address);
		platinfVO.setCp_no(cp_no);
		platinfVO.setCs_email(cs_email);
		platinfVO.setPr_policy(pr_policy);
		//platinfVO.setUpd_date(upd_date);
		platinfVO.setPin_photo(pin_photo);
		dao.insert(platinfVO);

		return platinfVO;
	}

	public PlatinfVO updatePlatinf(String pin_no, String com_address, String cp_no, 
			String cs_email, String pr_policy, byte[] pin_photo) {

		PlatinfVO platinfVO = new PlatinfVO();

		platinfVO.setPin_no(pin_no);
		platinfVO.setCom_address(com_address);
		platinfVO.setCp_no(cp_no);
		platinfVO.setCs_email(cs_email);
		platinfVO.setPr_policy(pr_policy);
		//platinfVO.setUpd_date(upd_date);
		platinfVO.setPin_photo(pin_photo);
		
		if(pin_photo!=null){
			dao.update(platinfVO);}
		else{dao.updateTwo(platinfVO);}

		return platinfVO;
	}

	public void deletePlatinf(String pin_no) {
		dao.delete(pin_no);
	}

	public PlatinfVO getOnePlatinf(String pin_no) {
		return dao.findByPrimaryKey(pin_no);
	}

	public List<PlatinfVO> getAll() {
		return dao.getAll();
	}
	public byte[] getPic(String pin_no){
		return ((PlatinfDAO)dao).getPic(pin_no);
	}
}

