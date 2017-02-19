package ru.agolovin.server;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerMenu {

    private static final int MENUSIZE = 5;
    private static final String LN = System.getProperty("line.separator");
    private InputStream in;
    private OutputStream out;
    private int position = 0;
    private File currentFileInDir;

    private BaseAction[] baseActionArray = new BaseAction[MENUSIZE];

    public ServerMenu(InputStream in, OutputStream out, File home) {
        this.in = in;
        this.out = out;
        this.currentFileInDir = home;
    }

    public void fillActions() {
        this.baseActionArray[position++] = new ShowCurDir();
    }

    private BaseAction checkKey(String input) {
        BaseAction result = null;
        for (BaseAction action : this.baseActionArray) {
            if (input.equals(action.key())) {
                result = action;
            }
        }
        return result;
    }

    void select(String input) {
        BaseAction action = checkKey(input);
        if (action != null) {
            action.execute(input);
        } else {
            System.out.println("Команда не найдена (введите help)");
        }
    }


    public class ShowCurDir extends BaseAction {
        @Override
        String key() {
            return "1";
        }

        @Override
        String info() {
            return "Show current directory";
        }

        @Override
        void execute(String text) {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : currentFileInDir.listFiles()) {
                stringBuilder.append(file.getName());
                stringBuilder.append(LN);
            }
        }
    }


}
