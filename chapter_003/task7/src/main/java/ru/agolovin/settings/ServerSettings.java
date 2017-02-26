package ru.agolovin.settings;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class ServerSettings {

    private int port;
    private String startPath;

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

    public int getPort() {
        return this.port;
    }

    public String getStartPath() {
        return this.startPath;
    }
}
