package com.students.model;

import java.util.List;

import javax.servlet.http.Part;

import com.coaches.model.CoachesVO;
import com.members.model.MembersVO;
import com.tools.Tools;

public class StudentsService {
	private StudentsDAO_interface dao;
	public StudentsService() {
		dao = new StudentsDAO();
	}
	
	//把使用者輸入的資料放到VO
	public void addStudents(String mem_acc, String mem_nickname, String stu_psw, String stu_name, Integer stu_sex, String stu_id, String stu_mail, String stu_into, byte[] stu_pic_byte){
		MembersVO membersVO = new MembersVO();
		StudentsVO studentsVO = new StudentsVO();
		Tools tools = new Tools();
		membersVO.setMem_acc(mem_acc);
		membersVO.setMem_nickname(mem_nickname);
		
		studentsVO.setStu_psw(stu_psw);
		studentsVO.setStu_name(stu_name);
		studentsVO.setStu_sex(stu_sex);
		studentsVO.setStu_id(stu_id);
		studentsVO.setStu_mail(stu_mail);
		studentsVO.setStu_into(stu_into);
		studentsVO.setStu_pic(stu_pic_byte);
		
		dao.insert(membersVO, studentsVO);
	}

	//比對資料庫跟使用者輸入資料
	public StudentsVO loginStudents(String account, String password) {
			return dao.login(account, password);
	}
	
	public StudentsVO updateStudents(StudentsVO studentsVO, String stu_name, String stu_mail, String stu_into){
		studentsVO.setStu_name(stu_name);
		studentsVO.setStu_mail(stu_mail);
		studentsVO.setStu_into(stu_into);

		dao.update(studentsVO);
		return studentsVO;
	}

	public List<StudentsVO> getAll() {
		return dao.getAll();
	}

	public List<StudentsVO> searchStu(String search_Name, String search_Type){
		return dao.searchStu(search_Name, search_Type);
	}

	public StudentsVO look_search_mem(MembersVO membersVO) {
		return dao.look_search_mem(membersVO);
	}
}
