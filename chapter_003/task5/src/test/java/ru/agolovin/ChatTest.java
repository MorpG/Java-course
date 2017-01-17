package ru.agolovin;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ChatTest {

    /**
     * Test.
     * @throws Exception exception
     */
    @Test
    public void whenCheckSomeWordFromChatThenCheckForUserAndDictionary() throws Exception {
        String[] answer = {"First", "Стоп", "Word", "Продолжить", "Word2", "Закончить"};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Chat chat = new Chat(new StubInput(answer));
        chat.chat();

        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;

        if (out.toString().contains("Пользователь")) {
            check1 = true;
        }
        if (out.toString().contains("Computer")) {
            check2 = true;
        }
        if (out.toString().contains("Закончить")) {
            check3 = true;
        }

        assertThat(check1, is(true));
        assertThat(check2, is(true));
        assertThat(check3, is(true));

    }
}