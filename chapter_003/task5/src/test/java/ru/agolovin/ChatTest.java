package ru.agolovin;

import org.junit.Test;

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
    public void chat() throws Exception {
        String[] answer = {"First", "Стоп", "Word", "Продолжить", "Word2", "Закончить"};
        Chat chat = new Chat(new StubInput(answer));
        chat.chat();
        boolean result = true;
        assertThat(result, is(true));
    }
}