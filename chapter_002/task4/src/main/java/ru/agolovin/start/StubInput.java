package ru.agolovin.start;

/**
 * Method to input from case.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    /**
     * @param answers String array
     */
    private String[] answers;

    /**
     * @param position int
     */
    private int position = 0;

    /**
     * @param sAnswers String array
     */
    public StubInput(final String[] sAnswers) {
        this.answers = sAnswers;
    }

    /**
     * @param question String
     * @return answers String
     */
    public final String ask(final String question) {
        return answers[position++];
    }

}
