package ru.agolovin.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Client {
    public static void main(String[] args) {
        int servPort = 6565;
        String interAdress = "127.0.0.1";

        try {
            InetAddress inetAddress = InetAddress.getByName(interAdress);
            System.out.println("Подключаемся к серверу: " + servPort);

            Socket socket = new Socket(inetAddress, servPort);

            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStrream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(socketInputStream);
            DataOutputStream out = new DataOutputStream(socketOutputStrream);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String string = null;
            System.out.println("Введите фразу для передачи серверу: ");

            while (true) {
                string = reader.readLine();
                out.writeUTF(string);
                out.flush();
                string = in.readUTF();
                System.out.println("Сервер прислал в ответ: " + string);
                System.out.println("Введите фразу для передачи серверву: ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
