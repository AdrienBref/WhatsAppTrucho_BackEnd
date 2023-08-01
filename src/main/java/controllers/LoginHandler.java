package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.JsonParser;

import java.io.IOException;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {

            // Obtener el cuerpo de la solicitud
            String requestBody = new String(exchange.getRequestBody().readAllBytes());

            // Parsear el JSON para obtener los datos de usuario y contraseña
            JsonParser jsonParser = new JsonParser();
            LoginRequest loginRequest = jsonParser.parse(requestBody, LoginRequest.class);
            

            // Aquí puedes verificar las credenciales en tu base de datos o sistema de autenticación
            // Si las credenciales son correctas, puedes redirigir al usuario a la página de acceso permitido
            // y devolver una respuesta 200 OK. Si no, puedes enviar una respuesta de error 401 Unauthorized.
            // Aquí, simplemente estamos enviando una respuesta con un mensaje para simplificar el ejemplo.
            String response = "Login exitoso para el usuario: " + loginRequest.getUsername();
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

    private static class LoginRequest {
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
