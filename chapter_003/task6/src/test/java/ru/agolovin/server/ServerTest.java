package ru.agolovin.server;

import org.junit.Test;
import ru.agolovin.client.Client;

import static org.junit.Assert.*;

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
    public void whenSetServerThenGetCorrect() {
        Server server = new Server();
        Client client = new Client();
        server.init();
        client.init();

    }


}