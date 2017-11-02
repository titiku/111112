package client;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.util.SocketUtils;
import org.w3c.dom.ls.LSInput;

import server.Calendar;
import server.Database;
import server.Dateday;

import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import common.CalendarInterface;
import common.DatedayInterface;

public class View {

	private JFrame frame;
	private CalendarInterface cal;
	private JComboBox comboMouth,comboyear;
	private ArrayList<JButton> listday;
	private JPanel panel;
	private JLabel day1, day2, day3, day4, day5, day6, day7;
	private JButton btnAdd,btnFind;
	private Listen listen;

	public View(CalendarInterface cal) {
		listen = new Listen();
		this.cal = cal;
		initialize();
	}
	private void initialize() {
		cal.load();
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(750, 600));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnAdd = new JButton("ADD");
		btnAdd.setBounds(478, 37, 70, 40);
		frame.getContentPane().add(btnAdd);
		btnAdd.addActionListener(listen);
		
		 btnFind = new JButton("Find");
		btnFind.setBounds(580, 37, 70, 40);
		frame.getContentPane().add(btnFind);
		btnFind.addActionListener(listen);

		JLabel lblMouth = new JLabel("Mouth");
		lblMouth.setBounds(128, 41, 46, 14);
		frame.getContentPane().add(lblMouth);

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(261, 41, 46, 14);
		frame.getContentPane().add(lblYear);

		comboyear = new JComboBox();
		comboyear.setBounds(257, 57, 88, 39);
		frame.getContentPane().add(comboyear);

		comboMouth = new JComboBox();
		comboMouth.setBounds(101, 66, 102, 20);
		frame.getContentPane().add(comboMouth);

		comboMouth.addItem("Jan");
		comboMouth.addItem("Feb");
		comboMouth.addItem("Mar");
		comboMouth.addItem("Apr");
		comboMouth.addItem("May");
		comboMouth.addItem("Jun");
		comboMouth.addItem("Jul");
		comboMouth.addItem("Aug");
		comboMouth.addItem("Sep");
		comboMouth.addItem("Oct");
		comboMouth.addItem("Nov");
		comboMouth.addItem("Dec");
		comboyear.addItem("2016");
		comboyear.addItem("2017");
		comboyear.addItem("2018");
		comboyear.addItem("2019");
		comboyear.addItem("2020");

		comboyear.addItemListener(listen);
		comboMouth.addItemListener(listen);

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



		listday = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			JButton but = new JButton("");
			but.setVisible(false);

			panel.add(but);
		}
		for (int i = 1; i < 37 - 5; i++) {
			JButton but = new JButton("");
			but = new JButton("" + (i));
			but.setBackground(Color.white);
			but.addActionListener(listen);
			listday.add(but);
			panel.add(but);
		}
		for (int i = 1; i < 7; i++) {
			JButton but = new JButton("");
			but.setVisible(false);
			panel.add(but);
		}
		
		refess();
		frame.pack();

	}

	public class Listen implements ItemListener, ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			
			if (src.equals(btnFind)) {
				JFrame frame3 = new JFrame("Find");
				frame3.setBounds(700, 0, 500, 400);
				frame3.setVisible(true);

				JPanel panel3 = new JPanel();
				panel3.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame3.add(panel3);
				panel3.setLayout(null);

				JTextArea area = new JTextArea();
				area.setEditable(false);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportView(area);
				scrollPane.setBounds(40, 20, 400, 200);
				panel3.add(scrollPane);

				JDateChooser dateChooser = new JDateChooser();
				dateChooser.setBounds(250, 260, 130, 30);
				panel3.add(dateChooser);

				JButton button = new JButton("Find");
				button.setBounds(80, 260, 90, 30);
				panel3.add(button);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] data = dateChooser.getDate().toString().split(" ");
						String mount=data[1];
						String year=data[5];
						String day=data[2];
						int c=0;
						for (DatedayInterface d:cal.getList()){
							if (d.getDay().equals(day) && d.getMount().equals(mount) && d.getYear().equals(year)){
								area.setText(d.getText());
								c=1;
							}
						}
						if (c==0){
							area.setText("NULL");
						}
						
						
					}
				});

			}
			

			for (JButton but : listday) {
				if (src.equals(but)) {
					JFrame frame3 = new JFrame("ADD");
					frame3.setBounds(700, 0, 500, 400);
					frame3.setVisible(true);

					frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					JPanel panel3 = new JPanel();
					panel3.setBorder(new EmptyBorder(5, 5, 5, 5));
					frame3.add(panel3);
					panel3.setLayout(null);

					JTextArea area = new JTextArea();

					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setViewportView(area);
					scrollPane.setBounds(40, 20, 400, 200);
					panel3.add(scrollPane);


					for (DatedayInterface d : cal.getList()) {
						if ((but.getLabel().equals(d.getDay()) || ("0" + but.getLabel()).equals(d.getDay()))
								&& comboMouth.getSelectedItem().equals(d.getMount())
								&& comboyear.getSelectedItem().equals(d.getYear())) {
							area.setText(d.getText());
							break;
						}
					}

					JButton button = new JButton("OK");
					button.setBounds(100, 270, 90, 30);
					panel3.add(button);
					button.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							for (DatedayInterface d : cal.getList()) {
								if ((but.getLabel().equals(d.getDay()) || ("0" + but.getLabel()).equals(d.getDay()))
										&& comboMouth.getSelectedItem().equals(d.getMount())
										&& comboyear.getSelectedItem().equals(d.getYear())) {
									d.setText(area.getText());
									cal.update(d.getDay(), d.getMount(), d.getYear(), d.getText());
									cal.load();
									break;
								}
							}

							frame3.setVisible(false);
						}
					});

					JButton button2 = new JButton("Delete");
					button2.setBounds(280, 270, 90, 30);
					panel3.add(button2);
					button2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							for (DatedayInterface d : cal.getList()) {
								if ((but.getLabel().equals(d.getDay()) || ("0" + but.getLabel()).equals(d.getDay()))
										&& comboMouth.getSelectedItem().equals(d.getMount())
										&& comboyear.getSelectedItem().equals(d.getYear())) {
									cal.getList().remove(d);
									
									but.setBackground(Color.white);
									cal.delete(d.getDay(),d.getMount(),d.getYear());
									cal.load();
									break;
								}
							}

							frame3.setVisible(false);
						}
					});

				}
			}

			if (src.equals(btnAdd)) {
				JFrame frame2 = new JFrame("ADD");
				frame2.setBounds(700, 0, 500, 400);
				frame2.setVisible(true);

//				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JPanel panel2 = new JPanel();
				panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame2.add(panel2);
				panel2.setLayout(null);

				JTextArea area = new JTextArea();

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportView(area);
				scrollPane.setBounds(40, 20, 400, 200);
				panel2.add(scrollPane);

				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(80, 240, 90, 30);
				panel2.add(comboBox);

				comboBox.addItem("Never");
				comboBox.addItem("Daily");
				comboBox.addItem("Weekly");
				comboBox.addItem("Monthly");

				JDateChooser dateChooser = new JDateChooser();
				dateChooser.setBounds(250, 240, 130, 30);
				panel2.add(dateChooser);

				JButton button = new JButton("OK");
				button.setBounds(80, 290, 90, 30);
				panel2.add(button);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						if (comboBox.getSelectedItem().equals("Never")) {
							String[] data = dateChooser.getDate().toString().split(" ");
							addNever(data,area);

						}else if (comboBox.getSelectedItem().equals("Daily")) {
							String[] data = dateChooser.getDate().toString().split(" ");
							String mount=data[1];
							String year=data[5];
							int day=Integer.parseInt(data[2]);
							boolean check=false;
							
							if (mount.equals("Jan")){
								int c=1;
								if (check==false){
									c=day;
								}
								
								for (int i=c;i<32;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Feb";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Feb")){
								int c=1;
								if (check==false){
									c=day;
								}
								
								int dd = 28;
								if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
									dd = 29;
								} else if (Integer.parseInt(year) % 100 == 0 && Integer.parseInt(year) % 400 == 0) {
									dd = 29;
								}
								for (int i=c;i<dd+1;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Mar";
								data[1]=mount;
								check=true;
								
								
							}
							if(mount.equals("Mar")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Apr";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Apr")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="May";
								data[1]=mount;
								check=true;
								
							}
							if(mount.equals("May")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Jun";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Jun")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Jul";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Jul")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Aug";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Aug")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Sep";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Sep")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Oct";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Oct")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Nov";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Nov")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									addNever(data, area);
								}
								mount="Dec";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Dec")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									addNever(data, area);
								}
//								mount="Jun";
//								data[1]=mount;
								check=true;
							}
							

							
							
						}else if (comboBox.getSelectedItem().equals("Weekly")) {
							String[] data = dateChooser.getDate().toString().split(" ");
							String mount=data[1];
							String year=data[5];
							int day=Integer.parseInt(data[2]);
							boolean check=false;
							int count=0;
							
							if (mount.equals("Jan")){
								int c=1;
								if (check==false){
									c=day;
								}
								
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Feb";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Feb")){
								int c=1;
								if (check==false){
									c=day;
								}
								
								int dd = 28;
								if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
									dd = 29;
								} else if (Integer.parseInt(year) % 100 == 0 && Integer.parseInt(year) % 400 == 0) {
									dd = 29;
								}
								for (int i=c;i<dd+1;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Mar";
								data[1]=mount;
								check=true;
								
								
							}
							if(mount.equals("Mar")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Apr";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Apr")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="May";
								data[1]=mount;
								check=true;
								
							}
							if(mount.equals("May")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Jun";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Jun")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Jul";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Jul")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Aug";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Aug")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Sep";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Sep")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Oct";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Oct")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Nov";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Nov")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
								mount="Dec";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Dec")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (count%7==0){
										addNever(data, area);
									}
									count+=1;
								}
//								mount="Jun";
//								data[1]=mount;
								check=true;
							}
							
							
							
						}else if (comboBox.getSelectedItem().equals("Monthly")) {
							String[] data = dateChooser.getDate().toString().split(" ");
							String mount=data[1];
							String year=data[5];
							int day=Integer.parseInt(data[2]);
							String day2=data[2];
							
							boolean check=false;
							
							if (mount.equals("Jan")){
								int c=1;
								if (check==false){
									c=day;
								}
								
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Feb";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Feb")){
								int c=1;
								if (check==false){
									c=day;
								}
								
								int dd = 28;
								if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
									dd = 29;
								} else if (Integer.parseInt(year) % 100 == 0 && Integer.parseInt(year) % 400 == 0) {
									dd = 29;
								}
								for (int i=c;i<dd+1;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Mar";
								data[1]=mount;
								check=true;
								
								
							}
							if(mount.equals("Mar")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Apr";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Apr")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="May";
								data[1]=mount;
								check=true;
								
							}
							if(mount.equals("May")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
						
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Jun";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Jun")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Jul";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Jul")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Aug";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Aug")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Sep";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Sep")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Oct";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Oct")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Nov";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Nov")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<31;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
								mount="Dec";
								data[1]=mount;
								check=true;
							}
							if(mount.equals("Dec")){
								int c=1;
								if (check==false){
									c=day;
								}
								for (int i=c;i<32;i++){
									data[2]=i+"";
									if (day2.equals(data[2]) || day2.equals("0"+data[2]) ){
										addNever(data, area);
									}
								}
//								mount="Jun";
//								data[1]=mount;
								check=true;
							}
			
							
						}
						
						
						frame2.setVisible(false);
						refess();
						System.out.println(dateChooser.getDate().toString());
						

					}
				});

				JButton button2 = new JButton("CANCEL");
				button2.setBounds(250, 290, 90, 30);
				panel2.add(button2);
				button2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame2.setVisible(false);

					}
				});

			}

		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (comboMouth.getSelectedItem().equals("Jan")) {
					String str = "1/1/" + comboyear.getSelectedItem() + "/17:20";
					String year = comboyear.getSelectedItem().toString();
					updatebtn(str, 31);

				}
				if (comboMouth.getSelectedItem().equals("Feb")) {
					int day = 28;
					String year = comboyear.getSelectedItem().toString();
					if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
						day = 29;
					} else if (Integer.parseInt(year) % 100 == 0 && Integer.parseInt(year) % 400 == 0) {
						day = 29;
					}
					String str = "1/2/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, day);

				}
				if (comboMouth.getSelectedItem().equals("Mar")) {
					String str = "1/3/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 31);
				}
				if (comboMouth.getSelectedItem().equals("Apr")) {
					String str = "1/4/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 30);
				}
				if (comboMouth.getSelectedItem().equals("May")) {
					String str = "1/5/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 31);

				}
				if (comboMouth.getSelectedItem().equals("Jun")) {
					String str = "1/6/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 30);
				}
				if (comboMouth.getSelectedItem().equals("Jul")) {
					String str = "1/7/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 31);

				}
				if (comboMouth.getSelectedItem().equals("Aug")) {
					String str = "1/8/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 31);

				}
				if (comboMouth.getSelectedItem().equals("Sep")) {
					String str = "1/9/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 30);

				}
				if (comboMouth.getSelectedItem().equals("Oct")) {
					String str = "1/10/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 31);
				}

				if (comboMouth.getSelectedItem().equals("Nov")) {
					String str = "1/11/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 30);
				}

				if (comboMouth.getSelectedItem().equals("Dec")) {
					String str = "1/12/" + comboyear.getSelectedItem() + "/17:20";
					updatebtn(str, 31);
				}

				refess();

			}

		}

	}



	public void updatebtn(String dmy, int day) {
		panel.removeAll();
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy/HH:mm");
		try {

			String[] list = dmy.split("/");

			String s = list[0] + "/" + list[1] + "/" + (Integer.parseInt(list[2])) + "/" + list[3];

			Date myTime = dateTimeFormat.parse(s);

			for (int i = 1; i <= myTime.getDay(); i++) {
				JButton but = new JButton("");
				but.setVisible(false);
				panel.add(but);
			}
			for (int i = 0; i < day; i++) {

				panel.add(listday.get(i));
			}

			for (int i = 1; i < 44 - (myTime.getDay() + 1 + day); i++) {
				JButton but = new JButton("");
				but.setVisible(false);
				panel.add(but);
			}

		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		panel.updateUI();
	}

	public void refess() {
		ArrayList<JButton> list = new ArrayList<>();
		for (DatedayInterface d : cal.getList()) {
			for (JButton but : listday) {
				if ((but.getLabel().equals(d.getDay()) || ("0" + but.getLabel()).equals(d.getDay()))
						&& comboMouth.getSelectedItem().equals(d.getMount())
						&& comboyear.getSelectedItem().equals(d.getYear())) {
					but.setBackground(Color.gray);
					list.add(but);
				} else {
					if (list.contains(but) == false) {
						but.setBackground(Color.white);
					}
				}

			}
		}
		frame.repaint();
	}
	
	public void addNever(String[] data, JTextArea area){
		if (data[2].length()<2){
			data[2]="0"+data[2];
		}
		Dateday date = new Dateday(data[2], data[1], data[5]);
		boolean check = false;


		for (DatedayInterface d : cal.getList()) {
			if ((d.getDay().equals(date.getDay()) || d.getDay().equals("0"+date.getDay()) )&& d.getMount().equals(date.getMount())
					&& d.getYear().equals(date.getYear())) {

				String str = area.getText();
				d.setText(d.getText()
						+ "\n********************************************************************\n"
						+ str);
				check = true;
				cal.update(d.getDay(), d.getMount(), d.getYear(), d.getText());
				cal.load();
				break;
			}
		}

		if (check == false) {
			date.setText(area.getText());
			cal.add(date.getDay(), date.getMount(), date.getYear(), date.getText());
			cal.load();
		}
	}

}
