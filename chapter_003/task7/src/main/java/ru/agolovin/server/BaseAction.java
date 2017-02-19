package ru.agolovin.server;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseAction {

    abstract String key();

    abstract String info();

    abstract void execute(String text);

}
