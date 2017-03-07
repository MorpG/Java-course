package ru.agolovin.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
class ServerMenu {

    /**
     * Check.
     */
    private static final String CHECKCORRECT = "correct";
    /**
     * Menu size.
     */
    private static final int MENUSIZE = 6;
    /**
     * Transfer result.
     */
    private static final String TRANSFERRESULT = "Transfer correct";
    /**
     * Transfer result Error.
     */
    private static final String TRANSFERRESULTERR = "Error transferring";
    /**
     * BufferSize.
     */
    private final int bufferSize = 16;
    /**
     * BufferSize.
     */
    private final int bufferSizeN = 1024;
    /**
     * PrintWriter.
     */
    private PrintWriter prW;
    /**
     * DataOutPutStream.
     */
    private DataOutputStream dataOutputStream;
    /**
     * DataInputStream.
     */
    private DataInputStream dataInputStream;
    /**
     * BufferedReader.
     */
    private BufferedReader reader;
    /**
     * Position.
     */
    private int position = 0;
    /**
     * Current Directory.
     */
    private File currentFileDir;
    /**
     * Base action array.
     */
    private BaseAction[] baseActionArray = new BaseAction[MENUSIZE];

    /**
     * Constructor.
     *
     * @param inputStream  InputStream.
     * @param outputStream OutPutStream.
     * @param home         File.
     */
    ServerMenu(InputStream inputStream, OutputStream outputStream, File home) {
        this.prW = new PrintWriter(outputStream, true);
        this.dataOutputStream = new DataOutputStream(outputStream);
        this.dataInputStream = new DataInputStream(inputStream);
        this.currentFileDir = home;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Init menu.
     */
    void fillActions() {
        this.baseActionArray[position++] = new Exit();
        this.baseActionArray[position++] = new ShowCurDir();
        this.baseActionArray[position++] = new InDir();
        this.baseActionArray[position++] = new OutDir();
        this.baseActionArray[position++] = new Download();
        this.baseActionArray[position++] = new Upload();
    }

    /**
     * Select key menu.
     *
     * @param input String
     */
    void select(String input) {
        for (BaseAction element : this.baseActionArray) {
            if (element != null && element.key().equals(input)) {
                int key = Integer.parseInt(input);
                this.baseActionArray[key].execute();
            }
        }
    }

    /**
     * Show menu.
     */
    void show() {
        for (BaseAction element : baseActionArray) {
            if (element != null) {
                this.prW.println(element.info());
            }
        }
        this.prW.println("");
        System.out.println("Show server menu");
    }

    /**
     * Exit.
     */
    public class Exit extends BaseAction {
        /**
         * Constructor.
         */
        Exit() {
            super("Exit");
        }

        @Override
        String key() {
            return "0";
        }

        @Override
        void execute() {
            System.out.println("Exit");
        }
    }

    /**
     * Show current directory.
     */
    public class ShowCurDir extends BaseAction {
        /**
         * Constructor.
         */
        ShowCurDir() {
            super("Show current directory");
        }

        @Override
        String key() {
            return "1";
        }

        @Override
        void execute() {
            prW.println("File list in " + currentFileDir);
            File[] element = currentFileDir.listFiles();
            if (element != null) {
                for (File file : element) {
                    prW.println(file.getName());
                }
            } else {
                prW.println("Empty directory");
            }
        }
    }

    /**
     * Go to parent directory.
     */
    public class OutDir extends BaseAction {
        /**
         * Constructor.
         */
        OutDir() {
            super("Go to parent directory");
        }

        @Override
        String key() {
            return "3";
        }

        @Override
        void execute() {
            String line;
            if (currentFileDir.getParent() != null) {
                currentFileDir = new File(currentFileDir.getParent());
                line = String.format("Current dir is: %s", currentFileDir);
                System.out.println(line);
                prW.println(line);
            } else {
                System.out.println("No parent directory");
                prW.println("No parent directory");
            }
        }
    }

    /**
     * Change directory.
     */
    public class InDir extends BaseAction {
        /**
         * Constructor.
         */
        InDir() {
            super("Step into dir");
        }

        @Override
        String key() {
            return "2";
        }

        @Override
        void execute() {
            File[] element = currentFileDir.listFiles();
            String directoryName = "";
            boolean flag = false;
            if (element != null) {
                prW.println("Enter directory");
                prW.println("");
                try {
                    directoryName = reader.readLine();
                    System.out.println(directoryName);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                for (File file : element) {
                    if (file.getName().equals(directoryName) && file.isDirectory()) {
                        currentFileDir = file.getAbsoluteFile();
                        flag = true;

                    }
                }
                if (flag) {
                    prW.println("Directory changed to : " + currentFileDir);
                } else {
                    prW.println("Error change directory");
                }

            }

        }
    }

    /**
     * Download file to client.
     */
    public class Download extends BaseAction {

        /**
         * Constructor.
         */
        Download() {
            super("Download file from server");
        }

        @Override
        String key() {
            return "4";
        }

        @Override
        void execute() {
            prW.println("Enter file name to download: ");
            prW.println("");
            try {
                String fileName = reader.readLine();
                System.out.println("File name: " + fileName);
                File[] element;
                boolean flag = false;
                element = currentFileDir.listFiles();
                if (element != null) {
                    for (File file : element) {
                        if (file.getName().equals(fileName)) {
                            File fileServer = new File(currentFileDir + "/" + fileName);
                            if (fileServer.exists() && fileServer.isFile()) {
                                flag = true;
                                prW.println("correct");
                                prW.println(file.length());
                                try (FileInputStream fis = new FileInputStream(fileServer)) {
                                    byte[] buffer = new byte[bufferSize * bufferSizeN];
                                    int partBuf;
                                    while ((partBuf = fis.read(buffer)) != -1) {
                                        dataOutputStream.write(buffer, 0, partBuf);
                                        dataOutputStream.flush();
                                    }
                                }
                            } else {
                                prW.print("File error");
                            }
                        }
                    }
                    if (!flag) {
                        prW.println("File not found");
                        System.out.println("File not found");
                    }
                    String resultTransfer = reader.readLine();
                    if (TRANSFERRESULT.equals(resultTransfer)) {
                        System.out.println(resultTransfer);
                    } else if (TRANSFERRESULTERR.equals(resultTransfer)) {
                        System.out.println(resultTransfer);
                    } else {
                        System.out.println("Error");
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Download file to server.
     */
    public class Upload extends BaseAction {
        /**
         * Constructor.
         */
        Upload() {
            super("Upload file to server");
        }

        @Override
        String key() {
            return "5";
        }

        @Override
        void execute() {
            try {
                String fileName = reader.readLine();
                String check = reader.readLine();
                long fileLength;
                if (CHECKCORRECT.equals(check)) {
                    fileLength = Long.parseLong(reader.readLine());
                    if (fileLength > 0) {
                        String filePath = String.format("%s/upload", currentFileDir);
                        String filePath2 = String.format("%s/upload/%s", currentFileDir, fileName);
                        File newFile = new File(filePath);
                        if (!newFile.isDirectory()) {
                            if (!newFile.mkdir()) {
                                System.out.println("Error downloading");
                            }
                        }
                        File newFile2 = new File(filePath2);
                        try (FileOutputStream fos = new FileOutputStream(newFile2)) {
                            byte[] buffer = new byte[bufferSize * bufferSizeN];
                            int partBuffer;
                            long tempLength = fileLength;
                            while (tempLength > 0) {
                                partBuffer = dataInputStream.read(buffer);
                                fos.write(buffer, 0, partBuffer);
                                fos.flush();
                                tempLength -= partBuffer;
                            }

                            if (fileLength == newFile2.length()) {
                                System.out.println(TRANSFERRESULT);
                                prW.println(TRANSFERRESULT);
                                System.out.println(String.format("File download to: %s", newFile.getAbsoluteFile()));
                            } else {
                                prW.println(TRANSFERRESULTERR);
                                System.out.println(TRANSFERRESULTERR);
                            }
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
