package ru.agolovin.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Server {
    public static void main(String[] args) {
        int port = 6700;

        try {
            ServerSocket servSocket = new ServerSocket(port);
            System.out.println("Ждём подключения к серверу");
            Socket socket = servSocket.accept();
            System.out.println("Подключение состоялось");

            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStrream = socket.getOutputStream();

            DataInputStream in = new DataInputStream(socketInputStream);
            DataOutputStream out = new DataOutputStream(socketOutputStrream);

            String string = null;

            while (true) {
                string = in.readUTF();
                System.out.println("Сервер принял сообщение: " + string);
                System.out.println("Отправка обратно");
                out.writeUTF(string);
                out.flush();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
