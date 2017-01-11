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

    /**
     * First temporary file.
     */
    private File tempOne;

    /**
     * Second temporary file.
     */
    private File tempTwo;

    /**
     * Distance file.
     */
    private File dist;

    /**
     * line separator.
     */
    private String lineSeparator = System.getProperty("lineSeparator");

    /**
     * Constructor.
     */
    FileSort() {
        this.tempOne = new File("tempOne.tmp");
        this.tempTwo = new File("tempTwo.tmp");
        this.dist = new File("distance.txt");
    }

    /**
     * external sort file
     * @param source File
     * @param distance File
     * @throws IOException exception
     */
    public void sort(File source, File distance) throws IOException {

        if (checkExistFile(source)) {
            this.dist = distance;
        }

        if (splitFile(source)) {
            mergeTempFiles();
            while (splitFile(distance)) {
                mergeTempFiles();
            }
        }
    }

    /**
     * check file exist.
     * @param file File
     * @return true if exist
     */
    private boolean checkExistFile(File file) {
        boolean result = true;
        if (!file.exists() && !file.isFile()) {
            System.out.println("source file invalid");
            result = false;
        }
        return result;
    }

    /**
     * check if file already sorted.
     * @param file File
     * @return boolean result
     * @throws IOException exception
     */
    private boolean splitFile(File file) throws IOException {
        boolean result = false;

        boolean flag = true;
        int currentLineLength = 0;
        String line;
        int lastLineLength = 0;

        try {
            RandomAccessFile rafFile = new RandomAccessFile(file, "r");
            RandomAccessFile rafTempOne = new RandomAccessFile(tempOne, "rw");
            RandomAccessFile rafTempTwo = new RandomAccessFile(tempTwo, "rw");

            rafTempOne.seek(0);
            rafTempTwo.seek(0);
            rafFile.seek(0);
            while (rafFile.getFilePointer() != rafFile.length()) {
                line = rafFile.readLine();
                currentLineLength = line.length();
                if (currentLineLength >= lastLineLength && flag) {
                    write(rafTempOne, line);

                } else if (currentLineLength < lastLineLength && flag) {
                    write(rafTempTwo, line);
                    flag = false;

                } else if (currentLineLength >= lastLineLength && !flag) {
                    write(rafTempTwo, line);

                } else if (currentLineLength < lastLineLength && !flag) {
                    write(rafTempOne, line);
                    flag = true;

                }
                lastLineLength = currentLineLength;
            }
            rafTempTwo.seek(0);
            if (rafTempTwo.length() == 0) {
                result = true;
            }

            rafFile.close();
            rafTempOne.close();
            rafTempTwo.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }

        this.tempTwo.delete();
        this.tempOne.delete();
        return result;
    }

    /**
     * merge temp files to one distance.
     */
    private void mergeTempFiles() {
        try {
            RandomAccessFile rafDistance = new RandomAccessFile(dist, "rw");
            RandomAccessFile rafTempOne = new RandomAccessFile(tempOne, "r");
            RandomAccessFile rafTempTwo = new RandomAccessFile(tempTwo, "r");

            rafDistance.seek(0);
            rafTempOne.seek(0);
            rafTempTwo.seek(0);

            boolean skipTempOne = false;
            boolean skipTempTwo = false;

            String lineTempOne = "";
            String lineTempTwo = "";

            if (tempOne.length() != 0) {
                lineTempOne = rafTempOne.readLine();
            } else {
                skipTempOne = true;
            }
            if (tempTwo.length() != 0) {
                lineTempTwo = rafTempTwo.readLine();
            } else {
                skipTempTwo = true;
            }

            do {
                if (!skipTempTwo && (skipTempOne || (lineTempTwo.length() >= lineTempOne.length()))) {
                    write(rafDistance, lineTempOne);
                    if (rafTempOne.getFilePointer() != rafTempOne.length()) {
                        lineTempOne = rafTempOne.readLine();
                    } else {
                        skipTempOne = true;
                    }
                } else if (!skipTempOne && (skipTempTwo || (lineTempOne.length() > lineTempTwo.length()))) {
                    write(rafDistance, lineTempTwo);
                    if (rafTempTwo.getFilePointer() != rafTempTwo.length()) {
                        lineTempTwo = rafTempTwo.readLine();
                    } else {
                        skipTempTwo = true;
                    }
                }
            } while (!skipTempOne || !skipTempTwo);

            rafDistance.close();
            rafTempOne.close();
            rafTempTwo.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * write string in the file.
     * @param randomAccessFile RandomAccessFile
     * @param string String
     */
    private void write(RandomAccessFile randomAccessFile, String string) {
        try {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeBytes(String.format("%s%s", string, lineSeparator));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}
