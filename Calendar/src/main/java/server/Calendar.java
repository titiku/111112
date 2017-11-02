package server;

import java.io.Serializable;
import java.util.ArrayList;

import common.CalendarInterface;
import common.DatedayInterface;

public class Calendar implements CalendarInterface{
	private ArrayList<DatedayInterface> list;
	Database db;
	
	public Calendar(Database db){
		list=new ArrayList<>();
		this.db=db;

	}
	public ArrayList<DatedayInterface> getList() {
		return list;
	}
	
	public void load(){
		list=db.loadDatabase();
	}
	
	public void update(String day,String month,String year,String text){
		db.updateDatabase(day, month, year, text);

	}

	public void delete(String day, String month, String year){
		db.delteDatabase(day, month, year);
	
		
	}
	
	public void add(String day, String month, String year,String text){
		db.addDatabase(day, month, year, text);
	
		
	}
	
}
