package com.Java.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Java.Bean.info;
import com.Java.Dbutil.DBUtil;

public class dao {
	
	private static Connection conn=DBUtil.getConn();

	@SuppressWarnings("null")
	public static List<info> getinfo(String tblx, String tblx2, String num) {
		String sql=null;
		
		List<info> list=new ArrayList<info>();
		
		int a =Integer.valueOf(num);
		
		if(a<=0) {
			a=10;
		}
		
		if(tblx2.equals("addr")) {
			
			sql="select * from iptu order by sum desc limit 0,"+a;
			
		}else if(tblx2.equals("cishu")) {
			
			sql="select * from traffictu order by sum desc limit 0,"+a;
			
		}else {
			
			sql="select * from typetu order by sum desc limit 0,"+a;
			
		}
		
		System.out.println(sql);
		
		try {
			ResultSet rs=conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				info i=new info();
				i.setInfo(rs.getString("name"));
				i.setNum(rs.getString("sum"));
				//System.out.println(rs.getString("name"));
				//System.out.println(rs.getString("sum"));
				list.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
