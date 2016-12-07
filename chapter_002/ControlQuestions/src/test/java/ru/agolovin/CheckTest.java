package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test methods for Check amount bracket.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class CheckTest {

    /**
     * Test for correct qun ().
     */

    @Test
    public final void whenCorrectThenResult() {
        String test = "()()()()()";
        Check ch = new Check();
        boolean result = ch.validate(test);
        assertThat(result, is(true));
    }

    /**
     * Test for not correct qun ().
     */

    @Test
    public final void whenNotCorrectThenResult() {
        String test = "((())()))";
        Check ch = new Check();
        boolean result = ch.validate(test);
        assertThat(result, is(false));
    }


}
