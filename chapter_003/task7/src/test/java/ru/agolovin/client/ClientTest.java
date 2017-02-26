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

    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenTestUserInputThenClientReceivedIt() throws IOException {
        String userInput = Joiner.on(LN).join("Line", "2", "0" + LN);
        String socketInput = Joiner.on(LN).join("Answer One", "Answer Two", "Exit");
        clientTest(userInput, socketInput, userInput);
    }


    private void clientTest(String user, String socketInput, String result) throws IOException {
        Socket socket = mock(Socket.class);
        Client client = new Client(socket);
        ByteArrayInputStream inUser = new ByteArrayInputStream(user.getBytes());
        System.setIn(inUser);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream inSocket = new ByteArrayInputStream(socketInput.getBytes());
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(inSocket);
        client.init();
        assertThat(result, is(out.toString()));
    }
}
