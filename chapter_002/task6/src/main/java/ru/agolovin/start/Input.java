package ru.agolovin.start;

/**
 * interface for input.
 */
public interface Input {

    /**
     * @param question String
     * @return ask User answer String
     */
    String ask(String question);

    /**
     * @param question String
     * @param range    int
     * @return user ask int
     */
    int ask(String question, int[] range);
}
