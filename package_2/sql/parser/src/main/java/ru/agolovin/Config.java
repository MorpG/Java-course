package ru.agolovin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class Config {

    /**
     * Properties.
     */
    private final Properties prop = new Properties();

    private String driver;

    private String url;

    private String username;

    private String password;

    private String create;
    private String insert;

    public Config(String file) {
        load(file);
    }

    public String getCreate() {
        return create;
    }

    public String getInsert() {
        return insert;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return this.url;
    }

    private void load(String file) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(file)) {
            prop.load(in);
            this.driver = prop.getProperty("jdbc.driver");
            this.url = prop.getProperty("jdbc.url");
            this.username = prop.getProperty("jdbc.username");
            this.password = prop.getProperty("jdbc.password");
            this.create = prop.getProperty("create");
            this.insert = prop.getProperty("insert");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
