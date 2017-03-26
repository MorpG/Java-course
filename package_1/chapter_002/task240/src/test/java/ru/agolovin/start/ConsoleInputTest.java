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
     * Test method ask.
     */
    @Test
    public final void whenUserInputThenResultIs() {
        String result = "testWord";
        InputStream in = new ByteArrayInputStream(result.getBytes());
        System.setIn(in);
        ConsoleInput console = new ConsoleInput();

        String answer = console.ask("sss");
        assertThat(result, is(answer));

    }

}