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

    private Properties prop = new Properties();
    private String driverName;
    private String url;
    private String create;
    private String clear;
    private String insert;

    public Config(String file) {
        load(file);
    }

    public String getUrl() {
        return url;
    }

    public String getCreate() {
        return create;
    }

    public String getClear() {
        return clear;
    }

    public String getInsert() {
        return insert;
    }

    Config load(String file) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(file)) {
            prop.load(in);
            this.driverName = prop.getProperty("drivername");
            this.url = prop.getProperty("url");
            this.create = prop.getProperty("create");
            this.clear = prop.getProperty("clear");
            this.insert = prop.getProperty("insert");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
