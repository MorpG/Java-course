package ru.agolovin;

/**
 * Method to input from case.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    /**
     * answers String array.
     */
    private String[] answers;

    /**
     * position int.
     */
    private int position = 0;

    /**
     * @param sAnswers String array
     */
    StubInput(final String[] sAnswers) {
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
