package ru.agolovin.client;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ClientTest {

    /**
     * Line separator.
     */
    private final static String LN = System.getProperty("line.separator");

    /**
     * Test single user Input.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenAskExitThenClientStopWork() throws IOException {
        String userInput = "exit";
        String socketInput = "Oracle: exit";
        String result = String.format("exit%s", LN);
        clientTest(userInput, socketInput, result);
    }

    /**
     * Test multiple user input.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenAskTwoQuestionThenClient() throws IOException {
        String userInput = Joiner.on(LN).join("hello", "two lines input", "exit");
        String socketInput = Joiner.on(LN).join("Oracle: hello", "", "Oracle: two lines",
                "Oracle: answer", "", "Oracle: exit");
        String result = Joiner.on(LN).join("hello", "two lines input", "exit", "");
        clientTest(userInput, socketInput, result);
    }

    /**
     * Base method.
     *
     * @param user        String
     * @param socketInput String
     * @param result      String
     * @throws IOException Exception
     */
    private void clientTest(String user, String socketInput, String result) throws IOException {
        Socket socket = mock(Socket.class);
        Client client = new Client(socket);
        ByteArrayInputStream inUser = new ByteArrayInputStream(user.getBytes());
        System.setIn(inUser);
        ByteArrayInputStream inSocket = new ByteArrayInputStream(socketInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(inSocket);
        client.init();
        assertThat(result, is(out.toString()));
    }
}
