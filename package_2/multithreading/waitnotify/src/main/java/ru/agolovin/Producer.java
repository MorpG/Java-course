package ru.agolovin;

import java.util.LinkedList;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Producer implements Runnable{
    private LinkedList list;

    @Override
    public void run() {

    }

    public Producer(LinkedList list) {
        this.list = list;
    }



}
