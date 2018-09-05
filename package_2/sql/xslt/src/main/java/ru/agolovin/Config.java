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

    /**
     * Url database.
     */
    private String url;

    /**
     * Create script.
     */
    private String create;

    /**
     * Clear script.
     */
    private String clear;

    /**
     * Insert script.
     */
    private String insert;

    /**
     * Select script.
     */
    private String select;

    /**
     * Constructor.
     *
     * @param file string filename
     */
    public Config(String file) {
        load(file);
    }

    /**
     * get select script.
     *
     * @return String
     */
    public String getSelect() {
        return select;
    }

    /**
     * Get url.
     *
     * @return string
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Get create.
     *
     * @return string.
     */
    public String getCreate() {
        return this.create;
    }

    /**
     * Get clear.
     *
     * @return String.
     */
    public String getClear() {
        return this.clear;
    }

    /**
     * Get insert.
     *
     * @return String
     */
    public String getInsert() {
        return this.insert;
    }

    /**
     * Load config from property file.
     *
     * @param file File
     */
    private void load(String file) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(file)) {
            prop.load(in);
            this.url = prop.getProperty("url");
            this.create = prop.getProperty("create");
            this.clear = prop.getProperty("clear");
            this.insert = prop.getProperty("insert");
            this.select = prop.getProperty("select");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
