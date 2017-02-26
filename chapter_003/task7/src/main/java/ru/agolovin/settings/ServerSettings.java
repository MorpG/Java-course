package ru.agolovin.settings;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerSettings {

    /**
     * Port Number.
     */
    private int port;

    /**
     * default file path.
     */
    private String startPath;

    /**
     * Base method.
     */
    public ServerSettings() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.port = Integer.parseInt(settings.getValue("port"));
        this.startPath = settings.getValue("home.path");
    }

    /**
     * Getter port number.
     * @return port int
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Getter file path.
     * @return filepath String
     */
    public String getStartPath() {
        return this.startPath;
    }
}
