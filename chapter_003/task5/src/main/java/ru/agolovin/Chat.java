package ru.agolovin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Stop.
     */
    private final String stop = "закончить";

    /**
     * Pause.
     */
    private final String pause = "стоп";

    /**
     * Continue.
     */
    private final String repeat = "продолжить";

    /**
     * Input.
     */
    private Input input;

    /**
     * Array list String.
     */
    private ArrayList<String> arrayComputerWords;

    /**
     * Computer words file.
     */
    private File computerWords;

    /**
     * log.
     */
    private File log;

    /**
     * Constructor.
      * @param input Input
     */
    Chat(Input input) {
        this.input = input;
        computerWords = new File("word.txt");
        log = new File("log.txt");
        arrayComputerWords = new ArrayList<>();
    }

    /**
     * Fill Array from File.
     * @param file File
     */
    private void fillArrayFromFile(File file) {
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(file, "r");
            String line;
            while ((line = raf.readLine()) != null) {
                arrayComputerWords.add(line);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Get Random number from range.
     * @param rows int
     * @return random number int
     */
    private int getRandomNumberFromRange(int rows) {
        int result;
        Random random = new Random();
        result = random.nextInt(rows);
        return result;
    }

    /**
     * Chat.
     * @throws Exception exception
     */
    public void chat() throws Exception {

        FileWriter fileWriter = null;

        String lineSeparator = System.getProperty("line.separator");

        try {

            boolean stopFlag = false;

            fillArrayFromFile(computerWords);

            int maxNumberRow = arrayComputerWords.size();

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

            fileWriter = new FileWriter(log);

            do {
                boolean flag = true;
                answer = input.ask("Пользователь:");
                System.out.println(String.format("Пользователь: %s", answer));
                fileWriter.write(answer);
                fileWriter.write(lineSeparator);


                if (pause.equals(answer.toLowerCase())) {
                    stopFlag = true;
                } else if (repeat.equals(answer.toLowerCase())) {
                    stopFlag = false;
                } else if (stop.equals(answer.toLowerCase())) {
                    flag = false;
                }


                if (!stopFlag && flag) {
                    String line = arrayComputerWords.get(getRandomNumberFromRange(maxNumberRow));
                    System.out.println(String.format("Компьютер: %s", line));
                    fileWriter.write(line);
                    fileWriter.write(lineSeparator);
                }

            } while (!stop.equals(answer.toLowerCase()));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
