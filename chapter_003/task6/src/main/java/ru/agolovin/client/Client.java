package ru.agolovin.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Client {

    /**
     * Socket.
     */
    private Socket socket;

    /**
     * Constructor.
     * @param socket Socket
     */
    Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * main method.
     *
     * @param args String
     */
    public static void main(String[] args) {
        final int defaultPort = 23451;
        final String ip = "127.0.0.1";
        try {
            Socket socket = new Socket(InetAddress.getByName(ip), defaultPort);
            new Client(socket).init();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Initialization.
     */
    void init() {
        try {
            System.out.println("Connection successful");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);

            String userInput;
            do {
                System.out.println("Please, enter your question for Oracle:");
                userInput = console.nextLine();
                out.println(userInput);
                String str = in.readLine();
                while (str != null && !str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!(userInput.equals("exit")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
