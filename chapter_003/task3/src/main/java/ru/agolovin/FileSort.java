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
    private String lineSeparator = System.getProperty("line.separator");

    /**
     * Constructor.
     */
    FileSort() {
        this.tempOne = new File("tempOne.tmp");
        this.tempTwo = new File("tempTwo.tmp");
        this.dist = new File("distance.txt");
    }

    /**
     * external sort file.
     *
     * @param source   File
     * @param distance File
     * @throws IOException exception
     */
    public void sort(File source, File distance) throws IOException {

        try {

            if (checkExistFile(source)) {
                if (checkExistFile(distance)) {
                    if (!distance.delete()) {
                        throw new FileSortException("Cant delete distance file");
                    }

                    if (!distance.createNewFile()) {
                        throw new FileSortException("Cant create distance file");
                    }
                    this.dist = distance;
                } else {
                    if (!this.dist.createNewFile()) {
                        throw new FileSortException("Cant create distance file");
                    }

                    this.dist = distance;
                }

                if (splitFile(source)) {
                    mergeTempFiles();

                    while (splitFile(this.dist)) {
                        resetFile(this.dist);
                        mergeTempFiles();
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (FileSortException fse) {
            System.out.println(fse);
        }

    }

    /**
     * check file exist.
     *
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
     *
     * @param file File
     * @return boolean result
     * @throws IOException exception
     */
    private boolean splitFile(File file) throws IOException {
        boolean result = false;

        boolean flag = true;
        int currentLineLength;
        String line;
        int lastLineLength = 0;

        try {

            //tempOne = File.createTempFile("tmp1", ".tmp");
            //tempTwo = File.createTempFile("tmp2", ".tmp");
            resetFile(this.tempOne);
            resetFile(this.tempTwo);
            RandomAccessFile rafFile = new RandomAccessFile(file, "r");
            RandomAccessFile rafTempOne = new RandomAccessFile(this.tempOne, "rw");
            RandomAccessFile rafTempTwo = new RandomAccessFile(this.tempTwo, "rw");

            rafTempOne.seek(0);
            rafTempTwo.seek(0);
            rafFile.seek(0);
            while ((line = rafFile.readLine()) != null) {
                //line = rafFile.readLine();
                currentLineLength = line.length();
                if ((currentLineLength >= lastLineLength) && flag) {
                    write(rafTempOne, line);

                } else if ((currentLineLength < lastLineLength) && flag) {
                    write(rafTempTwo, line);
                    flag = false;

                } else if ((currentLineLength >= lastLineLength) && !flag) {
                    write(rafTempTwo, line);

                } else if ((currentLineLength < lastLineLength) && !flag) {
                    write(rafTempOne, line);
                    flag = true;

                }
                lastLineLength = currentLineLength;
            }
            rafTempTwo.seek(0);
            result = rafTempTwo.length() != 0;

            rafFile.close();
            rafTempOne.close();
            rafTempTwo.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }

        return result;
    }

    /**
     * Reset file.
     * @param file File
     * @throws IOException Exception
     * @throws FileSortException Exception
     */
    public void resetFile(File file) throws IOException, FileSortException {

        if (file.exists()) {
            if (!file.delete()) {
                throw new FileSortException("Cant delete file");
            }
            if (!file.createNewFile()) {
                throw new FileSortException("Cant create file");
            }
        } else if (!file.createNewFile()) {
            throw new FileSortException("Cant create file");
        }
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

            while (!skipTempOne || !skipTempTwo) {
                if ((skipTempTwo || lineTempTwo.length() >= lineTempOne.length()) && !skipTempOne) {
                    write(rafDistance, lineTempOne);
                    if (rafTempOne.getFilePointer() != tempOne.length()) {
                        lineTempOne = rafTempOne.readLine();
                    } else {
                        skipTempOne = true;
                    }

                } else if ((skipTempOne || lineTempOne.length() > lineTempTwo.length()) && !skipTempTwo) {
                    write(rafDistance, lineTempTwo);
                    if (rafTempTwo.getFilePointer() != tempTwo.length()) {
                        lineTempTwo = rafTempTwo.readLine();
                    } else {
                        skipTempTwo = true;
                    }
                }
            }

            rafDistance.close();
            rafTempOne.close();
            rafTempTwo.close();

            if (!this.tempOne.delete()) {
                throw new FileSortException("Cant delete file");
            }

            if (!this.tempTwo.delete()) {
                throw new FileSortException("Cant delete file");
            }
//            tempOne.deleteOnExit();
//            tempTwo.deleteOnExit();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (FileSortException fse) {
            System.out.println(fse);
        }
    }

    /**
     * write string in the file.
     *
     * @param randomAccessFile RandomAccessFile
     * @param string           String
     */
    void write(RandomAccessFile randomAccessFile, String string) {
        try {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeBytes(String.format("%s%s", string, lineSeparator));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}
