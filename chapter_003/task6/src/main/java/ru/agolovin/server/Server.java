package ru.agolovin.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
     * Private socket server.
     */
    private Socket socket;

    /**
     * Arrays of the bot answers.
     */
    private String[] botAnswers = {"simple question", "", "difficult", "question", ""};

    /**
     * Default index.
     */
    private int index = 0;

    /**
     * Constructor.
     * @param socket Socket
     */
    Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * main method.
     *
     * @param args String
     */
    public static void main(String[] args) {
        final int port = 23451;
        try (Socket socket = new ServerSocket(port).accept()) {
            new Server(socket).init();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Initialization.
     */
    void init() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else {
                    if (!"exit".equals(ask)) {
                        String[] answers = takeAnswer();
                        for (String element : answers) {
                            out.println(element);
                        }
                    }
                    if (this.index == this.botAnswers.length) {
                        this.index = 0;
                    }
                }
            } while (!("exit".equals(ask)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Local answers.
     *
     * @return Array of the answers.
     */
    private String[] takeAnswer() {

        int stopIndex = this.index;
        for (int i = index; i < this.botAnswers.length; i++) {
            if (!this.botAnswers[i].isEmpty()) {
                stopIndex++;
            } else {
                stopIndex++;
                break;
            }
        }

        String[] answer = new String[stopIndex - this.index];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = this.botAnswers[this.index++];
        }

        return answer;
    }
}
