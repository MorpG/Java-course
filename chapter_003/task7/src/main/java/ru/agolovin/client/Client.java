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

    private Socket socket;

    private BufferedReader reader;

    private BufferedReader readerSocket;

    private PrintWriter writer;

    Client(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(System.in));
            this.readerSocket = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ClientSettings settings = new ClientSettings();
        Socket socket = new Socket(
                InetAddress.getByName(settings.getServerAddress()), settings.getPort());
        new Client(socket).init();
    }

    void init() {
        try {
            String line;
            if (checkConnection(writer, readerSocket)) {
                System.out.println("Press any key to start");
                reader.readLine();
                writer.println("show menu");
                readSocket(readerSocket);
                do {
                    line = reader.readLine();
                    writer.println(line);
                    readSocket(readerSocket);
                } while (!"0".equals(line));
            } else {
                System.out.println("connection failed");
            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void readSocket(BufferedReader readerSocket) {
        String str;
        try {
            str = readerSocket.readLine();
            while (str != null && !str.isEmpty()) {
                System.out.println(str);
                str = readerSocket.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private boolean checkConnection(PrintWriter writer, BufferedReader readerSocket) {
        boolean result = false;
        try {
            int i = 0;
            do {
                writer.println("start");
                i++;
                if (readerSocket.readLine().equals("start")) {
                    result = true;
                    System.out.println("Connection to server successful");
                    break;
                }
            } while (i <= 5);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }
}
