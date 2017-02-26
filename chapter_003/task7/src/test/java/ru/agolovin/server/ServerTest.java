package ru.agolovin.server;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {

    private final static String LN = System.getProperty("line.separator");

    @Test
    public void init() throws Exception {
        Socket socket = mock(Socket.class);
        Server server = new Server(socket);
//        String word = "0";
        String result = "2";
        String word = Joiner.on(LN).join(
                "1", "0");
//        String result = Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "", "");
        ByteArrayInputStream in = new ByteArrayInputStream(word.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        server.init();
        System.out.println(out.toString());

        assertThat(result, is(out.toString()));
    }

}