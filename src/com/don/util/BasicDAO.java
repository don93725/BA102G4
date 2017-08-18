package com.don.util;

import java.util.List;

public class BasicDAO {
	
	public int countBySQL(String sql) {
		List list = (List) new SQLHelper().executeQuery(sql, null);
		Object[] obj =null;
		if(list.size()!=0){
			obj = (Object[]) list.get(0);
		}
		int num = 0;
		if (obj != null) {
			num = Integer.parseInt(obj[0].toString());
		}
		return num;
	}
	
	public List<Object[]> executeQuery(String sql,Object[] param){
		return new SQLHelper().executeQuery(sql, param);
	}
		
	public boolean executeUpdate(String sql,Object[] param){
		return new SQLHelper().executeUpdate(sql, param);
	}
	public boolean executeUpdate(String[] sql,Object[][] param){
		return new SQLHelper().executeUpdate(sql, param);
	}
	
	
}
