package com.course.query;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CourseQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		
		
														
			if ("crs_time".equals(columnName) )
			//other
			
				aCondition = columnName + "=" + value;
									
		
			else if ("category".equals(columnName) ) //varchar
			
			
				aCondition = columnName + " like '%" + value + "%'";
							
		
			else if ("crs_date".equals(columnName))                         //date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
		
		}
		

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("crs_time", new String[] { "1" });
		map.put("category", new String[] { "A" });
		map.put("crs_date", new String[] { "2017-10-18" });
		
						
		

		
		
		String finalSQL = "SELECT * FROM course_time ct join course c ON ct.crs_no = c.crs_no join coaches coa on c.c_acc=coa.coa_acc left outer join place p  ON ct.p_no = p.p_no "
				          + CourseQuery.get_WhereCondition(map)
				          +"AND ct.status = 1"
				          +" "
				          + "order by ct_no";
		
		
		
		System.out.println("SQL = " + finalSQL);

	}

}
