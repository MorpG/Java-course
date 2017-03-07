package ru.agolovin.server;

import com.google.common.base.Joiner;
import org.junit.Test;
import ru.agolovin.settings.ServerSettings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

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
     * Test show directory server.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenShowAllFilesInDirectoryThenSererAnswer() throws IOException {
        String result = "dir1";
        String word = Joiner.on(LN).join(
                "start", "show menu", "1", "0");
        String startPath = new ServerSettings().getStartPath();
        File dirOne = new File(startPath);
        File[] dirList = dirOne.listFiles();
        if (dirList != null) {
            for (File file : dirList) {
                if (file.getName().equals(result) && file.isDirectory()) {
                    System.out.println("directory exist");
                }
            }
        } else {
            System.out.println("create directory to test");
            File trt = new File(dirOne + "/dir1");
            boolean mkDirOne = trt.mkdir();
            System.out.println(mkDirOne);
        }
        serverTest(word, result);
    }

    /**
     * Test change directory to parent.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenChangeDirectoryToSubThenServerAnswer() throws IOException {
        String result = "Directory changed to: dir1";
        String word = Joiner.on(LN).join(
                "start", "show menu", "1", "2", "0");
        String startPath = new ServerSettings().getStartPath();
        System.out.println(startPath);
        File dirOne = new File(startPath);
        if (Arrays.asList(dirOne.listFiles()).contains("dir1")) {
            System.out.println("directory exist");
        } else {
            System.out.println("create directory to test");
            File trt = new File(dirOne + "/dir1");
            boolean mkDirOne = trt.mkdir();
            System.out.println(mkDirOne);
        }

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
        ByteArrayInputStream in = new ByteArrayInputStream(word.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.init();
        System.out.println();
        System.out.println("OUT");
        System.out.println(out.toString());
        assertThat(out.toString().contains(result), is(true));

    }
}