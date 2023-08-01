package com.Bref.App;

import config.HibernateUtil;
import config.httpServer;
import controllers.WebSocketServerController;
//import entities.User;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import repositories.UserRepository;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;


@SpringBootApplication
@EntityScan
public class App {

	public static void main(String[] args) throws Exception {

		//SpringApplication.run(App.class, args);

		Scanner scanner = new Scanner(System.in);

		System.out.println( "Connecting to ddbb" );
		Session session = HibernateUtil.get().openSession();
		System.out.println( "Connected to ddbb" );



		/**
		 * Starting the Http server
		 */
		try {
			httpServer server = new httpServer(8080, "/");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		/*
		* Starting the webSocket server
		* */

		WebSocketServerController server = new WebSocketServerController(new InetSocketAddress(8081));
		server.start();
		System.out.println("Servidor WebSocket escuchando en el puerto " + 8081);


//		session.close();
//		System.out.println("Closing Conn to ddbb");



	}



}

