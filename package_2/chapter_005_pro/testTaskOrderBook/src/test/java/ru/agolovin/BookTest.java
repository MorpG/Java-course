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
        for (String e : book.keySet()) {
            System.out.println("Book name: " + e);
            System.out.println("\tBUY" + "\t\tPRICE" + "\tSELL");
            book.get(e).init();
            System.out.println();
        }
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
        boolean res = true;
    }

}