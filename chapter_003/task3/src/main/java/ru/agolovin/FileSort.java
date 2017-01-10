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

    private File tempOne;

    private File tempTwo;

    private File dist;

    private String lineSeparator = System.getProperty("lineSeparator");

    FileSort() {
        this.tempOne = new File("tempOne.tmp");
        this.tempTwo = new File("tempTwo.tmp");
        this.dist = new File("distance.txt");
    }

    public void sort(File source, File distance) throws IOException {

        if (checkExistFile(source)) {
            this.dist = distance;
        }

        if (splitFile(source)) {
            mergeTempFiles();
            deleteTempFiles();
            while (splitFile(distance)) {
                mergeTempFiles();
                deleteTempFiles();
            }
        }
    }

    private boolean checkExistFile(File file) {
        boolean result = true;
        if (!file.exists() && !file.isFile()) {
            System.out.println("source file invalid");
            result = false;
        }
        return result;
    }

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
            if (rafTempTwo.readLine() == null){
                result = true;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return result;
    }


    private void mergeTempFiles() {
    }

    private void deleteTempFiles() {
        tempOne.delete();
        tempTwo.delete();
    }

    private void write(RandomAccessFile randomAccessFile, String string) {
        try {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeBytes(String.format("%s%s", string, lineSeparator));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

//    private void copyToFile(File source, File distance) throws IOException {
//        rafSource = new RandomAccessFile(source, "r");
//        rafDistance = new RandomAccessFile(distance, "rw");
//        String line = rafSource.readLine();
//        if (line != null) {
//            rafDistance.writeBytes(line);
//        }
//        while ((line = rafSource.readLine()) != null) {
//            rafDistance.writeBytes(String.format("%s%s", lineSeparator, line));
//        }
//        rafSource.close();
//    }


}
