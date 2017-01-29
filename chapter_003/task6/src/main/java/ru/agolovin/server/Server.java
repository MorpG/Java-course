package ru.agolovin.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Server {

    /**
     * Port server.
     */
    private int port;

    /**
     * Arrays of the bot answers.
     */
    private String[] botAnswers = {"simple question", "", "difficult", "question", "", "again simple question"};

    /**
     * Default index.
     */
    private int index = 0;

    /**
     * Class constructor.
     */
    private Server() {
        final int defaultport = 23451;
        this.port = defaultport;
    }

    /**
     * main method.
     * @param args String
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }

    /**
     * Initialization.
     */
    private void init() {
        try {
            Socket socket = new ServerSocket(port).accept();
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
                }
            } while (!("exit".equals(ask)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Local answers.
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
        if ((this.index == this.botAnswers.length - 1) && stopIndex == this.index) {
            stopIndex++;
        }
        String[] answer = new String[stopIndex - this.index];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = this.botAnswers[this.index++];
        }
        if ((this.index == this.botAnswers.length - 1) && (!this.botAnswers[this.index].isEmpty())) {
            answer = Arrays.copyOf(answer, answer.length + 1);
            answer[answer.length - 1] = "";
        }
        return answer;
    }
}
