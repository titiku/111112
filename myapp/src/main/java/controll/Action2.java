package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Calendar;
import view.View;
import view.View2;

public class Action2 implements ActionListener {
	private View2 view2;
	private Calendar cal;
	private String str;
	private View view;
	JdbcSQLiteConnection db;

	public Action2(View2 view2, Calendar cal, String str, View view, JdbcSQLiteConnection db) {
		this.view2 = view2;
		this.cal = cal;
		this.str = str;
		this.view = view;
		this.db = db;
	}
	


	public boolean check(Calendar cal, String str) {
		for (int i = 0; i < cal.getListtime().size(); i++) {
			if (cal.getListtime().get(i).contains(str)) {
				return true;
			}
		}
		return false;
	}

	public boolean check2(Calendar cal, String s, DateFormat dateTimeFormat) {

		Date myTime;
		try {
			myTime = dateTimeFormat.parse(s);
			int day = myTime.getDay();
			String sday = "";
			if (day == 0) {
				sday = "Sunday";
			} else if (day == 1) {
				sday = "Monday";
			} else if (day == 2) {
				sday = "Tuesday";
			} else if (day == 3) {
				sday = "Wednesday";
			} else if (day == 4) {
				sday = "Thursday";
			} else if (day == 5) {
				sday = "Friday";
			} else if (day == 6) {
				sday = "Saturday";
			}

			String[] list = s.split("/");

			for (int i = 0; i < cal.getListtime().size(); i++) {
				if (cal.getListtime().get(i).contains(sday) && cal.getListtime().get(i).contains(list[3])) {
					return true;
				}else if (cal.getListtype().get(i).equals("Monthly")){
					String[] list2=cal.getListtime().get(i).split("/");
					if (list[0].equals(list2[1]) && list[3].equals(list2[2])){
						return true;
					}
					
				}
			}
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean check3(Calendar cal, String str, DateFormat dateTimeFormat) {
		Date myTime;
		int daya = 0;
		int dayb = 0;
		String[] l = str.split("/");
		String timea = l[3];
		String timeb;
		try {
			myTime = dateTimeFormat.parse(str);
			daya = myTime.getDay();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < cal.getListtime().size(); i++) {
			if (cal.getListtype().get(i).equals("Never")) {
				String[] list = cal.getListtime().get(i).split("/");
				int m = 0;
				if (list[1].equals("January")) {
					m = 1;
				} else if (list[1].equals("February")) {
					m = 2;
				} else if (list[1].equals("March")) {
					m = 3;
				} else if (list[1].equals("April")) {
					m = 4;
				} else if (list[1].equals("May")) {
					m = 5;
				} else if (list[1].equals("June")) {
					m = 6;
				} else if (list[1].equals("July")) {
					m = 7;
				} else if (list[1].equals("August")) {
					m = 8;
				} else if (list[1].equals("September")) {
					m = 9;
				} else if (list[1].equals("October")) {
					m = 10;
				} else if (list[1].equals("November")) {
					m = 11;
				} else if (list[1].equals("December")) {
					m = 12;
				}

				String s = list[0] + "/" + m + "/" + (Integer.parseInt(list[2]) + 543) + "/" + list[3];

				try {
					Date myTime2 = dateTimeFormat.parse(s);
					dayb = myTime2.getDay();
					timeb = list[3];
					if (daya == dayb && timea.equals(timeb)) {
						return true;
					}

				} catch (ParseException e) {

					e.printStackTrace();
				}
			} else if (cal.getListtype().get(i).equals("Weekly")) {
				String[] list = cal.getListtime().get(i).split("/");

				if (list[1].equals("Sunday")) {
					dayb = 0;
				} else if (list[1].equals("Monday")) {
					dayb = 1;
				} else if (list[1].equals("Tuesday")) {
					dayb = 2;
				} else if (list[1].equals("Wednesday")) {
					dayb = 3;
				} else if (list[1].equals("Thursday")) {
					dayb = 4;
				} else if (list[1].equals("Friday")) {
					dayb = 5;
				} else if (list[1].equals("Saturday")) {
					dayb = 6;
				}
				timeb = list[2];
				if (daya == dayb && timea.equals(timeb)) {
					return true;
				}

			}else if (cal.getListtype().get(i).equals("Monthly")){
				String[] list =cal.getListtime().get(i).split("/");
				String s1 =list[1]+"/";
	
				timeb=list[2];
				for (int x=0;x<5;x++){
					String y;
					if (x==0){
						y="2559";
					}else if (x==1){
						y="2560";
					}else if (x==2){
						y="2561";
					}else if (x==3){
						y="2562";
					}else {
						y="2563";
					}
					for (int j=1;j<13;j++){
						try {
							Date myTime3 = dateTimeFormat.parse(s1+j+"/"+y+"/11:13");
							if (daya==myTime3.getDay() && timea.equals(timeb)){
								return true;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
	
					}
				}
				
			}

		}
		return false;
	}
	
	
	public boolean check4(Calendar cal, String s, DateFormat dateTimeFormat){
		String[] list=s.split("/");
		int day=0;
		String sday = "";
		try {
			Date myTime = dateTimeFormat.parse(s);
			day=myTime.getDay();
			if (day == 0) {
				sday = "Sunday";
			} else if (day == 1) {
				sday = "Monday";
			} else if (day == 2) {
				sday = "Tuesday";
			} else if (day == 3) {
				sday = "Wednesday";
			} else if (day == 4) {
				sday = "Thursday";
			} else if (day == 5) {
				sday = "Friday";
			} else if (day == 6) {
				sday = "Saturday";
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (int i=0;i<cal.getListtime().size();i++){
			
			if (cal.getListtype().get(i).equals("Never")){
				String[] list2=cal.getListtime().get(i).split("/");
				if (list[0].equals(list2[0]) && list[3].equals(list2[3])){
					return true;
				}
			}else if (cal.getListtype().get(i).equals("Weekly")){
					String[] list2=cal.getListtime().get(i).split("/");
					
					for (int x=0;x<5;x++){
						String y;
						if (x==0){
							y="2559";
						}else if (x==1){
							y="2560";
						}else if (x==2){
							y="2561";
						}else if (x==3){
							y="2562";
						}else {
							y="2563";
						}
						for (int j=1;j<13;j++){
							try {
								Date myTime3 = dateTimeFormat.parse(list[0]+"/"+j+"/"+y+"/11:13");
								if (day==myTime3.getDay() && list[3].equals(list2[2])){
									return true;
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
		
						}
					}
	
			}else if (cal.getListtype().get(i).equals("Monthly")){
				
				String[] list2=cal.getListtime().get(i).split("/");
				if (list2[1].equals(list[0])  && list2[2].equals(list[3])){
					return true;
				}
		}
			
			
			
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(view2.getSave())) {
			str = str + "/" + view2.getHh().getSelectedItem().toString() + ":"
					+ view2.getMm().getSelectedItem().toString();

			ArrayList<String> timeday = new ArrayList<>();



			for (int i = 0; i < cal.getListtype().size(); i++) {
				if (cal.getListtype().get(i).equals("Daily")) {
					String[] a = cal.getListtime().get(i).split("/");
					timeday.add(a[1]);
				}
				

			}

			DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy/HH:mm");
			String[] list = str.split("/");
			int m = 0;
			if (list[1].equals("January")) {
				m = 1;
			} else if (list[1].equals("February")) {
				m = 2;
			} else if (list[1].equals("March")) {
				m = 3;
			} else if (list[1].equals("April")) {
				m = 4;
			} else if (list[1].equals("May")) {
				m = 5;
			} else if (list[1].equals("June")) {
				m = 6;
			} else if (list[1].equals("July")) {
				m = 7;
			} else if (list[1].equals("August")) {
				m = 8;
			} else if (list[1].equals("September")) {
				m = 9;
			} else if (list[1].equals("October")) {
				m = 10;
			} else if (list[1].equals("November")) {
				m = 11;
			} else if (list[1].equals("December")) {
				m = 12;
			}
			String s = list[0] + "/" + m + "/" + (Integer.parseInt(list[2]) + 543) + "/" + list[3];

//			System.out.println(timeday + "Time");
//			System.out.println(s + "STR");


			if (cal.getListtime().contains(str)) {
				view2.getFrame2().setVisible(false);
				JOptionPane.showMessageDialog(null, "Error//There is time in this note.");
			} else if (timeday.contains(
					view2.getHh().getSelectedItem().toString() + ":" + view2.getMm().getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(null, "Error//There is time in this note.");
				view2.getFrame2().setVisible(false);
			} else if (view2.getType().getSelectedItem().toString().equals("Daily") && check(cal,
					view2.getHh().getSelectedItem().toString() + ":" + view2.getMm().getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(null, "Error//There is time in this note.");
				view2.getFrame2().setVisible(false);
			} else if (view2.getType().getSelectedItem().toString().equals("Weekly") && check3(cal, s, dateTimeFormat)) {
				JOptionPane.showMessageDialog(null, "Error//There is time in this note.");
				view2.getFrame2().setVisible(false);
			} else if (view2.getType().getSelectedItem().toString().equals("Never") && check2(cal, s, dateTimeFormat)) {
				JOptionPane.showMessageDialog(null, "Error//There is time in this note.");
				view2.getFrame2().setVisible(false);
			
			}else if(view2.getType().getSelectedItem().toString().equals("Monthly") && check4(cal, s, dateTimeFormat)){
				JOptionPane.showMessageDialog(null, "Error//There is time in this note.");
				view2.getFrame2().setVisible(false);
			}else {
				if (view2.getHh().getSelectedItem().toString().equals("select time")
						|| view2.getMm().getSelectedItem().toString().equals("select time")
						|| view2.getType().getSelectedItem().toString().equals("select type")) {
					view2.getFrame2().setVisible(false);
					JOptionPane.showMessageDialog(null, "Error//plese select time and select type");
				} else {

					if (view2.getType().getSelectedItem().equals("Daily")) {
						String[] a = str.split("/");

						str = "Daily/" + a[3];
					} else if (view2.getType().getSelectedItem().equals("Weekly")) {

						Date myTime;
						try {
							myTime = dateTimeFormat.parse(s);

							int day = myTime.getDay();
							String sday = "";
							if (day == 0) {
								sday = "Sunday";
							} else if (day == 1) {
								sday = "Monday";
							} else if (day == 2) {
								sday = "Tuesday";
							} else if (day == 3) {
								sday = "Wednesday";
							} else if (day == 4) {
								sday = "Thursday";
							} else if (day == 5) {
								sday = "Friday";
							} else if (day == 6) {
								sday = "Saturday";
							}

							str = "Weekly/" + sday + "/" + list[3];

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}else if (view2.getType().getSelectedItem().equals("Monthly")){
					
						
						str = "Monthly/" + list[0] + "/" + list[3];
					}

					cal.save(str, view2.getArea().getText(), view2.getType().getSelectedItem().toString());

					view.getCombotime().addItem(str);

					view2.getFrame2().setVisible(false);

					 System.out.println(cal.getListtime());
					 System.out.println(cal.getListstr());
					 System.out.println(cal.getListtype());

					try {
						Class.forName("org.sqlite.JDBC");
						String dbURL = "jdbc:sqlite:store.db";
						Connection conn = DriverManager.getConnection(dbURL);
						if (conn != null) {

							DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
							// String query = "insert into Calendar\nvalues
							// ('1134','155')";
							String query = "insert into Calendar\nvalues ('" + str + "','" + view2.getArea().getText()
									+ "','" + view2.getType().getSelectedItem().toString() + "')";

							Statement statement = conn.createStatement();
							statement.execute(query);

							conn.close();
						}

					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

				}
			}
		}

		if (src.equals(view2.getCancel())) {

			view2.getFrame2().setVisible(false);
		}

	}



}
