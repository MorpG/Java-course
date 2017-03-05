package ru.agolovin.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Settings {

    /**
     * Properties.
     */
    private Properties properties = new Properties();

    /**
     * load properties.
     *
     * @param inputStream InputStream
     */
    public void load(InputStream inputStream) {
        try {
            this.properties.load(inputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Get property value.
     *
     * @param key String
     * @return property value
     */
    public String getValue(String key) {
        return this.properties.getProperty(key);
    }
}
