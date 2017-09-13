package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import model.Calendar;

import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class View {

	private JFrame frame;
	private Calendar cal;
	private JComboBox comboMouth;
	private ArrayList<JButton> listday;
	private JPanel panel;
	private JLabel day1, day2, day3, day4, day5, day6, day7;
	private JComboBox combotime,combodelete;
	JButton btnNote;
	JComboBox comboyear;
	
	
	public JComboBox getComboyear() {
		return comboyear;
	}


	public View(Calendar cal) {
		initialize();
		this.cal = cal;
	}


	private void initialize() {
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(750, 600));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNote = new JButton("Note");
		btnNote.setBounds(478, 37, 144, 59);
		frame.getContentPane().add(btnNote);

		JLabel lblMouth = new JLabel("Mouth");
		lblMouth.setBounds(128, 41, 46, 14);
		frame.getContentPane().add(lblMouth);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(261, 41, 46, 14);
		frame.getContentPane().add(lblYear);

		 comboyear = new JComboBox();
		comboyear.addItem("2016");
		comboyear.addItem("2017");
		comboyear.addItem("2018");
		comboyear.addItem("2019");
		comboyear.addItem("2020");
		comboyear.setBounds(257, 57, 88, 39);
		frame.getContentPane().add(comboyear);

		comboMouth = new JComboBox();
		comboMouth.setBounds(101, 66, 102, 20);
		comboMouth.addItem("January");
		comboMouth.addItem("February");
		comboMouth.addItem("March");
		comboMouth.addItem("April");
		comboMouth.addItem("May");
		comboMouth.addItem("June");
		comboMouth.addItem("July");
		comboMouth.addItem("August");
		comboMouth.addItem("September");
		comboMouth.addItem("October");
		comboMouth.addItem("November");
		comboMouth.addItem("December");

		frame.getContentPane().add(comboMouth);

		day1 = new JLabel("Sunday");
		day1.setBounds(68, 117, 58, 26);
		frame.getContentPane().add(day1);

		day2 = new JLabel("Monday");
		day2.setBounds(159, 117, 58, 26);
		frame.getContentPane().add(day2);

		day3 = new JLabel("Tuesday");
		day3.setBounds(249, 117, 58, 26);
		frame.getContentPane().add(day3);

		day4 = new JLabel("Wednesday");
		day4.setBounds(328, 117, 76, 26);
		frame.getContentPane().add(day4);

		day5 = new JLabel("Thursday");
		day5.setBounds(425, 117, 58, 26);
		frame.getContentPane().add(day5);

		day6 = new JLabel("Friday");
		day6.setBounds(520, 117, 58, 26);
		frame.getContentPane().add(day6);

		day7 = new JLabel("Saturday");
		day7.setBounds(602, 117, 58, 26);
		frame.getContentPane().add(day7);

		panel = new JPanel();
		panel.setBounds(47, 154, 625, 356);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(6, 0));
		
		combotime = new JComboBox();
		combotime.setBounds(478, 66, 144, 20);
		combotime.addItem("Select");
//		frame.getContentPane().add(combotime);
		


		listday = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			JButton but = new JButton("");
			but.setVisible(false);
			panel.add(but);
		}
		for (int i = 1; i < 37-5; i++) {
			JButton but = new JButton("");
				but = new JButton("" + (i));
				listday.add(but);
			panel.add(but);
		}
		for (int i = 1; i < 7; i++) {
			JButton but = new JButton("");
			but.setVisible(false);
			panel.add(but);
		}
		
		

		frame.pack();

	}

	public JFrame getFrame() {
		return frame;
	}


	public JButton getBtnNote() {
		return btnNote;
	}


	public JLabel getDay7() {
		return day7;
	}


	public JLabel getDay1() {
		return day1;
	}

	public JComboBox getCombotime() {
		return combotime;
	}

	public JLabel getDay2() {
		return day2;
	}

	public JLabel getDay3() {
		return day3;
	}

	public JLabel getDay4() {
		return day4;
	}

	public JLabel getDay5() {
		return day5;
	}

	public JLabel getDay6() {
		return day6;
	}
	public ArrayList<JButton> getListday() {
		return listday;
	}

	public JPanel getPanel() {
		return panel;
	}
	public JComboBox getComboBox() {
		return comboMouth;
	}
	
	public void loadSave(){
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:store.db";
			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				String query = "Select * from Calendar";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while (resultSet.next()) {

					cal.getListtime().add(resultSet.getString(1));
					cal.getListstr().add(resultSet.getString(2));
					cal.getListtype().add(resultSet.getString(3));
					combotime.addItem(resultSet.getString(1));
				}
				System.out.println(cal.getListtime()+"LOAD");
				System.out.println(cal.getListstr()+"LOAD");
				System.out.println(cal.getListtype()+"LOAD");
				conn.close();
			}

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
