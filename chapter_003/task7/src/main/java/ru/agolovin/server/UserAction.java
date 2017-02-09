package ru.agolovin.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public interface UserAction {

    /**
     * Key in menu.
     * @return String key number
     */
    String key();

    /**
     * Method.
     * @param dataInput DataInputStream
     * @param dataOutput DataOutputStream
     */
    void execute(DataInputStream dataInput, DataOutputStream dataOutput);

    /**
     * Information.
     * @return String inforamtion
     */
    String info();
}
