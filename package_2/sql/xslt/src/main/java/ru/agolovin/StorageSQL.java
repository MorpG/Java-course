package ru.agolovin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private Connection connection;

    private Config config;

    public StorageSQL(Config config) {
        this.config = config;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(config.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(config.getCreate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(config.getClear());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public List<StoreXML.Entry> getData() {
        List<StoreXML.Entry> result = new ArrayList<>();
        return null;
    }

    public void init() {
        connect();

    }


    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
