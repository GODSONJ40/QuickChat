import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Socket> onlineUsers = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        users.put("user1", "pass1");
        users.put("user2", "pass2");

        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server started on port 12345");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Authentication
                out.println("Username:");
                username = in.readLine();
                out.println("Password:");
                String password = in.readLine();

                if (!users.containsKey(username) || !users.get(username).equals(password)) {
                    out.println("Authentication failed.");
                    socket.close();
                    return;
                }

                out.println("Welcome " + username);
                onlineUsers.put(username, socket);
                broadcast(username + " has joined.");

                String msg;
                while ((msg = in.readLine()) != null) {
                    if (msg.startsWith("/sendfile")) {
                        receiveFile();
                    } else {
                        broadcast(username + ": " + msg);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error with user " + username);
            } finally {
                try {
                    if (username != null) {
                        onlineUsers.remove(username);
                        broadcast(username + " has left.");
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message) {
            List<Socket> sockets = new ArrayList<>(onlineUsers.values());
            for (Socket s : sockets) {
                try {
                    PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
                    writer.println(message);
                } catch (IOException e) {
                    System.out.println("Failed to send message to a user.");
                }
            }
        }

        private void receiveFile() throws IOException {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String fileName = dis.readUTF();
            long fileSize = dis.readLong();

            File file = new File("received_" + fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] buffer = new byte[4096];
                long remaining = fileSize;
                int read;
                while ((read = dis.read(buffer, 0, (int) Math.min(buffer.length, remaining))) > 0) {
                    fos.write(buffer, 0, read);
                    remaining -= read;
                }
            }

            broadcast(username + " sent a file: " + file.getName());
        }
    }
}
