package ru.agolovin;

import java.io.BufferedReader;
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

    private String lineSeparator = System.getProperty("lineSeparator");

    public void sort(File source, File distance) throws IOException {
        try {
            RandomAccessFile rafSource = new RandomAccessFile(source, "r");
            RandomAccessFile rafDistance = new RandomAccessFile(distance, "rw");

            String line = rafSource.readLine();
            if (line != null) {
                rafDistance.writeBytes(line);
            }
            while ((line = rafSource.readLine()) != null) {
                rafDistance.writeBytes(String.format("%s%s", lineSeparator, line));
            }
        }
        catch (IOException ioe) {
            System.err.println("ioe");

        }
    }
}
