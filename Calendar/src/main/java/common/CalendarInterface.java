package common;

import java.io.Serializable;
import java.util.ArrayList;

import server.Dateday;

public interface CalendarInterface {
	public ArrayList<DatedayInterface> getList() ;
	public void load();
	public void update(String day,String month,String year,String text);
	public void delete(String day, String month, String year);
	public void add(String day, String month, String year,String text);
}
