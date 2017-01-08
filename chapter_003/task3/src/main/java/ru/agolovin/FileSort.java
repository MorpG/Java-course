package ru.agolovin;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * File sort class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class FileSort {

    private RandomAccessFile rafSource;

    private RandomAccessFile rafDistance;

    private String lineSeparator = System.getProperty("lineSeparator");

    public void sort(File source, File distance) throws IOException {
        String fileDistance = "distance.txt";
        try {
            checkExistFile(source);

            distance = new File(fileDistance);
            RandomAccessFile rafDistance = new RandomAccessFile(distance, "rw");

            copyToDistance(source, distance);


            File tempOne = File.createTempFile("tempOne", ".tmp");
            File tempTwo = File.createTempFile("tempTwo", ".tmp");

            RandomAccessFile rafTempOne = new RandomAccessFile(tempOne, "rw");
            RandomAccessFile rafTempTwo = new RandomAccessFile(tempTwo, "rw");

            boolean flag = true;
            do {
                if (splitSourceFile() == 1){
                    flag = false;
                }
                merge();
            } while (flag);

        } catch (IOException ioe) {
            System.err.println("ioe");

        }
    }

    void checkExistFile (File file) throws IOException{
        if (file.exists() && file.isFile()) {
            throw new IOException("source file invalid");
        }
    }

    public int splitSourceFile(File source) {

    }

    private void copyToDistance(File source, File distance) throws IOException{
        rafSource = new RandomAccessFile(source, "r");
        rafDistance = new RandomAccessFile(distance, "rw");
        String line = rafSource.readLine();
        if (line != null) {
            rafDistance.writeBytes(line);
        }
        while ((line = rafSource.readLine()) != null) {
            rafDistance.writeBytes(String.format("%s%s", lineSeparator, line));
        }
        rafSource.close();
    }


}
