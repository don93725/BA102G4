package com.students.model;

import java.util.List;

import com.members.model.MembersVO;

public interface StudentsDAO_interface {
	public void insert(MembersVO membersVO, StudentsVO studentsVO);
	public StudentsVO login(String stu_account, String stu_password);
	public void update(StudentsVO studentsVO);
	public List<StudentsVO> getAll();
	public List<StudentsVO> searchStu(String search_Name, String search_Type);
	public StudentsVO look_search_mem(MembersVO membersVO);
	public void update_forPic(String stu_acc, byte[] stu_pic_byte);
}
