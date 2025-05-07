import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Login
            System.out.print(in.readLine()); // Username:
            out.println(scanner.nextLine());

            System.out.print(in.readLine()); // Password:
            out.println(scanner.nextLine());

            String response = in.readLine();
            System.out.println(response);

            if (response.startsWith("Authentication failed")) return;

            // Listen in a separate thread
            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected.");
                }
            }).start();

            // Send messages
            while (true) {
                String input = scanner.nextLine();
                if (input.startsWith("/sendfile")) {
                    System.out.print("Enter file path: ");
                    String filePath = scanner.nextLine();
                    out.println("/sendfile");  // notify server
                    sendFile(filePath, socket);
                } else {
                    out.println(input);
                }
            }
        } catch (IOException e) {
            System.out.println("Connection failed.");
        }
    }

    private static void sendFile(String filePath, Socket socket) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File not found.");
                return;
            }

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(file.getName());
            dos.writeLong(file.length());

            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int read;

            while ((read = fis.read(buffer)) > 0) {
                dos.write(buffer, 0, read);
            }

            fis.close();
            System.out.println("File sent.");
        } catch (IOException e) {
            System.out.println("File transfer failed.");
        }
    }
}
