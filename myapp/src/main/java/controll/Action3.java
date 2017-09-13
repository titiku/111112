package controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;

import model.Calendar;
import view.View;
import view.View3;

public class Action3 implements ActionListener {
	Calendar cal;
	View3 view3;
	String date;
	View view;
	Action action1;
	String text;

	public Action3(Calendar cal, View3 view3, String str, View view, Action action1) {
		this.cal = cal;
		this.view3 = view3;
		this.date = str;
		this.view = view;
		this.action1=action1;
		this.text=view3.getArea().getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src.equals(view3.getDelete())) {
			view.getCombotime().removeActionListener(action1);
			view3.getFrame3().setVisible(false);

			for (int i = 0; i < cal.getListstr().size(); i++) {
				if (cal.getListtime().get(i).equals(date)&& cal.getListstr().get(i).equals(view3.getArea().getText())) {
					cal.delete(i);
				}

			}

//			System.out.println(cal.getListsave());
//			System.out.println(cal.getListtime());
			view.getCombotime().removeItemAt(view.getCombotime().getSelectedIndex());

			view.getFrame().getContentPane().add(view.getBtnNote());
			view.getFrame().remove(view.getCombotime());
		
			
			view.getFrame().repaint();

			
			
			try {
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:store.db";
				Connection conn = DriverManager.getConnection(dbURL);
				if (conn != null) {

					DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
					String query = "DELETE FROM Calendar\nWHERE date=''";
					
					 query = "DELETE FROM Calendar\nWHERE date='"+date+"' AND str='"+text+"'";
					
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
		
		if (src.equals(view3.getCancel())){
			view.getCombotime().removeActionListener(action1);
			view3.getFrame3().setVisible(false);
			view.getFrame().getContentPane().add(view.getBtnNote());
			view.getFrame().remove(view.getCombotime());
			
		
		}
		if (src.equals(view3.getSave())){

			view.getCombotime().removeActionListener(action1);
			int index = 0;
			for (int i=0;i<cal.getListtime().size();i++){
	
				if (cal.getListtime().get(i).equals(date) && cal.getListstr().get(i).equals(text)){
					index=i;
				
					break;
				}
			}
			view.getCombotime().removeItemAt(view.getCombotime().getSelectedIndex());
			
			
			cal.edit(index,date, view3.getArea().getText(),view3.getType().getText());
			view.getCombotime().addItem(date);
			
			
			view3.getFrame3().setVisible(false);
			
			
			view.getFrame().getContentPane().add(view.getBtnNote());
			view.getFrame().remove(view.getCombotime());
		
			view.getFrame().repaint();

			
			try {
				Class.forName("org.sqlite.JDBC");
				String dbURL = "jdbc:sqlite:store.db";
				Connection conn = DriverManager.getConnection(dbURL);
				if (conn != null) {

					DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
					String query = "DELETE FROM Calendar\nWHERE date=''";
					 query = "DELETE FROM Calendar\nWHERE date='"+date+"' AND str='"+text+"'";
					
					Statement statement = conn.createStatement();
					statement.execute(query);
					
					query = "insert into Calendar\nvalues ('"+date+"','"+view3.getArea().getText()+"','"+view3.getType().getText().toString()+"')";
		
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
