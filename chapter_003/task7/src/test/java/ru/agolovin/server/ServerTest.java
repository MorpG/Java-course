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
    private static final String LN = System.getProperty("line.separator");

    /**
     * Create directory to test.
     *
     * @param name String
     */
    private void createDirectory(String name) {
        String startPath = new ServerSettings().getStartPath();
        File startPathDir = new File(startPath);
        File[] dirList = startPathDir.listFiles();
        boolean marker = false;
        if (dirList != null) {
            for (File file : dirList) {
                if (file.getName().equals(name) && file.isDirectory()) {
                    marker = true;
                }
            }
            if (!marker) {
                System.out.println("create directory to test");
                File dirName = new File(startPathDir + "/" + name);
                boolean mkDirOne = dirName.mkdir();
                System.out.println("Create directory: " + mkDirOne);
            }
        } else {
            File dirName = new File(startPathDir + "/" + name);
            boolean mkDirOne = dirName.mkdir();
            System.out.println("Create directory: " + mkDirOne);
        }
    }

    /**
     * Delete directory after test.
     *
     * @param name String
     */
    private void deleteDirectory(String name) {
        String startPath = new ServerSettings().getStartPath();
        File dirOne = new File(startPath);
        File[] dirList = dirOne.listFiles();
        if (dirList != null) {
            for (File file : dirList) {
                if (file.getName().equals(name) && file.isDirectory()) {
                    System.out.println("Directory deleted: " + file.delete());
                }
            }
        } else {
            System.out.println("Directory is empty");
        }
    }

    /**
     * Test show directory server.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenShowAllFilesInDirectoryThenSererAnswer() throws IOException {
        String result = "dir1";
        createDirectory(result);
        String word = Joiner.on(LN).join(
                "start", "show menu", "1", "0");
        serverTest(word, result);
        deleteDirectory(result);
    }

    /**
     * Test change directory to parent.
     *
     * @throws IOException Exception
     */
    @Test
    public void whenChangeDirectoryToSubThenServerAnswer() throws IOException {
        String directoryName = "dir1";
        createDirectory(directoryName);
        ServerSettings settings = new ServerSettings();
        File file = new File(settings.getStartPath());
        String filePathDir1 = file.getAbsolutePath() + "\\" + directoryName;
        String result = "Directory changed to: " + filePathDir1;
        String word = Joiner.on(LN).join(
                "start", "show menu", "1", "2", directoryName, "0");
        serverTest(word, result);
        deleteDirectory(directoryName);

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
        System.out.println("Server OUT");
        System.out.println(out.toString());
        assertThat(out.toString().contains(result), is(true));

    }
}