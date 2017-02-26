package ru.agolovin.server;

import java.io.*;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerMenu {

    private static final int MENUSIZE = 4;
    private static final String LN = System.getProperty("line.separator");
    private InputStream in;
    private OutputStream out;
    private PrintWriter prW;
    private DataInputStream dataInputStream;
    private int position = 0;
    private File currentFileInDir;

    private BaseAction[] baseActionArray = new BaseAction[MENUSIZE];

    public ServerMenu(InputStream in, OutputStream out, File home) {
        this.in = in;
        this.out = out;
        this.prW = new PrintWriter(out, true);
        this.currentFileInDir = home;
        this.dataInputStream = new DataInputStream(in);
    }

    void fillActions() {
        this.baseActionArray[position++] = new Exit();
        this.baseActionArray[position++] = new ShowCurDir();
        this.baseActionArray[position++] = new InDir();
        this.baseActionArray[position++] = new OutDir();
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
                StringBuilder sb = new StringBuilder();
                for (File file : element) {
//                    prW.println(file.getName());
                    sb.append(file.getName());
                    sb.append(LN);
                }
                prW.println(sb.toString());
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
            if ((element = currentFileInDir.listFiles()) != null) {
                prW.println("Enter directory");
                try {
                    path = dataInputStream.readUTF();
                    dataInputStream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                for (File file : element) {
                    if (file.getName().equals(path)) {
                        currentFileInDir = new File(path);
                    }
                }
            }

        }
    }

    public class OutDir extends BaseAction {
        public OutDir() {
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

}
