package ru.agolovin;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $
 * @since 0.1
 */
public class ParseXML {

    /**
     * Total count from XML field.
     */
    private int count;

    /**
     * Constructor.
     *
     * @param file File.
     */
    ParseXML(File file) {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = parserFactory.newSAXParser();
            Handler handler = new Handler();
            parser.parse(file, handler);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get total count.
     *
     * @return int result
     */
    public int getCount() {
        return count;
    }

    /**
     * Handler.
     */
    class Handler extends DefaultHandler {
        @Override
        public void startElement(String url, String localName, String name,
                                 Attributes attributes) {
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                String atrName = attributes.getQName(i);
                if (atrName != null && atrName.equals("field")) {
                    count += Integer.parseInt(attributes.getValue(i));
                }
            }
        }
    }
}
