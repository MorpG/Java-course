package ru.agolovin.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Test methods for ConsoleInput class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInputTest {

    /**
     * Test method ask String.
     */
    @Test
    public final void whenUserInputStringThenResultIs() {
        String result = "testWord";
        InputStream in = new ByteArrayInputStream(result.getBytes());
        System.setIn(in);
        ConsoleInput console = new ConsoleInput();

        String answer = console.ask("sss");
        assertThat(result, is(answer));

    }

    /**
     * Test method for ask int.
     */
    @Test
    public final void whenUserInputIntThenResultIs() {
        String result = "1";
        int[] range = new int[] {0, 1};
        InputStream in = new ByteArrayInputStream(result.getBytes());
        System.setIn(in);
        ConsoleInput console = new ConsoleInput();

        int answer = console.ask("test question", range);
        assertThat(Integer.parseInt(result), is(answer));

    }

}