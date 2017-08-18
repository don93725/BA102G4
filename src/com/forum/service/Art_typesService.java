package com.forum.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.don.util.TransData;
import com.forum.dao.Art_typesDAO;
import com.forum.domain.Art_types;

public class Art_typesService {
	public List<Art_types> getArt_types(String forum_no) {

		Art_typesDAO art_typesDAO = new Art_typesDAO();
		String sql = "Select * from art_types where forum_no=? and art_type_stat=0";
		Object[] param ={forum_no};
		List<Art_types> list = art_typesDAO.getVOBySQL(sql, param);				
		return list;

	}
}
