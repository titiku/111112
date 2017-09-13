package controll;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ku.sci.cs.myapp.MyAppTest;
import model.Calendar;
import view.View;
import view.View2;
import view.View3;

public class Action implements ActionListener {
	private View view;
	private Calendar cal;
	JdbcSQLiteConnection db;

	public Action(View view, Calendar cal, JdbcSQLiteConnection db) {
		this.view = view;
		this.cal = cal;
		this.db = db;
	}

	public void updatebtn(String dmy, int day) {
		view.getPanel().removeAll();
		DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy/HH:mm");
		try {

			String[] list = dmy.split("/");

			String s = list[0] + "/" + list[1] + "/" + (Integer.parseInt(list[2]) + 543) + "/" + list[3];

			Date myTime = dateTimeFormat.parse(s);

			for (int i = 1; i <= myTime.getDay(); i++) {
				JButton but = new JButton("");
				but.setVisible(false);
				view.getPanel().add(but);
			}
			for (int i = 0; i < day; i++) {

				view.getPanel().add(view.getListday().get(i));
			}

			for (int i = 1; i < 44 - (myTime.getDay() + 1 + day); i++) {
				JButton but = new JButton("");
				but.setVisible(false);
				view.getPanel().add(but);
			}

		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		view.getPanel().updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(view.getBtnNote())) {

			if (cal.getListtime().size() > 0) {
				view.getFrame().remove(view.getBtnNote());
				view.getFrame().add(view.getCombotime());
				view.getCombotime().addActionListener(this);
				view.getFrame().repaint();
			} else {
				JOptionPane.showMessageDialog(null, "Find note not found");
			}

		}
		if (src.equals(view.getComboBox()) || src.equals(view.getComboyear())) {
	
			if (view.getComboBox().getSelectedItem().equals("January")) {
				String str = "1/1/" + view.getComboyear().getSelectedItem() + "/17:20";
				String year = view.getComboyear().getSelectedItem().toString();
				updatebtn(str, 31);

			}
			if (view.getComboBox().getSelectedItem().equals("February")) {
				int day = 28;
				String year = view.getComboyear().getSelectedItem().toString();
				if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) {
					day = 29;
				} else if (Integer.parseInt(year) % 100 == 0 && Integer.parseInt(year) % 400 == 0) {
					day = 29;
				}
				String str = "1/2/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, day);

			}
			if (view.getComboBox().getSelectedItem().equals("March")) {
				String str = "1/3/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 31);
			}
			if (view.getComboBox().getSelectedItem().equals("April")) {
				String str = "1/4/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 30);
			}
			if (view.getComboBox().getSelectedItem().equals("May")) {
				String str = "1/5/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 31);

			}
			if (view.getComboBox().getSelectedItem().equals("June")) {
				String str = "1/6/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 30);
			}
			if (view.getComboBox().getSelectedItem().equals("July")) {
				String str = "1/7/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 31);

			}
			if (view.getComboBox().getSelectedItem().equals("August")) {
				String str = "1/8/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 31);

			}
			if (view.getComboBox().getSelectedItem().equals("September")) {
				String str = "1/9/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 30);

			}
			if (view.getComboBox().getSelectedItem().equals("October")) {
				String str = "1/10/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 31);
			}

			if (view.getComboBox().getSelectedItem().equals("November")) {
				String str = "1/11/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 30);
			}

			if (view.getComboBox().getSelectedItem().equals("December")) {
				String str = "1/12/" + view.getComboyear().getSelectedItem() + "/17:20";
				updatebtn(str, 31);
			}
		}

		if (src.equals(view.getCombotime())) {

			if (cal.getListtime().contains(view.getCombotime().getSelectedItem())) {

				View3 view3 = new View3();
				view3.getArea().setText(cal.getListstr().get(view.getCombotime().getSelectedIndex() - 1));
				Action3 act3 = new Action3(cal, view3, (String) view.getCombotime().getSelectedItem(), view, this);
				view3.getType().setText(cal.getListtype().get(view.getCombotime().getSelectedIndex() - 1));
				view3.getDelete().addActionListener(act3);
				view3.getCancel().addActionListener(act3);
				view3.getSave().addActionListener(act3);

			}

		}

		for (

				int i = 0; i < 31; i++) {
			if (src.equals(view.getListday().get(i))) {

				String str = (i + 1) + "/" + (String) view.getComboBox().getSelectedItem() + "/"
						+ view.getComboyear().getSelectedItem().toString();
				View2 view2 = new View2();
				Action2 act2 = new Action2(view2, cal, str, view, db);

				view2.getSave().addActionListener(act2);
				view2.getCancel().addActionListener(act2);
				view2.getHh().addActionListener(act2);
				view2.getMm().addActionListener(act2);
				view2.getType().addActionListener(act2);
			}
		}
	}

}
