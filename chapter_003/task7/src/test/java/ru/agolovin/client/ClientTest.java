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
    private static final String LN = System.getProperty("line.separator");

    /**
     * Test user input and socket received.
     *
     * @throws IOException Exception.
     */
    @Test
    public void whenTestUserInputThenClientReceivedIt() throws IOException {
        String userInput = Joiner.on(LN).join("", "Line", "2", "0" + LN);
        String socketInp = Joiner.on(LN).join("start", "show menu", "", "Line", "", "2", "", "0");
        String socketOutP = Joiner.on(LN).join("start", "show menu", "Line", "2", "0" + LN);
        clientTest(userInput, socketInp, socketOutP);
    }

    /**
     * ClientTest.
     *
     * @param user        String
     * @param socketInput String
     * @param result      String
     * @throws IOException Exception
     */
    private void clientTest(String user, String socketInput, String result) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream inUser = new ByteArrayInputStream(user.getBytes());
        System.setIn(inUser);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream inSocket = new ByteArrayInputStream(socketInput.getBytes());
        when(socket.getInputStream()).thenReturn(inSocket);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.init();
        assertThat(result, is(out.toString()));
    }
}
