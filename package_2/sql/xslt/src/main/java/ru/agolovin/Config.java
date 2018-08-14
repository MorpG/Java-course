package ru.agolovin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class Config {

    Properties prop = new Properties();

    Config load(String file) {
        try {
            this.prop.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;

    }

    String confData(String data) {
        return this.prop.getProperty(data);
    }


}
