package ru.agolovin.server;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerMenu {

    private static final int MENUSIZE = 2;
    private static final String LN = System.getProperty("line.separator");
    private InputStream in;
    private OutputStream out;
    private PrintWriter prW;
    private int position = 0;
    private File currentFileInDir;

    private BaseAction[] baseActionArray = new BaseAction[MENUSIZE];

    public ServerMenu(InputStream in, OutputStream out, File home) {
        this.in = in;
        this.out = out;
        this.prW = new PrintWriter(out, true);
        this.currentFileInDir = home;
    }

    void fillActions() {
        this.baseActionArray[position++] = new Exit();
        this.baseActionArray[position++] = new ShowCurDir();
    }

    void select(String input) {
        for (BaseAction element : this.baseActionArray)
            if (element != null && element.key().equals(input)) {
                int key = Integer.parseInt(input);
                this.baseActionArray[key].execute(input);
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
        void execute(String text) {
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
        void execute(String text) {
            prW.println("File list in " + currentFileInDir);
            File[] element;
            if ((element = currentFileInDir.listFiles()) != null) {
                for (File file : element) {
                    prW.println(file.getName());
                }
            } else {
                prW.print("Empty directory");
            }
        }
    }


}
