package com.Bref.App;

import config.HibernateUtil;
import config.httpServer;
import entities.Message;
//import entities.User;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import repositories.MessageRepository;
import utils.JsonParser;
//import repositories.UserRepository;

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

//		User user1 = new User("Adrian");
//		UserRepository userRepository = new UserRepository(session);
//		userRepository.save(user1);

//		Message message1 = new Message("dsafj9duf03f", "31-07-2023", "Ola ke ase");
//		MessageRepository messageRepository = new MessageRepository(session);
//		messageRepository.save(message1);



//		session.close();
//		System.out.println("Closing Conn to ddbb");

	}

}

