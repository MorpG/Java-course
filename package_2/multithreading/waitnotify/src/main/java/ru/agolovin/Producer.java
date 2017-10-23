package ru.agolovin;

import java.util.LinkedList;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Producer implements Runnable{
    private LinkedList<String> list;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (list) {
                while (true);
            }
        }
    }

    public Producer(LinkedList list) {
        this.list = list;
    }



}
