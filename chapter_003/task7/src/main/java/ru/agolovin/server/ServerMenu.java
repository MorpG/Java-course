package ru.agolovin.server;

import java.io.*;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
class ServerMenu {

    private static final int MENUSIZE = 6;
    private PrintWriter prW;
    private DataOutputStream dataOutputStream;
    private BufferedReader reader;
    private int position = 0;
    private File currentFileInDir;
    private InputStream inputStream;

    private BaseAction[] baseActionArray = new BaseAction[MENUSIZE];

    ServerMenu(InputStream inputStream, OutputStream outputStream, File home) {
        this.inputStream = inputStream;
        this.prW = new PrintWriter(outputStream, true);
        this.dataOutputStream = new DataOutputStream(outputStream);
        this.currentFileInDir = home;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    void fillActions() {
        this.baseActionArray[position++] = new Exit();
        this.baseActionArray[position++] = new ShowCurDir();
        this.baseActionArray[position++] = new InDir();
        this.baseActionArray[position++] = new OutDir();
        this.baseActionArray[position++] = new Download();
        this.baseActionArray[position++] = new Upload();
    }

    void select(String input) {
        for (BaseAction element : this.baseActionArray) {
            if (element != null && element.key().equals(input)) {
                int key = Integer.parseInt(input);
                this.baseActionArray[key].execute();
            }
        }
    }

    void show() {
        for (BaseAction element : baseActionArray) {
            if (element != null) {
                this.prW.println(element.info());
                System.out.println(element.info());
            }
        }
        this.prW.println("");
    }


    public class Exit extends BaseAction {
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

    public class ShowCurDir extends BaseAction {
        ShowCurDir() {
            super("Show current directory");
        }

        @Override
        String key() {
            return "1";
        }

        @Override
        void execute() {
            prW.println("File list in " + currentFileInDir);
            File[] element;
            if ((element = currentFileInDir.listFiles()) != null) {
                for (File file : element) {
                    prW.println(file.getName());
                }
            } else {
                prW.println("Empty directory");
            }
        }
    }

    public class OutDir extends BaseAction {
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
            if (currentFileInDir.getParentFile() != null) {
                currentFileInDir = currentFileInDir.getParentFile();
                line = String.format("Current dir is: %s", currentFileInDir);
                System.out.println(line);
                prW.println(line);
            } else {
                System.out.println("No parent directory");
                prW.println("No parent directory");
            }

        }
    }

    public class InDir extends BaseAction {
        InDir() {
            super("Step into dir");
        }

        @Override
        String key() {
            return "2";
        }

        @Override
        void execute() {
            File[] element;
            String path = "";
            boolean flag = false;
            if ((element = currentFileInDir.listFiles()) != null) {
                prW.println("Enter directory");
                prW.println("");
                try {
                    path = reader.readLine();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                for (File file : element) {
                    if (file.getName().equals(path)) {
                        currentFileInDir = new File(path);
                        flag = true;
                    }

                }
                if (flag) {
                    prW.println("Directory changed to : " + currentFileInDir);
                } else
                    prW.println("Error change directory");

            }

        }
    }

    public class Download extends BaseAction {
        Download() {
            super("Download file from server");
        }

        @Override
        String key() {
            return "4";
        }

        @Override
        void execute() {
            prW.println("Write file name to download: ");
            prW.println("");
            try {
                String fileName = reader.readLine();
                File[] element;
                if ((element = currentFileInDir.listFiles()) != null) {
                    for (File file : element) {
                        if (file.getName().equals(fileName)) {
                            File fileServer = new File(currentFileInDir + "/" + fileName);
                            if (fileServer.exists() && fileServer.isFile()) {
                                prW.println(file.length());
                                try (FileInputStream fis = new FileInputStream(fileServer)) {
                                    byte[] buffer = new byte[8 * 1024];
                                    int partBuf;
                                    while ((partBuf = fis.read(buffer)) != -1) {
                                        dataOutputStream.write(buffer, 0, partBuf);
                                    }
                                }
                            } else {
                                prW.print("File error");
                            }

                        } else {
                            prW.println("File not found");
                        }
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public class Upload extends BaseAction {
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
                String filename = reader.readLine();
                long fileSize;
                File file = new File(filename);
                if (file.exists() && file.isFile()) {
                    try {
                        fileSize = Long.parseLong(reader.readLine());
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] bytes = new byte[16 * 1024];
                        int partBuf;
                        while ((partBuf = inputStream.read(bytes)) > 0) {
                            fos.write(bytes, 0, partBuf);
                            if (file.length() == fileSize) {
                                break;
                            }
                        }
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
