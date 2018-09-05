package ru.agolovin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class StorageSQL implements AutoCloseable {

    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Config.
     */
    private final Config config;

    /**
     * Constructor.
     *
     * @param config Config
     */
    public StorageSQL(Config config) {
        this.config = config;
    }

    /**
     * Connect to DB.
     */
    private void connect() {
        try {
            connection = DriverManager.getConnection(config.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create table in DB.
     */
    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(config.getCreate());
            statement.execute(config.getClear());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Generate n rows in DB.
     *
     * @param number int
     */
    public void generate(int number) {
        try (PreparedStatement statement = connection.prepareStatement(
                config.getInsert())) {
            for (int i = 1; i <= number; i++) {
                statement.setInt(1, i);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get List from DB.
     *
     * @return List
     */
    public List<StoreXML.Entry> getData() {
        List<StoreXML.Entry> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(this.config.getSelect())) {
            while (resultSet.next()) {
                result.add(new StoreXML.Entry(Integer.valueOf(resultSet.getString(1))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Initialization.
     */
    public void init() {
        connect();
    }


    @Override
    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
