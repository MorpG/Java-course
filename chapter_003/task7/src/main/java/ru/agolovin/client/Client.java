package ru.agolovin.client;

import ru.agolovin.settings.ClientSettings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Client {

    private Socket socket = new Socket();

    public Client(Socket socket) {
        this.socket = socket;

    }

    public static void main(String[] args) throws IOException {
        ClientSettings settings = new ClientSettings();
        Socket socket = new Socket(
                InetAddress.getByName(settings.getServerAddress()), settings.getPort());
        new Client(socket).init();
    }

    public void init() {
        try {
            System.out.println("Connection to server successful");
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader readerSocket = new BufferedReader(new InputStreamReader(socketInputStream));
            PrintWriter writer = new PrintWriter(socketOutputStream, true);
            String line;
            while (readerSocket.ready()) {
                System.out.println(readerSocket.readLine());
            }
            System.out.println("Please, press any key to show menu");
            do {
                line = reader.readLine();
                writer.println(line);
                while (readerSocket.ready()) {
                    System.out.println(readerSocket.readLine());
                }
            } while (!"0".equals(line));


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
