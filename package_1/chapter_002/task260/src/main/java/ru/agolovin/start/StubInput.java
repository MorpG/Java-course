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
     * nswers String array.
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

    /**
     * @param question String
     * @param range    int
     * @return new UnsupportedOperationException
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        return exist ? key : -1;
    }
}
