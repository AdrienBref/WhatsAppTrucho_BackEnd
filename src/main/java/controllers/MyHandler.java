package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import config.HibernateUtil;
//import entities.User;
import entities.Message;
import org.hibernate.Session;
//import repositories.UserRepository;
import repositories.MessageRepository;
import services.MessageServices;
import utils.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class MyHandler implements HttpHandler {

    private String contain;
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String clientAddress = exchange.getRemoteAddress().getAddress().getHostAddress();


        if ("POST".equals(exchange.getRequestMethod())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
                System.out.println("Client: " + clientAddress);
                contain = line;
            }


            Session session = HibernateUtil.get().openSession();
            JsonParser parser = new JsonParser();
            System.out.println(contain);

            MessageServices messageServices = new MessageServices();

            messageServices.addToMessageStack(contain);

            Message message1 = parser.parse(contain, Message.class);

            MessageRepository messageRepository = new MessageRepository(session);
            messageRepository.save(message1);



        }

        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "X-Client-ID");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

        // Configurar respuesta
        String response = "¡Hola desde el servidor HTTP de Java!";
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.getResponseHeaders().set("Content-Type", "application/javascript");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    public String getContain() {
        return contain;
    }

    public void setContain(String contain) {
        this.contain = contain;
    }
}
