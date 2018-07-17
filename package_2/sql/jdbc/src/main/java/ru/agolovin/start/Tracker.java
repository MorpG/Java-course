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
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    /**
     * RN Random.
     */
    private static final Random RN = new Random();
    private static final String DB_CONF_PROPERTIES = "db_conf.properties";
    private Properties properties;
    private Connection connection;
    /**
     * List Items.
     */
    private List<Item> items = new ArrayList<>();

    public void initProperties() {
        try (InputStream reader = getClass().getClassLoader().getResourceAsStream(DB_CONF_PROPERTIES)) {
            this.properties = new Properties();
            try {
                this.connection = DriverManager.getConnection(
                        this.properties.getProperty("url"),
                        this.properties.getProperty("username"),
                        this.properties.getProperty("password")
                );
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                try {
                    this.close();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    /**
     * add Item into tacker.
     *
     * @param item Item
     */
    final void addItem(final Item item) {
        item.setId(this.generateId());
        try (PreparedStatement ps = this.connection.prepareStatement("INSERT INTO items (id, name, description) VALUES (?, ?, ?)")) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * update Item in the tacker.
     *
     * @param item Item
     */
    final void updateItem(final Item item) {
        try (PreparedStatement ps = this.connection.prepareStatement("UPDATE items SET description = ? WHERE id = ?")) {
            ps.setString(1, item.getDescription());
            ps.setString(2, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * delete Item in the tacker.
     *
     * @param item Item
     */
    final void deleteItem(final Item item) {
        try (PreparedStatement ps = this.connection.prepareStatement("delete from items where id = ?")) {
            ps.setString(1, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    /**
     * find Item by Id in the tacker.
     *
     * @param id String
     * @return item Item
     */
    final Item findById(final String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
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
        List<Item> result = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement("select * from items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Item item = new Item(resultSet.getString(2), resultSet.getString(3), 123l);
                    item.setId(resultSet.getString(1));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        List<Item> tmp = new ArrayList<>();
        int length = 0;
        for (Item item : this.items) {
            if (item != null
                    && ((item.getName().equals(filter.getFilter()))
                    || item.getDescription().equals(filter.getFilter()))) {
                tmp.add(item);
                length++;
            }
        }
        List<Item> result = new ArrayList<>();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                result.add(tmp.get(i));
            }
        }

        return result;
    }
}
