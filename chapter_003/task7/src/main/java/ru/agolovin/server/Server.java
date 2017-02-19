package ru.agolovin.server;

import ru.agolovin.settings.Settings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Server {

    private int port;

    private String startPath;

    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        Socket socket = new Socket();
        new Server(socket).init();
    }

    void init() {
        setSettings();
        try {
            System.out.println("Wait connection");
            socket = new ServerSocket(port).accept();
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketInputStream));
            ServerMenu serverMenu = new ServerMenu(socketInputStream, socketOutputStream, new File(startPath));
            serverMenu.fillActions();
            System.out.println("Connection successful");
            while (true) {
                String string = reader.readLine();
                System.out.println("Client received: " + string);
                serverMenu.select(string);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void setSettings() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.port = Integer.parseInt(settings.getValue("port"));
        this.startPath = settings.getValue("home.path");

    }
}
