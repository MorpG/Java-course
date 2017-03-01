package ru.agolovin.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerMenu {

    private static final int MENUSIZE = 6;
    private static final String LN = System.getProperty("line.separator");
    private PrintWriter prW;
    private BufferedReader reader;
    private int position = 0;
    private File currentFileInDir;
    private String newFile;

    private BaseAction[] baseActionArray = new BaseAction[MENUSIZE];

    public ServerMenu(BufferedReader reader, PrintWriter writer, File home) {
        this.prW = writer;
        this.currentFileInDir = home;
        this.reader = reader;
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
            String line = "";
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

        }
    }

}
