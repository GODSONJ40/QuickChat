package server;

import common.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final Map<String, String> users = new ConcurrentHashMap<>();
    private static final Map<String, ClientHandler> onlineUsers = new ConcurrentHashMap<>();
    public static void main(String[] args) throws IOException {
        users.put("user1", "pass1");
        users.put("user2", "pass2");
        users.put("user3", "pass3");
        users.put("user4", "pass4");
        users.put("user5", "pass5");

        ServerSocket serverSocket = new ServerSocket(12345);

        System.out.println("=================================");
        System.out.println("Great progress!");
        System.out.println("QuickChat Server Started");
        System.out.println("Port: 12345");
        System.out.println("=================================");

        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket);
            Thread thread = new Thread(handler);
            thread.start();
        }
    }

    public static boolean authenticate(String username, String password) {
        return users.containsKey(username)
                && users.get(username).equals(password);
    }

    public static void addOnlineUser(String username, ClientHandler handler) {
        onlineUsers.put(username, handler);
    }

    public static void removeOnlineUser(String username) {
        onlineUsers.remove(username);
    }

    public static void broadcast(Message message) {

        for (ClientHandler client : onlineUsers.values()) {
            client.sendMessage(message);
        }
    }

    public static Map<String, ClientHandler> getOnlineUsers() {
        return onlineUsers;
    }
}