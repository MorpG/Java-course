package ru.agolovin.server;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Input {

    /**
     * Base method.
     * @param question String
     * @return answer String
     */
    String ask(String question);
}
