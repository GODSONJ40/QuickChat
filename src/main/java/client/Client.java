package client;

import common.Message;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            NetworkClient client =
                    new NetworkClient(
                            "localhost",
                            12345,
                            username,
                            password
                    );

            System.out.println("Connected as " + username);

            new Thread(() -> {
                try {
                    while (true) {
                        Message msg = client.readMessage();
                        System.out.println(msg);
                    }

                } catch (Exception e) {
                    System.out.println("Disconnected from server.");
                }

            }).start();

            while (true) {
                String text = scanner.nextLine();
                client.sendMessage(
                        new Message(username, text)
                );
            }

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}