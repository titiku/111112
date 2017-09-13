package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View3 {
	JTextArea area;

	JButton delete;
	JButton cancel;
	JButton save;

	JFrame frame3;
	JLabel type;
	public View3() {

		 frame3 = new JFrame();
		frame3.setPreferredSize(new Dimension(500, 400));
		frame3.setVisible(true);
		frame3.setLayout(new GridLayout(2, 0, 50, 50));
		area = new JTextArea();
		JScrollPane pane = new JScrollPane(area);
//		area.setEditable(false);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());

		frame3.add(pane);
		frame3.add(panel2);

		delete = new JButton("delete");
		cancel=new JButton("Cancel");
		save= new JButton("Save");
		
		type =new JLabel("");
		panel2.add(type);
		panel2.add(save);
		panel2.add(delete);
		panel2.add(cancel);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		frame3.pack();
	}
	
	
	public JLabel getType() {
		return type;
	}


	public JFrame getFrame3() {
		return frame3;
	}
	public JTextArea getArea() {
		return area;
	}

	public JButton getDelete() {
		return delete;
	}
	public JButton getCancel() {
		return cancel;
	}
	public JButton getSave() {
		return save;
	}


}
