package controll;
import javax.swing.JButton;

import model.Calendar;
import view.View;

public class Controll {
	
	public void startApp(){
		Calendar cal= new Calendar();
		View view= new View(cal);	
	}

}
