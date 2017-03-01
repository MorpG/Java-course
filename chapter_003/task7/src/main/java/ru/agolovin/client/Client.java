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

    Client(Socket socket) {
        this.socket = socket;

    }

    public static void main(String[] args) throws IOException {
        ClientSettings settings = new ClientSettings();
        Socket socket = new Socket(
                InetAddress.getByName(settings.getServerAddress()), settings.getPort());
        new Client(socket).init();
    }

    void init() {
        try {
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader readerSocket = new BufferedReader(new InputStreamReader(socketInputStream));
            PrintWriter writer = new PrintWriter(socketOutputStream, true);
            String line;
            String str;
            int i = 0;
            boolean flag = false;
            do {
                writer.println("true");
                i++;
                if (readerSocket.readLine().equals("true")) {
                    flag = true;
                    System.out.println("Connection to server successful");
                    break;
                }
            } while (i <= 5);
            if (flag) {
                System.out.println("Press any key to continue");
                reader.readLine();
                writer.println("show menu");
                str = readerSocket.readLine();
                while (str != null && !str.isEmpty()) {
                    System.out.println(str);
                    str = readerSocket.readLine();
                }
                do {
                    line = reader.readLine();
                    writer.println(line);
                    str = readerSocket.readLine();
                    while (str != null && !str.isEmpty()) {
                        System.out.println(str);
                        str = readerSocket.readLine();
                    }
                } while (!"0".equals(line));
            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
