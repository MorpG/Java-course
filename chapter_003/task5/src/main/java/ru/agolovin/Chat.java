package ru.agolovin;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Создать программу консольный чат. Пользователь вводит слово-фразу, программа берет случайную фразу.
 * из текстового файла и выводит в ответ. Программа замолкает если пользователь вводит.
 * слово «стоп» при этом он может продолжать отправлять сообщения в чат.
 * Если пользователь вводит слово «продолжить» , программа снова начинает отвечать.
 * При вводе слова «закончить» программа прекращает работу. Запись диалога.
 * включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Chat {

    private int maxNumberRow;

    private File computerWords;

    private File log;

    Chat() {
        computerWords = new File("word.txt");
        log = new File("log.txt");
    }

    private int defendNumberRows(File file) {
        int result = 0;
        try {
            FileReader fileReader = new FileReader(file);
            LineNumberReader line = new LineNumberReader(fileReader);

            while (line.readLine() != null) {
                result++;
            }
            line.close();
            fileReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    private String getRandomLineFromFile(File file) {
        String result = "";
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            int quanLine = maxNumberRow - 1;
            Random random = new Random();
            int position = random.nextInt(quanLine);
            raf.seek(position);
            result = raf.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public void chat() throws Exception {

        final String stopWord = "закончить";
        final String pauseWord = "стоп";
        final String continueWord = "продолжить";

        String lineSeparator = System.getProperty("line.separator");

        boolean stopFlag = false;

        this.maxNumberRow = defendNumberRows(computerWords);
        System.out.println(maxNumberRow);

        String answer;

        if (log.exists() && log.isFile()) {
            if (!log.delete()) {
                throw new Exception("Cant reset log");
            }
            if (!log.createNewFile()) {
                throw new Exception("Cant create log");
            }
        } else {
            if (!log.createNewFile()) {
                throw new Exception("Cant create log");
            }
        }

        RandomAccessFile raf = new RandomAccessFile(log, "rw");
        raf.seek(0);

        do {
            answer = ask();
            System.out.println("Пользователь: " + answer);
            raf.writeBytes(answer + lineSeparator);
            if (answer.toLowerCase().equals(pauseWord)) {
                stopFlag = true;
            } else if (answer.toLowerCase().equals(continueWord)) {
                stopFlag = false;
            }

            if (!stopFlag) {
                String line = getRandomLineFromFile(computerWords);
                System.out.println("Компьютер: " + line);
                raf.writeBytes(line + lineSeparator);
            }

        } while (!answer.toLowerCase().equals(stopWord));

        raf.close();
    }

    private String ask() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) throws Exception {
        Chat chat = new Chat();
        chat.chat();
    }

}
