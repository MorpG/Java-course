package ru.agolovin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class SetDB implements AutoCloseable {

    private static final Logger LOGGER = LogManager.getLogger(SetDB.class);
    private Config config;
    private Connection connection;

    public void connect() {
        this.connection = null;

        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public void insert(String desc, String url, Long data) {
        try (PreparedStatement statement =
                     connection.prepareStatement(config.getInsert())) {
            statement.setString(1, desc);
            statement.setString(2, url);
            statement.setTimestamp(3, new Timestamp(data));
            statement.executeUpdate();
            LOGGER.info("add " + desc + " " + url);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }


    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
