package ru.agolovin.server;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {

    /**
     * Line separator.
     */
    private final static String LN = System.getProperty("line.separator");

    /**
     * Test show directory server.
     * @throws IOException Exception
     */
    @Test
    public void whenShowAllFilesInDirectoryThenSererAnswer() throws IOException {
        String result = "Empty directory";
        String word = Joiner.on(LN).join(
                "1", "0");
        serverTest(word, result);
    }

    /**
     * Test change directory to parent.
     * @throws IOException Exception
     */
    @Test
    public void whenChangeDirectoryToParentThenSererAnswer() throws IOException {
        String result = ".";
        String word = Joiner.on(LN).join(
                "1", "3", "0");
        serverTest(word, result);
    }

    /**
     * Base test method.
     * @param word String
     * @param result String
     * @throws IOException Exception
     */
    private void serverTest(String word, String result) throws IOException {
        Socket socket = mock(Socket.class);
        Server server = new Server(socket);
        ByteArrayInputStream in = new ByteArrayInputStream(word.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        server.init();
        System.out.println(out.toString());
        assertThat(out.toString().contains(result), is(true));

    }
}