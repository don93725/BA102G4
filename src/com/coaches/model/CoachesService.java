package com.coaches.model;

import java.util.List;

import javax.servlet.http.Part;

import com.members.model.MembersVO;
import com.tools.Tools;

public class CoachesService {
	private CoachesDAO_interface dao;
	public CoachesService(){
		dao = new CoachesDAO();
	}
	
	//把使用者輸入的資料放到VO
	public void addCoaches(String mem_acc, String mem_nickname, String coa_psw, String coa_name, Integer coa_sex, String coa_id, String coa_mail, String coa_into, byte[] coa_pic_byte){
		MembersVO membersVO = new MembersVO();
		CoachesVO coachesVO = new CoachesVO();
		Tools tools = new Tools();
		membersVO.setMem_acc(mem_acc);
		membersVO.setMem_nickname(mem_nickname);
		
		coachesVO.setCoa_psw(coa_psw);
		coachesVO.setCoa_name(coa_name);
		coachesVO.setCoa_sex(coa_sex);
		coachesVO.setCoa_id(coa_id);
		coachesVO.setCoa_mail(coa_mail);
		coachesVO.setCoa_into(coa_into);
		coachesVO.setCoa_pic(coa_pic_byte);
		
		dao.insert(membersVO, coachesVO);
	}
	
	//比對資料庫跟使用者輸入資料
	public CoachesVO loginCoaches(String account, String password) {
		return dao.login(account, password);
	}

	//更新會員資料
	public CoachesVO updateCoaches(CoachesVO coachesVO, String coa_name, String coa_mail, String coa_into){
		coachesVO.setCoa_name(coa_name);
		coachesVO.setCoa_mail(coa_mail);
		coachesVO.setCoa_into(coa_into);

		dao.update(coachesVO);
		return coachesVO;
	}

	//找教練頁面
	public List<CoachesVO> getAll() {
		return dao.getAll();
	}

	public List<CoachesVO> searchCoa(String search_Name, String search_Type){
		return dao.searchCoa(search_Name, search_Type);
	}

	public CoachesVO look_search_mem(MembersVO membersVO) {
		return dao.look_search_mem(membersVO);
	}
	
	public List<CoachesVO> getAllBySta(Integer coa_sta) {
		return dao.getAllBySta(coa_sta);
	}
	public void updateCoaApply(Integer coa_sta,String coa_acc){
		 dao.setSta(coa_sta, coa_acc);
	}
	
	
}

