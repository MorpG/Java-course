package ru.agolovin.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.agolovin.models.Filter;
import ru.agolovin.models.Item;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(Tracker.class);
    /**
     * RN Random.
     */
    private static final Random RN = new Random();
    private static final String DB_PROPERTIES = "db.properties";
    private Properties properties;
    private Connection connection;


    /**
     * List Items.
     */
    private List<Item> items = new ArrayList<>();

    public Tracker() {
        this.initProperties();
        this.prepareData();
    }

    private void initProperties() {

        String url = "jdbc:postgresql://localhost:5432/tracker";
        String USERNAME = "postgres";
        String PASSWORD = "password";
        try {
            this.connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        try (InputStream reader = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES)) {
//            this.properties = new Properties();
//            this.properties.load(reader);
//            try {
//                this.connection = DriverManager.getConnection(
//                        this.properties.getProperty("url"),
//                        this.properties.getProperty("username"),
//                        this.properties.getProperty("password")
//                );
//            } catch (SQLException e) {
//                LOGGER.error(e.getMessage(), e);
//            } finally {
//                try {
//                    this.close();
//                } catch (Exception e) {
//                    LOGGER.error(e.getMessage(), e);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

    private void prepareData() {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS items (id VARCHAR(20), name VARCHAR(120), description TEXT, timeCreate BIGINT);");
            statement.execute("DELETE FROM items");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * add Item into tacker.
     *
     * @param item Item
     */
    final void addItem(final Item item) {
        item.setId(this.generateId());
        try (PreparedStatement ps = this.connection.prepareStatement
                ("INSERT INTO items (id, name, description, timeCreate) VALUES (?, ?, ?, ?)")) {
            addToItem(item, ps);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void addToItem(Item item, PreparedStatement ps) throws SQLException {
        ps.setString(1, item.getId());
        ps.setString(2, item.getName());
        ps.setString(3, item.getDescription());
        ps.setLong(4, item.getTimeCreate());
        ps.executeUpdate();
    }

    /**
     * update Item in the tacker.
     *
     * @param item Item
     */
    final void updateItem(final Item item) {
        try (PreparedStatement ps = this.connection.prepareStatement
                ("UPDATE items SET name = ?, description = ?, timeCreate = ? WHERE id = ?")) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDescription());
            ps.setLong(4, item.getTimeCreate());
            ps.executeUpdate();
//            addToItem(item, ps);
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
        try (PreparedStatement ps = this.connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            ps.setString(1, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
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