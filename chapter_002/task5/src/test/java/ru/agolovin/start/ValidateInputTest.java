package ru.agolovin.start;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test methods for ConsoleInput class.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ValidateInputTest {
    /**
     * Test method for ask int in the range menu.
     */
    @Test
    public final void whenUserInputInTheRangeThenResultIs() {
        String result = "1";
        int[] range = new int[] {0, 1};
        InputStream in = new ByteArrayInputStream(result.getBytes());
        System.setIn(in);
        ValidateInput validateInput = new ValidateInput();

        int answer = validateInput.ask("test question", range);
        assertThat(Integer.parseInt(result), is(answer));

    }

    /**
     * Test method for ask int not in the range menu.
     */
    @Test
    public final void whenUserInputNotInTheRangeThenResultIs() {

        String input = "1";
        int[] range = new int[] {0, 2};
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ValidateInput validateInput = new ValidateInput();
        boolean check = false;
        try {
            int answer = validateInput.ask("test question", range);
        } catch (Exception e) {
            check = true;
        }
        assertThat(check, is(true));

    }

}