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

    @Test
    public void testBook() throws Exception {
        long startTime = System.currentTimeMillis();
        Map<String, Book> book = new HashMap<>();
        File file = new File("D:\\Project\\orders.xml");
        ReadFromXML reader = new ReadFromXML(book);
        reader.readXML(file);
        for (String element : book.keySet()) {
            System.out.println("Book name: " + element);
            System.out.println("      BUY             SELL");
            System.out.println("Volume @ Price - Volume @ Price");
            book.get(element).init();
            System.out.println();
        }
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println(String.format("Time: %s (milli seconds)",  elapsed));
    }

}