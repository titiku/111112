package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controll.Action;
import controll.Action2;
import model.Calendar;

public class View2 {

	private JButton save;
	private JTextArea area;
	
	private JFrame frame2;
	JButton cancel;
	JComboBox hh;
	JComboBox mm;
	JComboBox type;

	public View2() {

		frame2 = new JFrame();
		frame2.setPreferredSize(new Dimension(500, 400));
		frame2.setVisible(true);
		frame2.setLayout(new GridLayout(2, 0, 50, 50));
		area = new JTextArea();
		JScrollPane pane = new JScrollPane(area);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		frame2.add(pane);
		frame2.add(panel2);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 save = new JButton("Save");
		 cancel = new JButton("Cancel");
	
		hh =new JComboBox<>();
		hh.addItem("select time");
		for (int i= 0;i<24 ;i++){
			if (i<10){
				hh.addItem("0"+i);
			}else{
				hh.addItem(""+i);
			}
	
		}
		
		mm=new JComboBox<>();
		mm.addItem("select time");
		for (int i= 0;i<60 ;i++){
			if (i<10){
				mm.addItem("0"+i);
			}else{
				mm.addItem(""+i);
			}
		}
		type =new JComboBox<>();
		
		type.addItem("select type");
		type.addItem("Never");
		type.addItem("Daily");
		type.addItem("Weekly");
		type.addItem("Monthly");
		
		panel2.add(type);
		panel2.add(hh);
		panel2.add(mm);
		panel2.add(save);
		panel2.add(cancel);
		
		frame2.pack();
	}
	
	public JButton getCancel() {
		return cancel;
	}
	public JButton getSave() {
		return save;
	}
	public JTextArea getArea() {
		return area;
	}
	public JFrame getFrame2() {
		return frame2;
	}
	public JComboBox getHh() {
		return hh;
	}

	public JComboBox getMm() {
		return mm;
	}
	public JComboBox getType() {
		return type;
	}

}
