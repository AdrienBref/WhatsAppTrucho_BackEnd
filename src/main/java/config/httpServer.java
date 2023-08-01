package config;
import com.sun.net.httpserver.HttpServer;

import controllers.LoginHandler;
import controllers.MyHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class httpServer {

    private int port = 0;
    private String path = "";
    public httpServer(int port, String path) throws IOException {

        this.port = port;
        this.path = path;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Crear contexto y definir manejador para las solicitudes
        server.createContext("/", new MyHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/register", new RegisterHandler());

        // Iniciar el servidor
        server.start();

        System.out.println("HTTP server started. Listening on port: " + port );

    }
}