package ru.agolovin.settings;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ClientSettings {
    /**
     * Port.
     */
    private int port;

    /**
     * IP server.
     */
    private String serverAddress;

    /**
     * Constructor.
     */
    public ClientSettings() {
        Settings settings = new Settings();
        ClassLoader loader = ru.agolovin.settings.Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.port = Integer.parseInt(settings.getValue("port"));

        this.serverAddress = settings.getValue("ip_address");
    }

    /**
     * Getter port.
     * @return port integer
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Getter Server IP.
     * @return IP integer.
     */
    public String getServerAddress() {
        return this.serverAddress;
    }
}
