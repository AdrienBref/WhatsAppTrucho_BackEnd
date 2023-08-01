package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import entities.User;
import utils.JsonParser;
import utils.PasswordUtils;

import java.io.IOException;
import java.io.OutputStream;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            // Obtener el cuerpo de la solicitud
            String requestBody = new String(exchange.getRequestBody().readAllBytes());

            // Parsear el JSON para obtener los datos de usuario y contraseña
            JsonParser jsonParser = new JsonParser();
            RegisterRequest registerRequest = jsonParser.parse(requestBody, RegisterRequest.class);

           
            String userName = registerRequest.getUsername();

            // Realizar el hash de la contraseña 
             String hashedPassword = PasswordUtils.hashPassword(registerRequest.getPassword());

            //crea un nuevo usuario con el nombre y la contraseña con el hash
            User nuevoUsuario = new User(userName, hashedPassword);

            
            //AQUI HAY QUE GUARDAR LOS DATOS EN LA BBDD !!!!!!!!!!

            // Envía una respuesta al front de registro exitoso
            String response = "Registro exitoso para el usuario: " + registerRequest.getUsername();
            sendResponse(exchange, response, 200);
        } else {
            // Enviar respuesta 405 Method Not Allowed para indicar que solo se admite POST
            sendResponse(exchange, "Método no permitido", 405);
        }
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static class RegisterRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
