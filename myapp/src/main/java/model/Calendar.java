package model;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;




public class Calendar {
//	type never
	private ArrayList<String> listtime=new ArrayList<>();
	private ArrayList<String> liststr=new ArrayList<>();
	private ArrayList<String> listtype=new ArrayList<>();
	
	
	
	public Calendar(){
		
	}
	

	public ArrayList<String> getListtime() {
		return listtime;
	}

	public ArrayList<String> getListstr() {
		return liststr;
	}

	
	public ArrayList<String> getListtype() {
		return listtype;
	}


	public void save(String time,String str,String type){
		listtime.add(time);
		liststr.add(str);
		listtype.add(type);
	}
	public void delete(int index){
		liststr.remove(index);
		listtime.remove(index);
		listtype.remove(index);
	}
	
	public void edit(int index,String time,String str,String type){
		delete(index);
		save(time,str,type);
	
	}

	
}
