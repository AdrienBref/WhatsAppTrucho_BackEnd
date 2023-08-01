package controllers;

import jakarta.websocket.Session;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


public class WebSocketServerController extends WebSocketServer {


    public WebSocketServerController(InetSocketAddress address) {
        super(address);
    }
    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("Nueva conexión WebSocket: " + webSocket.getRemoteSocketAddress());
    }


    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Conexión WebSocket cerrada: " + webSocket.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("Mensaje recibido del cliente: " + s);
        webSocket.send("Hola, cliente!");
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        System.err.println("Error en la conexión WebSocket: " + e);
    }

    @Override
    public void onStart() {

    }


}

