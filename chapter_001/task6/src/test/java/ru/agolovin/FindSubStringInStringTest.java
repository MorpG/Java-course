package ru.agolovin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindSubStringInStringTest {
    @Test
    public void whenStringContainsSubStringThanResultIs() {
        FindSubStringInString fnd = new FindSubStringInString();
        String orig = "abcabca";
        String sub = "bcab";
        boolean result = fnd.contains(orig, sub);
        assertThat(result, is(true));
    }

    @Test
    public void whenStringNotContainsSubStringThanResultIs() {
        FindSubStringInString fnd = new FindSubStringInString();
        String orig = "abcabca";
        String sub = "lk";
        boolean result = fnd.contains(orig, sub);
        assertThat(result, is(false));
    }
}

