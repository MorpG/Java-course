package ru.agolovin;

import java.sql.Connection;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class StorageSQL implements AutoCloseable{
    Connection connection;

    private Config config;

    public StorageSQL(Config config) {
        this.config = config;
    }

    @Override
    public void close() throws Exception {

    }
}
