package ru.agolovin.start;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Test methods for StubInput class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class StubInputTest {
    /**
     * Test method for ask int.
     */
    @Test
    public final void whenUserInputIntThenResultIs() {
        String result = "1";
        String[] stubAnswer = {"1"};
        int[] range = new int[] {0, 1};
        StubInput stubInput = new StubInput(stubAnswer);

        int answer = stubInput.ask("test question", range);
        assertThat(Integer.parseInt(result), is(answer));
    }

    /**
     * Test method for ask int if not in range.
     */
    @Test
    public final void whenUserInputIntNotInRangeThenResultIs() {
        final int firstRange = 5;
        final int secondRange = 6;
        String result = "-1";
        String[] stubAnswer = {"-1"};
        int[] range = new int[] {firstRange, secondRange};
        StubInput stubInput = new StubInput(stubAnswer);

        int answer = stubInput.ask("test question", range);
        assertThat(Integer.parseInt(result), is(answer));
    }

}