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
public class ReadFromXML {
    private Map<String, Order> order;
    private File path;

    ReadFromXML(Map<String, Order> inpMap, File inpPath) {
        this.order = inpMap;
        this.path = inpPath;
    }

    void readXML(File path) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xml = XMLInputFactory.newInstance();
        XMLStreamReader reader = xml.createXMLStreamReader(new FileInputStream(path));
        String line;
        while (reader.hasNext())
            if (reader.isStartElement()) {
                line = reader.getLocalName();
                if (line.equals("AddOrder")) {
                    //TODO
                }
                if (line.equals("DeleteOrder")) {
                    //TODO
                }
            }
    }
}
