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
    private static final String LN = System.getProperty("line.separator");

    /**
     * Test single server ask.
     * @throws Exception Exception
     */
    @Test
    public void whenSetExitServerThenServerStopWork() throws Exception {
        String word = "exit";
        String result = "";
        testServer(word, result);
    }

    /**
     * Test ask server twice.
     * @throws Exception Exception
     */
    @Test
    public void whenAskHelloServerThenServerAnswerIt() throws Exception {
        String word = Joiner.on(LN).join(
                "hello", "exit");
        String result = Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "", "");
        testServer(word, result);
    }

    /**
     * Test ask server multiple.
     * @throws Exception Exception
     */
    @Test
    public void whenAskSomeWordsServerThenHisAnswer() throws Exception {
        String word = Joiner.on(LN).join(
                "hello", "Give me first answer", "second answer", "next answer please", "exit");
        String result = Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "",
                "simple question", "", "difficult", "question", "", "simple question", "", ""
        );
        testServer(word, result);
    }

    /**
     * Base method.
     * @param incoming String
     * @param result String
     * @throws IOException Exception
     */
    private void testServer(String incoming, String result) throws IOException {
        Socket socket = mock(Socket.class);
        Server server = new Server(socket);
        ByteArrayInputStream in = new ByteArrayInputStream(incoming.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        server.init();
        assertThat(result, is(out.toString()));
    }
}
