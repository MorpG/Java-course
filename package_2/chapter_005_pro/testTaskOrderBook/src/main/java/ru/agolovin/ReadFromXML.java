package ru.agolovin;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */
class ReadFromXML {
    private Map<String, Book> orders;

    ReadFromXML(Map<String, Book> inpMap) {
        this.orders = inpMap;
    }

    void readXML(File path) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xml = XMLInputFactory.newInstance();
        XMLStreamReader reader = xml.createXMLStreamReader(new FileInputStream(path));
        String line;
        while (reader.hasNext())
            if (reader.isStartElement()) {
                line = reader.getLocalName();
                if (line.equals("AddOrder")) {
                    addFromXml(reader);
                }
                if (line.equals("DeleteOrder")) {
                    deleteFromXml(reader);
                }
            }
    }

    private void addFromXml(XMLStreamReader reader) {
        String bookName = reader.getAttributeValue(0);
        String type = reader.getAttributeValue(1);
        float price = Float.valueOf(reader.getAttributeValue(2));
        int value = Integer.valueOf(reader.getAttributeValue(3));
        int id = Integer.valueOf(reader.getAttributeValue(4));
        Order order = new Order(id, value, price, type);
        Book book = this.orders.computeIfAbsent(bookName, k -> new Book());
        book.add(order);
    }

    private void deleteFromXml(XMLStreamReader reader) {
        Book book = this.orders.get(reader.getAttributeValue(0));
        book.delete(Integer.valueOf(reader.getAttributeValue(1)));
    }
}
