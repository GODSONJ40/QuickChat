package server;

import common.Message;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String username;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            String username = (String) in.readObject();
            String password = (String) in.readObject();

            if (!Server.authenticate(username, password)) {
                out.writeObject(
                        new Message(
                                "SERVER",
                                "Authentication failed."
                        )
                );
                out.flush();
                socket.close();
                return;
            }

            this.username = username;
            Server.addOnlineUser(username, this);
            System.out.println(username + " connected.");

            sendMessage(
                    new Message(
                            "SERVER",
                            "Welcome " + username + "!"
                    )
            );

            Server.broadcast(
                    new Message(
                            "SERVER",
                            username + " joined the chat."
                    )
            );

            while (true) {
                Message message = (Message) in.readObject();
                System.out.println(message);
                Server.broadcast(message);
            }
        } catch (Exception e) {
            System.out.println(username + " disconnected.");
        } finally {
            try {
                if (username != null) {
                    Server.removeOnlineUser(username);
                    Server.broadcast(
                            new Message(
                                    "SERVER",
                                    username + " left the chat."
                            )
                    );
                }
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.out.println("Failed to send message.");
        }
    }
}