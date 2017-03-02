package ru.agolovin.server;

import com.google.common.base.Joiner;
import org.junit.Test;
import ru.agolovin.settings.ServerSettings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
     *
     * @throws IOException Exception
     */
    @Test
    public void whenShowAllFilesInDirectoryThenSererAnswer() throws IOException {
        String result = "dir1";
        String word = Joiner.on(LN).join(
                "start" , "show menu", "1", "0");
        serverTest(word, result);
    }

    /**
     * Test change directory to parent.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenChangeDirectoryToParentThenServerAnswer() throws IOException {
        String result = "Directory changed to : dir1";
        String word = Joiner.on(LN).join(
                "true", "show menu", "2", "dir1", "0");
        String startPath = new ServerSettings().getStartPath();

        File dirOne = new File(startPath + "//dir1");
        boolean mkDirOne = dirOne.mkdir();
        System.out.println(mkDirOne);
        serverTest(word, result);
    }

    /**
     * Base test method.
     *
     * @param word   String
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
        System.out.println();
        System.out.println("OUT");
        System.out.println(out.toString());
        assertThat(out.toString().contains(result), is(true));

    }
}