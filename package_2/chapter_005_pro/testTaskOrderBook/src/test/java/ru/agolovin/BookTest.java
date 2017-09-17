package ru.agolovin;

import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class BookTest {

    /**
     * Test method work.
     *
     * @throws Exception exception
     */
    @Test
    public void testBook() throws Exception {
        long startTime = System.currentTimeMillis();
        Map<String, Book> book = new HashMap<>();
        File file = new File("D:\\Project\\orders.xml");
        ReadFromXML reader = new ReadFromXML(book);
        reader.fillFromXML(file);
        for (String element : book.keySet()) {
            System.out.println("Order book: " + element);
            book.get(element).init();
        }
        long elapsed = System.currentTimeMillis() - startTime;

        System.out.println(String.format("Elapsed time: %s (s)", elapsed / 1000));
    }

}