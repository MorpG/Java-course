package ru.agolovin.start;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        int[] range = new int[]{0, 1};
        ByteArrayInputStream in = new ByteArrayInputStream(result.getBytes());
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
        int[] range = new int[]{0, 2};
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ValidateInput validateInput = new ValidateInput();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        boolean check = false;
        try {
            validateInput.ask("test question", range);
        } catch (Exception e) {
            System.out.println("Catch Exception");
        }
        if (out.toString().contains("Please select key from menu.")) {
            check = true;
        }

        assertThat(check, is(true));

    }

}
