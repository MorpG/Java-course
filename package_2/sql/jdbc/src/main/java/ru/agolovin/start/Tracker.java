package ru.agolovin.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Tracker methods.
 * contains add, update, delete Item.
 * findById, generateId.
 * getAll, getByFilter.
 *
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
public class Tracker implements AutoCloseable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    /**
     * RN Random.
     */
    private static final Random RN = new Random();

    /**
     * Properties file name.
     */
    private static final String DB_PROPERTIES = "db.properties";

    /**
     * Connection posgreSql.
     */
    private Connection connection;


    /**
     * Constructor.
     */
    Tracker() {
        this.getConnection();
        this.prepareData();
    }

    /**
     * get connection to db.
     */
    private void getConnection() {
        try (InputStream reader = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(reader);
            try {
                this.connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("username"),
                        properties.getProperty("password")
                );
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    /**
     * Prepare table.
     */
    private void prepareData() {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS items ("
                            + "id CHARACTER VARYING(50) PRIMARY KEY, name CHARACTER VARYING(50),"
                            + " description CHARACTER VARYING(50), "
                            + "timeCreate BIGINT);");
            statement.execute("DELETE FROM items");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * add Item into tacker.
     *
     * @param item Item
     */
    final void addItem(final Item item) {
        getConnection();
        item.setId(this.generateId());
        try (PreparedStatement ps = this.connection.prepareStatement(
                "INSERT INTO items (id, name, description, timeCreate) VALUES (?, ?, ?, ?)")) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDescription());
            ps.setLong(4, item.getTimeCreate());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    /**
     * update Item in the tacker.
     *
     * @param item Item
     */
    final void updateItem(final Item item) {
        getConnection();
        try (PreparedStatement ps = this.connection.prepareStatement(
                "UPDATE items SET name = ?, description = ?, timeCreate = ? WHERE id = ?")) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setLong(3, item.getTimeCreate());
            ps.setString(4, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    /**
     * delete Item in the tacker.
     *
     * @param item Item
     */
    final void deleteItem(final Item item) {
        getConnection();
        try (PreparedStatement ps = this.connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            ps.setString(1, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    /**
     * find Item by Id in the tacker.
     *
     * @param id String
     * @return item Item
     */
    final Item findById(final String id) {
        getConnection();
        Item result = null;
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            ps.setString(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    result = new Item(resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getLong(4));
                    result.setId(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * generate new id for Tracker.
     *
     * @return id String
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * show all in Tracker.
     *
     * @return result Array of Items
     */
    final List<Item> getAll() {
        getConnection();
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getLong(4));
                    item.setId(resultSet.getString(1));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close();
        }

        return result;
    }

    /**
     * show by filter in Tracker.
     *
     * @param filter Filter
     * @return result Array of Items
     */
    final List<Item> getByFilter(final Filter filter) {

        ArrayList<Item> result = new ArrayList<>();
        Item temp;
        getConnection();
        try (PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            ps.setString(1, filter.getFilter());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    temp = new Item(rs.getString(2), rs.getString(3), rs.getLong(4));
                    temp.setId(rs.getString(1));
                    result.add(temp);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            close();
        }
        return result;
    }
}
