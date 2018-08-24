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
    private String select;

    Config(String file) {
        load(file);
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getUrl() {
        return this.url;
    }

    public String getCreate() {
        return this.create;
    }

    public String getClear() {
        return this.clear;
    }

    public String getInsert() {
        return this.insert;
    }

    Config load(String file) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(file)) {
            prop.load(in);
            this.driverName = prop.getProperty("drivername");
            this.url = prop.getProperty("url");
            this.create = prop.getProperty("create");
            this.clear = prop.getProperty("clear");
            this.insert = prop.getProperty("insert");
            this.select = prop.getProperty("select");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
