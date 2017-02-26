package ru.agolovin.server;

import ru.agolovin.settings.ServerSettings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Server {

    private String startPath;
    private Socket socket;

    Server(Socket socket) {
        this.socket = socket;
        this.startPath = new ServerSettings().getStartPath();
    }

    public static void main(String[] args) throws IOException {
        ServerSettings settings = new ServerSettings();
        Socket socket = new ServerSocket(settings.getPort()).accept();
        Server server = new Server(socket);
        server.init();
    }

    public String getStartPath() {
        return startPath;
    }

    void init() {
        try {
            System.out.println("Connection successful");
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketInputStream));
            ServerMenu serverMenu = new ServerMenu(socketInputStream, socketOutputStream, new File(startPath));
            serverMenu.fillActions();
            String string;
            do {
                serverMenu.show();
                string = reader.readLine();
                System.out.println("Client received: " + string);
                serverMenu.select(string);
            } while (!"0".equals(string));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
