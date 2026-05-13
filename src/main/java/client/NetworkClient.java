package client;

import common.Message;
import java.io.*;
import java.net.Socket;

public class NetworkClient {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public NetworkClient(
            String host,
            int port,
            String username,
            String password
    ) throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());

        // send login credentials
        try {
            out.writeObject(username);
            out.writeObject(password);
            out.flush();
        } catch (IOException e) {
            throw new IOException("Failed to authenticate.");
        }
    }

    public void sendMessage(Message message) throws IOException {
        out.writeObject(message);
        out.flush();
    }

    public Message readMessage()
            throws IOException, ClassNotFoundException {
        return (Message) in.readObject();
    }

    public void close() throws IOException {
        socket.close();
    }
}