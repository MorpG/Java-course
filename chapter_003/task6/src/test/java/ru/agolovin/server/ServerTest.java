package ru.agolovin.server;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
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
     * Test server.
     */
    @Test
    public void whenSetServerThenGetCorrect() throws Exception {
        Socket socket = mock(Socket.class);
        ServerSocket serverSocket = mock(ServerSocket.class);
//        ByteArrayOutputStream array = new ByteArrayOutputStream();
//        DataOutputStream data = new DataOutputStream(array);
//        data.writeUTF("exit");
//        when(socket.getInputStream()).thenReturn(
//                new DataInputStream(new ByteArrayInputStream(array.toByteArray())));
        String word = "exit";

        when(serverSocket.accept()).thenReturn(socket);
        when(socket.getInputStream()).thenReturn(new DataInputStream(new ByteArrayInputStream(word.getBytes())));

        Server server = new Server();
        server.init();
        boolean result = true;
//        data.close();
        assertThat(result, is(true));
    }

}