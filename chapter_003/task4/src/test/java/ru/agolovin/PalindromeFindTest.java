package ru.agolovin;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class PalindromeFindTest {

    /**
     * Test then input palindrome word.
     */
    @Test
    public void whenInputPalindromeThenResultTrue() {
        String userInput = "Ротор";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        PalindromeFind palindrome = new PalindromeFind();

        boolean check = palindrome.isPalindrome();

        assertThat(check, is(true));
    }

    /**
     * Test when input not palindrome word.
     */
    @Test
    public void whenInputNotPalindromeThenResultFalse() {
        String userInput = "Рeтор";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);
        PalindromeFind palindrome = new PalindromeFind();
        boolean check = palindrome.isPalindrome();
        assertThat(check, is(false));
    }

}