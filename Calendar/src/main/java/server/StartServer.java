package server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartServer {

	public static void main(String[] args) {
		ApplicationContext n = new ClassPathXmlApplicationContext("Server.xml");
		System.out.println("start");
	}

}
