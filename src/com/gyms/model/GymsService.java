package com.gyms.model;

import java.util.List;

import javax.servlet.http.Part;

import com.members.model.MembersVO;
import com.students.model.StudentsVO;
import com.tools.Tools;

public class GymsService {
	private GymsDAO_interface dao;
	public GymsService(){
		dao = new GymsDAO();
	}
	
	//把使用者輸入的資料放到VO
	public void addGyms(String gym_name, String mem_nickname, String mem_acc, String gym_psw, String gym_mail, String gym_into, byte[] gym_pic_byte, String gym_add, String gym_latlng){
		
		MembersVO membersVO = new MembersVO();
		GymsVO gymsVO = new GymsVO();
		membersVO.setMem_acc(mem_acc);
		membersVO.setMem_nickname(mem_nickname);
		
		gymsVO.setGym_name(gym_name);
		gymsVO.setGym_psw(gym_psw);
		gymsVO.setGym_mail(gym_mail);
		gymsVO.setGym_into(gym_into);
		gymsVO.setGym_pic(gym_pic_byte);
		gymsVO.setGym_add(gym_add);
		gymsVO.setGym_latlng(gym_latlng);
		dao.insert(membersVO, gymsVO);
	}
	
	//比對資料庫跟使用者輸入資料 有會員就回傳審核狀態，沒有就回傳2
	public GymsVO loginGyms(String account, String password) {
		return dao.login(account, password);
	}
	
	public GymsVO updateGyms(GymsVO gymsVO,String gym_name, String gym_mail, String gym_into, String gym_add, String gym_latlng) {
		gymsVO.setGym_name(gym_name);
		gymsVO.setGym_mail(gym_mail);
		gymsVO.setGym_into(gym_into);
		gymsVO.setGym_add(gym_add);
		gymsVO.setGym_latlng(gym_latlng);

		dao.update(gymsVO);
		return gymsVO;
	}
	public List<GymsVO> getAll() {
		return ((GymsDAO)dao).getAll();
	}
	public List<GymsVO> searchGyms(String search_Name,String search_Type) {
		return ((GymsDAO)dao).searchGyms(search_Name, search_Type);
	}
	public List<GymsVO> getAllBySta(Integer gym_sta) {
		return dao.getAllBySta(gym_sta);
	}
	public void updateGymApply(Integer gym_sta,String gym_acc){
		 dao.setSta(gym_sta, gym_acc);
	}
	public GymsVO look_search_mem(MembersVO membersVO) {
		return dao.look_search_mem(membersVO);
	}
	
}
