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
     * Client port.
     */
    private int port;

    /**
     * Client ip.
     */
    private String ip;

    /**
     * Constructor.
     */
    private Client() {
        final int defaultPort = 23451;
        this.port = defaultPort;
        this.ip = "127.0.0.1";
    }

    /**
     * main method.
     *
     * @param args String
     */
    public static void main(String[] args) {
        Client client = new Client();
        client.init();
    }

    /**
     * Initialization.
     */
    private void init() {
        try {
            Socket socket = new Socket(InetAddress.getByName(ip), port);
            System.out.println("Connection successful");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);

            String userInput;
            do {
                System.out.println("Please, enter the question:");
                userInput = console.nextLine();
                out.println(userInput);
                String str = in.readLine();
                while (!str.isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (!(userInput.equals("exit")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
