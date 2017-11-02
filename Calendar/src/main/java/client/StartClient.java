package client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.CalendarInterface;

/**
 * Hello world!
 *
 */
public class StartClient 
{
    public static void main( String[] args )
    {
    	ApplicationContext b = new ClassPathXmlApplicationContext("Client.xml");
		Controll con = (Controll) b.getBean("con");
		con.startApp((CalendarInterface) b.getBean("calendar"));
    }
}
