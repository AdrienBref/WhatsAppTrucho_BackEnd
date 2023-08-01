package com.Bref.App;

import config.HibernateUtil;
import config.httpServer;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



import java.io.IOException;

@SpringBootApplication
@EntityScan
public class App {

	public static void main(String[] args) {

		//SpringApplication.run(App.class, args);

		System.out.println( "Connecting to ddbb" );
		Session session = HibernateUtil.get().openSession();
		System.out.println( "Connected to ddbb" );


		/**
		 * Starting the server
		 */

		try {
			httpServer server = new httpServer(8080, "/");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

//		session.close();
//		System.out.println("Closing Conn to ddbb");

	}

}

