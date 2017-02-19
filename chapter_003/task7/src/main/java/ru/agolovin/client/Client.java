package ru.agolovin.client;

import ru.agolovin.settings.Settings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Client {
    private String inetAdress;
    private int port;

    private Socket socket = new Socket();

    public Client(Socket socket) {
        this.socket = socket;

    }

    public void init() {
        setSettings();
        System.out.println("Connection to server: ");
        try {
            InetAddress inetAddress = InetAddress.getByName(inetAdress);
            socket = new Socket(inetAddress, port);
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStram = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader readerSocket = new BufferedReader(new InputStreamReader(socketInputStream));
            PrintWriter writer = new PrintWriter(socketOutputStram, true);

            while (true) {
                String line = reader.readLine();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void setSettings() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        port = Integer.parseInt(settings.getValue("port"));
        inetAdress = settings.getValue("address");

    }


}
