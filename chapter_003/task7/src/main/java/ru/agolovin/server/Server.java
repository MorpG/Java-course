package ru.agolovin.server;

import ru.agolovin.settings.ServerSettings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Server {

    /**
     * Start path.
     */
    private String startPath;

    /**
     * Socket.
     */
    private Socket socket;

    /**
     * Constructor.
     *
     * @param socket Socket.
     */
    Server(Socket socket) {
        this.socket = socket;
        this.startPath = new ServerSettings().getStartPath();
    }

    /**
     * Main method.
     *
     * @param args String
     * @throws IOException Exception
     */
    public static void main(String[] args) throws IOException {
        ServerSettings settings = new ServerSettings();
        Socket socket = new ServerSocket(settings.getPort()).accept();
        Server server = new Server(socket);
        server.init();
    }

    /**
     * Initialization.
     */
    void init() {
        try {
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketInputStream));
            PrintWriter writer = new PrintWriter(socketOutputStream, true);
            ServerMenu serverMenu = new ServerMenu(
                    socketInputStream, socketOutputStream, new File(startPath));
            serverMenu.fillActions();
            String string;
            int i = 0;
            boolean flag = false;
            final int maxTry = 5;
            do {
                string = reader.readLine();
                if ("start".equals(string)) {
                    writer.println("start");
                    flag = true;
                    System.out.println("Connection successful");
                    break;
                } else {
                    i++;
                }
            } while (i <= maxTry);
            if (flag) {
                string = reader.readLine();
                if (string.equals("show menu")) {
                    serverMenu.show();
                }
                do {
                    if (!flag) {
                        serverMenu.show();
                    }
                    string = reader.readLine();
                    System.out.println("Client received: " + string);
                    serverMenu.select(string);
                    flag = false;
                } while (!"0".equals(string));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
