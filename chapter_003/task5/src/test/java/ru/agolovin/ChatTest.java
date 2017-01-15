package ru.agolovin;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ChatTest {
    private String lineSeparator = System.getProperty("line.separator");

    @Test
    public void chat() throws Exception {
        String answer = "first" + lineSeparator + "закончить";
        System.setIn(new ByteArrayInputStream(answer.getBytes()));

        Chat chat = new Chat();
        chat.chat();
        boolean result = true;
        assertThat(result, is(true));
    }

}