package ru.agolovin;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StorageSQLTest {

    @Test
    public void thenGenerate100ThenExpectGetThem() {
            Config config = new Config("xslt.properties");
            StorageSQL sql = new StorageSQL(config);
            sql.init();
        }

}