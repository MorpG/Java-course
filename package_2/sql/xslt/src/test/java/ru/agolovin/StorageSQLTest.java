package ru.agolovin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StorageSQLTest {

    @Test
    public void thenGenerate100ThenExpectGetThem() {
        Config config = new Config("xslt.properties");
        StorageSQL sql = new StorageSQL(config);
        sql.init();
        sql.createTable();
        int limit = 5;
        sql.generate(limit);
        List<StoreXML.Entry> answer = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            answer.add(new StoreXML.Entry(i));
        }
        List<StoreXML.Entry> resu = sql.getData();
        assertThat(resu, is(answer));
    }


}