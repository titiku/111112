package model;

public class Dateday {
	private String day;
	private String mount;
	private String year;
	private String text;
	
	public Dateday(String day,String mount,String year){
		this.day=day;
		this.mount= mount;
		this.year=year;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDay() {
		return day;
	}

	public String getMount() {
		return mount;
	}

	public String getYear() {
		return year;
	}
	
	
	
	
}
