package ru.agolovin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class PalindromeFindTest {

    @Test
    public void whenInputPalindromeThenResultTrue() throws Exception {
        PalindromeFind palindrome = new PalindromeFind();
        String userInput = "Ротор";
        boolean check = palindrome.isPalindrome(userInput);

        assertThat(check, is(true));
    }

    @Test
    public void whenInputNotPalindromeThenResultFalse() throws Exception {
        PalindromeFind palindrome = new PalindromeFind();
        String userInput = "Рeтор";
        boolean check = palindrome.isPalindrome(userInput);
        assertThat(check, is(false));
    }

}